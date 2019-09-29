package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 17:15
 * @description：定时任务调度业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysJobLogService;
import com.alina.bootrpc.system.mapper.SysJobLogMapper;
import com.alina.bootrpc.system.model.SysJobLog;
import org.springframework.stereotype.Service;

@Service(value = SysJobLogServiceImpl.BEAN_NAME)
public class SysJobLogServiceImpl  extends BaseServiceImpl<SysJobLogMapper, SysJobLog> implements ISysJobLogService {
    public final static String BEAN_NAME = "jobLogService";
}
