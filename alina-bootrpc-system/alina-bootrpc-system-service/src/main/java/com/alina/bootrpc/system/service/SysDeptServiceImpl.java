package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:15
 * @description：部门业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.annotation.DataScope;
import com.alina.bootrpc.common.core.annotation.DataSource;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.domain.Ztree;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.core.utils.StringUtils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysDeptService;
import com.alina.bootrpc.system.mapper.SysDeptMapper;
import com.alina.bootrpc.system.mapper.SysUserMapper;
import com.alina.bootrpc.system.model.SysDept;
import com.alina.bootrpc.system.model.SysRole;
import com.alina.bootrpc.system.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(version="1.0.0")
public class SysDeptServiceImpl  extends BaseServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Override
    public String checkDeptNameUnique(SysDept dept)
    {
        Long deptId = BlankUtil.isBlank(dept.getId()) ? -1L : dept.getId();
        SysDept info = new SysDept();
        info.setDeptName(dept.getDeptName());
        info.setParentId(dept.getParentId());
        SysDept result = queryOne(info);
        if (BlankUtil.isNotBlank(result) && result.getId().longValue() != deptId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public Boolean checkDeptExistUser(Long deptId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(deptId);
        int result = userMapper.selectCount(sysUser);
        return result > 0 ? true : false;
    }

    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTree(SysDept dept)
    {
        List<SysDept> deptList = queryList(dept);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    @Override
    public List<Ztree> roleDeptTreeData(SysRole role)
    {
        Long roleId = role.getId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDept> deptList = queryList(new SysDept());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            ztrees = initZtree(deptList, roleDeptList);
        }
        else
        {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }


    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList)
    {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SysDept dept : deptList)
        {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(dept.getId());
                ztree.setpId(dept.getParentId());
                ztree.setName(dept.getDeptName());
                ztree.setTitle(dept.getDeptName());
                if (isCheck)
                {
                    ztree.setChecked(roleDeptList.contains(dept.getId() + dept.getDeptName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }


}
