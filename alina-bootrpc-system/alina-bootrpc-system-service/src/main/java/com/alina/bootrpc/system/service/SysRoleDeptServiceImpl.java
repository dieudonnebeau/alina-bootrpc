package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多(Dieudonne)
 * @date       ：Created on 2019/9/16 16:38
 * @description：角色和部门关联业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysRoleDeptService;
import com.alina.bootrpc.system.mapper.SysRoleDeptMapper;
import com.alina.bootrpc.system.model.SysRoleDept;
import org.springframework.stereotype.Service;

@Service(value = SysRoleDeptServiceImpl.BEAN_NAME)
public class SysRoleDeptServiceImpl  extends BaseServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {
    public final static String BEAN_NAME = "roleDeptService";
}
