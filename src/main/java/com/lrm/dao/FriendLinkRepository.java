package com.lrm.dao;

import com.lrm.po.FriendLink;
import com.lrm.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName FriendLinkRepository
 * @Description: TODO
 * @Author: bughua
 * @CreateDate: 2019/7/4 16:30
 */
public interface FriendLinkRepository extends JpaRepository<Type,Long> {
    @Query("select t from FriendLink t")
    List<FriendLink> findFriendLink();
}
