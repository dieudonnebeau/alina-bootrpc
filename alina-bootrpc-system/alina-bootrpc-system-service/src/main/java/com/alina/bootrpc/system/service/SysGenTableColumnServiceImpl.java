package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:11
 * @description：代码生成业务表字段业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysGenTableColumnService;
import com.alina.bootrpc.system.mapper.SysGenTableColumnMapper;
import com.alina.bootrpc.system.model.SysGenTableColumn;
import org.springframework.stereotype.Service;

@Service(value = SysGenTableColumnServiceImpl.BEAN_NAME)
public class SysGenTableColumnServiceImpl  extends BaseServiceImpl<SysGenTableColumnMapper, SysGenTableColumn> implements ISysGenTableColumnService {
    public final static String BEAN_NAME = "genTableColumnService";
}
