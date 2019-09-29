package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.model.SysUserOnline;

import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2019/9/6.
 */
public interface ISysUserOnlineService extends IBaseService<SysUserOnline> {

    /**
     * 查询会话集合
     * @param expiredDate 有效期
     * @return 会话集合
     */
    List<SysUserOnline> selectOnlineByExpired(Date expiredDate);

    /**
     * 通过会话序号删除信息
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    void batchDeleteOnline(List<String> sessions);
}
