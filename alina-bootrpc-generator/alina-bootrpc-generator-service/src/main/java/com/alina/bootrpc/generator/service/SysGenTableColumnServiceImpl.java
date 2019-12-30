package com.alina.bootrpc.generator.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:11
 * @description：代码生成业务表字段业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.generator.facade.ISysGenTableColumnService;
import com.alina.bootrpc.generator.mapper.SysGenTableColumnMapper;
import com.alina.bootrpc.generator.model.SysGenTableColumn;

@Service(version="1.0.0")
public class SysGenTableColumnServiceImpl  extends BaseServiceImpl<SysGenTableColumnMapper, SysGenTableColumn> implements ISysGenTableColumnService {
}
