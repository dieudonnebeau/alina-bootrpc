package com.alina.bootrpc.system.service;
/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/17 9:16
 * @description：参数配置业务逻辑服务
 * @modified By：
 * @version:     1.0
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.impl.BaseServiceImpl;
import com.alina.bootrpc.system.facade.ISysConfigService;
import com.alina.bootrpc.system.mapper.SysConfigMapper;
import com.alina.bootrpc.system.model.SysConfig;
import tk.mybatis.mapper.entity.Example;

@Service(version="1.0.0")
public class SysConfigServiceImpl  extends BaseServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {
    public final static String BEAN_NAME = "configService";

    @Override
    public String selectConfigByKey(String configKey) {
        Example example = new Example(SysConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("config_key",configKey);
        SysConfig sysConfig = queryOneByExample(example);
        return BlankUtil.isBlank(sysConfig) ? "" : sysConfig.getConfigValue();
    }
}
