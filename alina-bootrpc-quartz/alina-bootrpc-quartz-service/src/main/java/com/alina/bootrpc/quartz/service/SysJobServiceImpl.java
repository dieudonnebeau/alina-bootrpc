package com.alina.bootrpc.quartz.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:53
 * @description：定时任务调度日志业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.quartz.facade.ISysJobService;
import com.alina.bootrpc.quartz.mapper.SysJobMapper;
import com.alina.bootrpc.quartz.model.SysJob;

@Service(version="1.0.0")
public class SysJobServiceImpl  extends BaseServiceImpl<SysJobMapper, SysJob> implements ISysJobService {
}
