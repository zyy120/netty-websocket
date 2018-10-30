package com.spring.zyy.datasources;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DataSourceMethodInterceptor  implements MethodInterceptor, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> clazz = invocation.getThis().getClass();
        String className = clazz.getName();
        if (ClassUtils.isAssignable(clazz, Proxy.class)) {
            className = invocation.getMethod().getDeclaringClass().getName();
        }
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Method method= invocation.getMethod();
        //logger.trace("execute {}.{}({})", className, methodName, arguments);
        System.out.println("111111111111111"+methodName+"methodName"+arguments+"className"
                +className+"methodLog.description()");
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource ds=method.getAnnotation(DataSource.class);
            String dataSource=ds.value();
            System.out.println(dataSource);
            if(!StringUtils.isEmpty(dataSource)){
                DataSourceSwitch.setDataSourceType(dataSource);
            }else {
                DataSourceSwitch.clearDataSourceType();
            }
        }
        Object result = invocation.proceed();
        return result;
    }
}
