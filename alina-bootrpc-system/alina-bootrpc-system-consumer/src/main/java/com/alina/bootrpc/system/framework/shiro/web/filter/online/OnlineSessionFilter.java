package com.alina.bootrpc.system.framework.shiro.web.filter.online;


import com.alina.bootrpc.common.core.constant.ShiroConstants;
import com.alina.bootrpc.common.core.enums.OnlineStatus;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.system.facade.ISysDeptService;
import com.alina.bootrpc.system.framework.shiro.session.OnlineSession;
import com.alina.bootrpc.system.framework.shiro.session.OnlineSessionDAO;
import com.alina.bootrpc.system.framework.util.ShiroUtils;
import com.alina.bootrpc.system.model.SysDept;
import com.alina.bootrpc.system.model.SysUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 15:02
 * @description：自定义访问控制
 * @modified By：
 * @version:     1.0
 */
public class OnlineSessionFilter extends AccessControlFilter
{
    /**
     * 强制退出后重定向的地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception
    {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null)
        {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession)
        {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
            if (isGuest == true)
            {
                SysUser user = ShiroUtils.getSysUser();
                if (user != null)
                {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setLoginName(user.getLoginName());
					onlineSession.setAvatar(user.getAvatar());
                    SysDept sysDept = deptService.queryByID(user.getDeptId());
                    if(BlankUtil.isNotBlank(sysDept)){
                        onlineSession.setDeptName(sysDept.getDeptName());
                    }
                    onlineSession.markAttributeChanged();
                }
            }

            if (onlineSession.getStatus() == OnlineStatus.off_line)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        Subject subject = getSubject(request, response);
        if (subject != null)
        {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

    // 跳转到登录页
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException
    {
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
