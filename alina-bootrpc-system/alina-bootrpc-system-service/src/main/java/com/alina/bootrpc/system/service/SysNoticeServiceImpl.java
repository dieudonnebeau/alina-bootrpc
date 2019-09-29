package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:44
 * @description：通知公告业务逻辑服务
 * @modified By：
 * @version:     1.0
 */
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysNoticeService;
import com.alina.bootrpc.system.mapper.SysNoticeMapper;
import com.alina.bootrpc.system.model.SysNotice;
import org.springframework.stereotype.Service;

@Service(value = SysNoticeServiceImpl.BEAN_NAME)
public class SysNoticeServiceImpl  extends BaseServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {
    public final static String BEAN_NAME = "noticeService";
}
