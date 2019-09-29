package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:29
 * @description：在线用户记录业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.core.utils.DateUtils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserOnlineService;
import com.alina.bootrpc.system.mapper.SysUserOnlineMapper;
import com.alina.bootrpc.system.model.SysUserOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service(value = SysUserOnlineServiceImpl.BEAN_NAME)
public class SysUserOnlineServiceImpl  extends BaseServiceImpl<SysUserOnlineMapper, SysUserOnline> implements ISysUserOnlineService {
    public final static String BEAN_NAME = "userOnlineService";
    @Autowired
    private SysUserOnlineMapper userOnlineMapper;

    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineMapper.selectOnlineByExpired(lastAccessTime);
    }

    @Override
    public void batchDeleteOnline(List<String> sessions) {
        if(BlankUtil.isNotBlank(sessions)){
            for (String sessionId : sessions)
            {
                SysUserOnline userOnline = new SysUserOnline();
                userOnline.setSessionid(sessionId);
                userOnline = queryOne(userOnline);
                if (BlankUtil.isNotBlank(userOnline))
                {
                    deleteByID(userOnline.getId());
                }
            }
        }
    }
}
