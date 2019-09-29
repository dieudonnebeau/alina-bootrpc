package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:41
 * @description：岗位信息业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysPostService;
import com.alina.bootrpc.system.mapper.SysPostMapper;
import com.alina.bootrpc.system.model.SysPost;
import org.springframework.stereotype.Service;

@Service(value = SysPostServiceImpl.BEAN_NAME)
public class SysPostServiceImpl  extends BaseServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    public final static String BEAN_NAME = "postService";
}
