package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:16
 * @description：参数配置业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.constant.UserConstants;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysConfigService;
import com.alina.bootrpc.system.mapper.SysConfigMapper;
import com.alina.bootrpc.system.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Service(version="1.0.0")
public class SysConfigServiceImpl  extends BaseServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    @Autowired
    private  SysConfigMapper configMapper;
    @Override
    public String selectConfigByKey(String configKey) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("configKey" , configKey);
        SysConfig sysConfig = queryOneByParams(SysConfig.class , params);
        return BlankUtil.isBlank(sysConfig) ? "" : sysConfig.getConfigValue();
    }

    @Override
    public String checkConfigKeyUnique(SysConfig config)
    {
        Long configId = BlankUtil.isBlank(config.getId()) ? -1L : config.getId();
        SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (BlankUtil.isNotBlank(info) && info.getId().longValue() != configId.longValue())
        {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }
}
