package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/16 16:29
 * @description：在线用户记录业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.core.utils.DateUtils;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysUserOnlineService;
import com.alina.bootrpc.system.mapper.SysUserOnlineMapper;
import com.alina.bootrpc.system.model.SysUserOnline;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(version="1.0.0")
public class SysUserOnlineServiceImpl  extends BaseServiceImpl<SysUserOnlineMapper, SysUserOnline> implements ISysUserOnlineService {
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
                Map<String , Object> params = new HashMap<>();
                params.put("sessionid" , sessionId);
                SysUserOnline userOnline = queryOneByParams(SysUserOnline.class , params);
                if (BlankUtil.isNotBlank(userOnline))
                {
                    deleteByID(userOnline.getId());
                }
            }
        }
    }
}
