package com.alina.bootrpc.system.mapper;

import com.alina.bootrpc.common.mapper.dao.BaseMapper;
import com.alina.bootrpc.system.model.SysLogininfor;

public interface SysLogininforMapper  extends BaseMapper<SysLogininfor> {

    /**
     * 清空系统登录日志
     */
    void cleanLogininfor();

}