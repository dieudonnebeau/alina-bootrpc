package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:53
 * @description：定时任务调度日志业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysJobService;
import com.alina.bootrpc.system.mapper.SysJobMapper;
import com.alina.bootrpc.system.model.SysJob;
import org.springframework.stereotype.Service;

@Service(value = SysJobServiceImpl.BEAN_NAME)
public class SysJobServiceImpl  extends BaseServiceImpl<SysJobMapper, SysJob> implements ISysJobService {
    public final static String BEAN_NAME = "jobService";
}
