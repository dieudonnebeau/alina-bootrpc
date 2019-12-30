package com.alina.bootrpc.quartz.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.quartz.model.SysJobLog;

public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}