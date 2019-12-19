package com.alina.bootrpc.system.framework.shiro.service;


import com.alina.bootrpc.common.core.utils.StringUtils;
import com.alina.bootrpc.system.facade.ISysUserOnlineService;
import com.alina.bootrpc.system.framework.shiro.session.OnlineSession;
import com.alina.bootrpc.system.model.SysUserOnline;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 14:55
 * @description：会话db操作处理
 * @modified By：
 * @version:     1.0
 */
@Component
public class SysShiroService
{
    @Autowired
    private ISysUserOnlineService onlineService;

    /**
     * 删除会话
     *
     * @param onlineSession 会话信息
     */
    public void deleteSession(OnlineSession onlineSession)
    {
        onlineService.deleteByID(String.valueOf(onlineSession.getId()));
    }

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId)
    {
        Map<String , Object> params = new HashMap<>(16);
        params.put("sessionid" , sessionId);
        SysUserOnline userOnline = onlineService.queryOneByParams(SysUserOnline.class , params);
        return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
    }

    public Session createSession(SysUserOnline userOnline)
    {
        OnlineSession onlineSession = new OnlineSession();
        if (StringUtils.isNotNull(userOnline))
        {
            onlineSession.setId(userOnline.getSessionid());
            onlineSession.setHost(userOnline.getIpaddr());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setDeptName(userOnline.getDeptName());
            onlineSession.setLoginName(userOnline.getLoginName());
            onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
            onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
            onlineSession.setTimeout(userOnline.getExpireTime());
        }
        return onlineSession;
    }
}
