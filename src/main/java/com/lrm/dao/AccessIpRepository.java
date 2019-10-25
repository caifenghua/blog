package com.lrm.dao;

import com.lrm.po.AccessIp;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName AccessIpRepository
 * @Description:
 * @Author: bughua
 * @CreateDate: 2019/10/22 9:42
 */
public interface AccessIpRepository extends JpaRepository<AccessIp,Long> {

}
