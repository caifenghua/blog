package com.lrm.service;

import com.lrm.dao.AccessIpRepository;
import com.lrm.po.AccessIp;
import com.lrm.util.CusAccessObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AccessIpServiceImpl
 * @Description:
 * @Author: bughua
 * @CreateDate: 2019/10/22 9:41
 */
@Service
public class AccessIpServiceImpl implements AccessIpService{

    @Autowired
    private AccessIpRepository accessIpRepository;

    @Override
    public AccessIp saveComment(AccessIp accessIp) {
        return accessIpRepository.save(accessIp);
    }
}
