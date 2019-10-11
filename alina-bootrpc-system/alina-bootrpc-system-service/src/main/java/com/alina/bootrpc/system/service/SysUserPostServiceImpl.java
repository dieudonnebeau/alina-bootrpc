package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:26
 * @description：用户与岗位关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserPostService;
import com.alina.bootrpc.system.mapper.SysUserPostMapper;
import com.alina.bootrpc.system.model.SysUserPost;

@Service(version="1.0.0")
public class SysUserPostServiceImpl  extends BaseServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {
}
