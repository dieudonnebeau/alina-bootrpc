package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysUserOnline;

import java.util.List;

public interface SysUserOnlineMapper  extends BaseMapper<SysUserOnline> {
    /**
     * 查询过期会话集合
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    List<SysUserOnline> selectOnlineByExpired(String lastAccessTime);

}