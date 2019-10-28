package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysOperLog;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysOperLogService extends IBaseService<SysOperLog> {

    void cleanOperLog();
}
