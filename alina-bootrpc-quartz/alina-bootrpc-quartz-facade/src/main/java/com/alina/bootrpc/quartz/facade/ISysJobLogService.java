package com.alina.bootrpc.quartz.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.quartz.model.SysJobLog;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysJobLogService extends IBaseService<SysJobLog> {

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
