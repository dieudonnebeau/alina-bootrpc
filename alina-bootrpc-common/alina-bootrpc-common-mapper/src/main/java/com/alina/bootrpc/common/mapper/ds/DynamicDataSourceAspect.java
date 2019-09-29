package com.alina.bootrpc.common.mapper.ds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName:  DynamicDataSourceAspect   
 * @Description:切换数据源
 * @author: 湖南无为网电子商务有限公司  (Dieudonne)
 * @date:   2018年4月20日 下午2:10:28   
 *     
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved. 
 * 注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	/**
	 * 根据@TargetDataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
	 * @param point
	 * @param ds
	 * @throws Throwable
	 */
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        DynamicDataSource.setDataSourceType(ds.value());
    }

    /**
     * 方法执行完毕后清除数据源
     * @param point
     * @param ds
     */
    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        DynamicDataSource.clearDataSourceType();
    }

}
