package com.lrm.service;

import com.lrm.dao.FriendLinkRepository;
import com.lrm.dao.TypeRepository;
import com.lrm.po.FriendLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FriendLinkServiceImpl
 * @Description:
 * @Author: bughua
 * @CreateDate: 2019/7/4 16:29
 */
@Service
public class FriendLinkServiceImpl  implements FriendLinkService{
    @Autowired
    private FriendLinkRepository friendLinkRepository;
    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkRepository.findFriendLink();
    }
}
