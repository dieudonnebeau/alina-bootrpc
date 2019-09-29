package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:47
 * @description：系统访问记录业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysLogininforService;
import com.alina.bootrpc.system.mapper.SysLogininforMapper;
import com.alina.bootrpc.system.model.SysLogininfor;
import org.springframework.stereotype.Service;

@Service(value = SysLogininforServiceImpl.BEAN_NAME)
public class SysLogininforServiceImpl  extends BaseServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {
    public final static String BEAN_NAME = "logininforService";
}
