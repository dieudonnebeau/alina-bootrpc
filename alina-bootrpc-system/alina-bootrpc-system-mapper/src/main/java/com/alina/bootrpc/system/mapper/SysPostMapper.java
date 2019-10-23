package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysPost;

import java.util.List;

public interface SysPostMapper  extends BaseMapper<SysPost> {

    List<SysPost> selectPostsByUserId(Long userId);
}