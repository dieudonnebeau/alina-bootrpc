package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysDept;
import com.alina.bootrpc.system.model.SysRole;

import java.util.List;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysDeptService extends IBaseService<SysDept> {

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    String checkDeptNameUnique(SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    Boolean checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    List<Ztree> selectDeptTree(SysDept dept);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleDeptTreeData(SysRole role);
}
