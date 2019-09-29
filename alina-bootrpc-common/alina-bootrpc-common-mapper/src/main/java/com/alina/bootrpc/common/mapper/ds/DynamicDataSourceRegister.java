package com.alina.bootrpc.common.mapper.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.validation.DataBinder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: DynamicDataSourceRegister
 * @Description: 动态数据源注册
 * @author: 湖南无为网电子商务有限公司 (Dieudonne)
 * @date: 2018年4月20日 下午2:12:39
 * 
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved.
 *             注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

	// 数据源配置信息
	private PropertyValues dataSourcePropertyValues;
	// 默认数据源
	private DataSource defaultDataSource;
	// 动态数据源
	private Map<String, DataSource> dynamicDataSources = new HashMap<>();

	/**
	 * 加载多数据源配置
	 */
//	@Override
//	public void setEnvironment(Environment env) {
//		// RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env,
//		// "jdbc.");
//		String test = env.getProperty("jdbc.datasources");
//		System.out.println(test);
//		String[] dsPrefixs = jdbcDatasources.getDatasources();
//		for (String dsPrefix : dsPrefixs) {// 多个数据源
//			Map<String, Object> map = new HashMap<String, Object>();
//			if ("ds".equals(dsPrefix)) {
//				map = jdbcDatasources.getDs();
//			}
//			if ("ds1".equals(dsPrefix)) {
//				map = jdbcDatasources.getDs1();
//			}
//			if ("ds2".equals(dsPrefix)) {
//				map = jdbcDatasources.getDs2();
//			}
//			DataSource ds = initDataSource(map);
//			// 设置默认数据源
//			if ("ds".equals(dsPrefix)) {
//				defaultDataSource = ds;
//			} else {
//				dynamicDataSources.put(dsPrefix, ds);
//			}
//			dataBinder(ds, env);
//		}
//	}
	
	/**
	 * 加载多数据源配置
	 */
	@Override
	public void setEnvironment(Environment env) {
		String dsPrefixs = env.getProperty("jdbc.datasources");
		for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源env.getProperty("jdbc."+dsPrefix , Map.class);
			Map<String, Object> map = new HashMap<>();
			map.put("dsType", env.getProperty("jdbc."+dsPrefix+".dsType"));
			map.put("driverClassName", env.getProperty("jdbc."+dsPrefix+".driverClassName"));
			map.put("url", env.getProperty("jdbc."+dsPrefix+".url"));
			map.put("username", env.getProperty("jdbc."+dsPrefix+".username"));
			map.put("password", env.getProperty("jdbc."+dsPrefix+".password"));
			DataSource ds = initDataSource(map);
			// 设置默认数据源
			if ("ds".equals(dsPrefix)) {
				defaultDataSource = ds;
			} else {
				dynamicDataSources.put(dsPrefix, ds);
			}
			dataBinder(ds, env);
		}
	}

	/**
	 * 初始化数据源
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DataSource initDataSource(Map<String, Object> map) {
		String driverClassName = map.get("driverClassName").toString();
		String url = map.get("url").toString();
		String username = map.get("username").toString();
		String password = map.get("password").toString();

		String dsType = map.get("dsType").toString();
		Class<DataSource> dataSourceType;
		DataSource dataSource = null;
		try {
			dataSourceType = (Class<DataSource>) Class.forName(dsType);

			dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username)
					.password(password).type(dataSourceType).build();
			;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	/**
	 * 加载数据源配置信息
	 * 
	 * @param dataSource
	 * @param env
	 */
	private void dataBinder(DataSource dataSource, Environment env) {
		DataBinder dataBinder = new DataBinder(dataSource);
		dataBinder.setIgnoreInvalidFields(false);// false
		dataBinder.setIgnoreUnknownFields(true);// true
		if (dataSourcePropertyValues == null) {
			Map<String, Object> values = new HashMap<>();
			values.put("name", "spring.datasource.name");
			values.put("minIdle", "spring.datasource.minIdle");
			values.put("maxActive", "spring.datasource.maxActive");
			values.put("initialSize", "spring.datasource.initialSize");
			values.put("maxWait", "spring.datasource.maxWait");
			values.put("timeBetweenEvictionRunsMillis", "spring.datasource.timeBetweenEvictionRunsMillis");
			values.put("minEvictableIdleTimeMillis", "spring.datasource.minEvictableIdleTimeMillis");
			values.put("validationQuery", "spring.datasource.validationQuery");
			values.put("testWhileIdle", "spring.datasource.testOnBorrow");
			values.put("testOnBorrow", "spring.datasource.");
			values.put("testOnReturn", "spring.datasource.testOnReturn");
			values.put("poolPreparedStatements", "spring.datasource.poolPreparedStatements");
			values.put("maxPoolPreparedStatementPerConnectionSize", "spring.datasource.maxPoolPreparedStatementPerConnectionSize");
			values.put("removeAbandoned", "spring.datasource.removeAbandoned");
			values.put("filters", "spring.datasource.filters");
			dataSourcePropertyValues = new MutablePropertyValues(values);
		}
		dataBinder.bind(dataSourcePropertyValues);
	}

	/**
	 * 注册数据源been
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 将主数据源添加到更多数据源中
		targetDataSources.put("dataSource", defaultDataSource);
		// 添加更多数据源
		targetDataSources.putAll(dynamicDataSources);

		// 创建DynamicDataSource
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(DynamicDataSource.class);
		beanDefinition.setSynthetic(true);
		MutablePropertyValues mpv = beanDefinition.getPropertyValues();
		mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
		mpv.addPropertyValue("targetDataSources", targetDataSources);
		registry.registerBeanDefinition("dataSource", beanDefinition);

		logger.info("多数据源注册成功");
	}

}
