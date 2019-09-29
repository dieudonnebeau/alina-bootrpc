package com.alina.bootrpc.common.mapper.ds;

import java.lang.annotation.*;

/**
 * 
 * @ClassName:  TargetDataSource   
 * @Description:在方法上使用，用于指定使用哪个数据源   
 * @author: 湖南无为网电子商务有限公司  (Dieudonne)
 * @date:   2018年4月20日 下午2:11:58   
 *     
 * @Copyright: 2018 http://www.0731333.com Inc. All rights reserved. 
 * 注意：本内容仅限于湖南无为网电子商务有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
