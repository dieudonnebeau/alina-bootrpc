package com.alina.bootrpc.generator.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 17:17
 * @description：代码生成业务业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.generator.mapper.SysGenTableMapper;
import com.alina.bootrpc.generator.model.SysGenTable;

@Service(version="1.0.0")
public class SysGenTableServiceImpl  extends BaseServiceImpl<SysGenTableMapper, SysGenTable> implements IBaseService<SysGenTable> {
}
