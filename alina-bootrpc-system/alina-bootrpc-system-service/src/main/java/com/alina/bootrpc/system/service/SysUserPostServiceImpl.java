package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:26
 * @description：用户与岗位关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserPostService;
import com.alina.bootrpc.system.mapper.SysUserPostMapper;
import com.alina.bootrpc.system.model.SysUserPost;
import org.springframework.stereotype.Service;


@Service(value = SysUserPostServiceImpl.BEAN_NAME)
public class SysUserPostServiceImpl  extends BaseServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {
    public final static String BEAN_NAME = "userPostService";
}
