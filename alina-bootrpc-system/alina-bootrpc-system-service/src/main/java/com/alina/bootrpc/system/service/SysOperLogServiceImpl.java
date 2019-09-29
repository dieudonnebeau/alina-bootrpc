package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:43
 * @description：操作日志记录业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysOperLogService;
import com.alina.bootrpc.system.mapper.SysOperLogMapper;
import com.alina.bootrpc.system.model.SysOperLog;
import org.springframework.stereotype.Service;

@Service(value = SysOperLogServiceImpl.BEAN_NAME)
public class SysOperLogServiceImpl  extends BaseServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {
    public final static String BEAN_NAME = "operLogService";
}
