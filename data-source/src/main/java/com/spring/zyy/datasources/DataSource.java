package com.spring.zyy.datasources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @param null
 * @Description: 方法说明 数据源注解
 * @Author: zyy
 * @date: 20* @param null6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String value() default "";
}
