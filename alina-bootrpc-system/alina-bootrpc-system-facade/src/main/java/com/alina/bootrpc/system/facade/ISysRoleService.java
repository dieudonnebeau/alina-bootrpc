package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysRole;

import java.util.Set;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysRoleService extends IBaseService<SysRole> {
    /**
     * 根据用户ID查询角色
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRoleKeys(Long userId);
}
