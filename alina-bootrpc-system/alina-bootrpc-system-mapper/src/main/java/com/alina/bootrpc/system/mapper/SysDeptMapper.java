package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    List<String> selectRoleDeptTree(@Param("roleId") Long roleId);

}