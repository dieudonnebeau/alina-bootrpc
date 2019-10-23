package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:41
 * @description：岗位信息业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysPostService;
import com.alina.bootrpc.system.mapper.SysPostMapper;
import com.alina.bootrpc.system.model.SysPost;

import java.util.HashMap;
import java.util.Map;

@Service(version="1.0.0")
public class SysPostServiceImpl  extends BaseServiceImpl<SysPostMapper, SysPost> implements ISysPostService {


    @Override
    public String checkPostNameUnique(SysPost post)
    {
        Long postId = BlankUtil.isBlank(post.getId()) ? -1L : post.getId();
        Map<String , Object> param = new HashMap<>(16);
        param.put("postName" , post.getPostName());
        SysPost info = queryOneByParams(SysPost.class, param);
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != postId.longValue())
        {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }


    @Override
    public String checkPostCodeUnique(SysPost post)
    {
        Long postId = BlankUtil.isBlank(post.getId()) ? -1L : post.getId();
        Map<String , Object> param = new HashMap<>(16);
        param.put("postCode" , post.getPostCode());
        SysPost info = queryOneByParams(SysPost.class, param);
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != postId.longValue())
        {
            return UserConstants.POST_CODE_NOT_UNIQUE;
        }
        return UserConstants.POST_CODE_UNIQUE;
    }
}
