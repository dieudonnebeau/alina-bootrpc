package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:47
 * @description：系统访问记录业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysLogininforService;
import com.alina.bootrpc.system.mapper.SysLogininforMapper;
import com.alina.bootrpc.system.model.SysLogininfor;

@Service(version="1.0.0")
public class SysLogininforServiceImpl  extends BaseServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {
}
