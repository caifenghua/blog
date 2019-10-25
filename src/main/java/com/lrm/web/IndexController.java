package com.lrm.web;

import com.lrm.listener.MyHttpSessionListener;
import com.lrm.po.AccessIp;
import com.lrm.service.*;
import com.lrm.util.CusAccessObjectUtil;
import com.lrm.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by limi on 2017/10/13.
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private AccessIpService accessIpService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model, HttpSession  session, HttpServletRequest request) {
        session.setAttribute("loginName",RandomUtil.generateLowerString(10));
        session.setMaxInactiveInterval(180);
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        model.addAttribute("friendLinks", friendLinkService.listFriendLink());
        model.addAttribute("online", MyHttpSessionListener.online);
        String ipAddress = CusAccessObjectUtil.getIpAddress(request);
        AccessIp accessIp = new AccessIp();
        accessIp.setIp(ipAddress);
        accessIp.setCreate_at(new Date());
        accessIpService.saveComment(accessIp);
        return "index";
    }

    @GetMapping("/online")
    public int online(){
        return MyHttpSessionListener.online;
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

}
