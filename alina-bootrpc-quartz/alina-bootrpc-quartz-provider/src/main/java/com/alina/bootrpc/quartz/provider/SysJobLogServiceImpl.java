package com.alina.bootrpc.quartz.provider;
/**
 * @author ：迪艾多
 * @date ：Created on 2019/9/16 17:15
 * @description：定时任务调度业务逻辑服务
 * @modified By：
 * @version: 1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.quartz.facade.ISysJobLogService;
import com.alina.bootrpc.quartz.mapper.SysJobLogMapper;
import com.alina.bootrpc.quartz.model.SysJobLog;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class SysJobLogServiceImpl extends BaseServiceImpl<SysJobLogMapper, SysJobLog> implements ISysJobLogService {

    @Autowired
    private SysJobLogMapper jobLogMapper;
    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog()
    {
        jobLogMapper.cleanJobLog();
    }
}
