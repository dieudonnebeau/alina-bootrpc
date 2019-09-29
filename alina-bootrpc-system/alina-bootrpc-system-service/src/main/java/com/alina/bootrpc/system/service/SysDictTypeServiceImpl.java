package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:12
 * @description：字典类型业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysDictTypeService;
import com.alina.bootrpc.system.mapper.SysDictTypeMapper;
import com.alina.bootrpc.system.model.SysDictType;
import org.springframework.stereotype.Service;

@Service(value = SysDictTypeServiceImpl.BEAN_NAME)
public class SysDictTypeServiceImpl  extends BaseServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {
    public final static String BEAN_NAME = "dictTypeService";
}
