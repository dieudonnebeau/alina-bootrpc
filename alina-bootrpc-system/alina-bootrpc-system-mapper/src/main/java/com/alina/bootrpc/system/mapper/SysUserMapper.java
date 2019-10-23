package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysUser;

import java.util.List;

public interface SysUserMapper  extends BaseMapper<SysUser> {

    /**
     * 根据条件分页查询未已配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUnallocatedList(SysUser user);

}