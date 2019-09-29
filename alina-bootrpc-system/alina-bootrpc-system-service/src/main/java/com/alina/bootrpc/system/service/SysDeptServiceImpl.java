package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:15
 * @description：部门业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysDeptService;
import com.alina.bootrpc.system.mapper.SysDeptMapper;
import com.alina.bootrpc.system.model.SysDept;
import org.springframework.stereotype.Service;

@Service(value = SysDeptServiceImpl.BEAN_NAME)
public class SysDeptServiceImpl  extends BaseServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {
    public final static String BEAN_NAME = "deptService";
}
