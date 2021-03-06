package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysLogininfor;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysLogininforService extends IBaseService<SysLogininfor> {

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();
}
