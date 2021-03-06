package com.lrm.service;

import com.lrm.dao.CommentRepository;
import com.lrm.po.Comment;
import com.lrm.util.SendMailUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limi on 2017/10/22.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(final Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        String toEmail = "13277090522@163.com";
        if (parentCommentId != -1) {
            Comment parentComment = commentRepository.findOne(parentCommentId);
            comment.setParentComment(parentComment);
            toEmail = parentComment.getEmail();
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        // 匿名内部类
        final String finalToEmail = toEmail;
        Runnable task = new Runnable() {
            @Override
            public void run() { // 覆盖重写抽象方法
                String context = "您的文章【"+comment.getBlog().getTitle() + "】又有新评论啦，评论人" +
                        "【"+ comment.getNickname() +"】评论内容【" + comment.getContent() +"】快去看看吧！";
                String subject = "您又有新评论啦";
                if (comment.getContent().indexOf("傻逼") != -1 || comment.getContent().indexOf("垃圾") != -1
                        || comment.getContent().indexOf("辣鸡") != -1 || comment.getContent().indexOf("骚") != -1
                        || comment.getContent().indexOf("脏") != -1) {
                    context += "(该评论含有恶意词汇，建议删除)";
                }
                if (!"13277090522@163.com".equals(finalToEmail)){
                    context = "您在文章【"+comment.getBlog().getTitle() + "】的评论有回复啦，回复人" +
                            "【"+ comment.getNickname() +"】回复内容【" + comment.getContent() +"】快去看看吧！";
                    subject = "您有新回复啦";
                }
                SendMailUtil.send(context, finalToEmail, subject);
            }
        };
        new Thread(task).start(); // 启动线程
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
