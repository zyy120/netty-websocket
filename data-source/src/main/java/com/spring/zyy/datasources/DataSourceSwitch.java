package com.spring.zyy.datasources;

import org.springframework.util.Assert;

public class DataSourceSwitch{
    private static final ThreadLocal contextHolder=new ThreadLocal();  
      
    public static void setDataSourceType(String dataSourceType){
        Assert.notNull(dataSourceType, "DataSourceType cannot be null");
        contextHolder.set(dataSourceType);  
    }  
      
    public static String getDataSourceType(){  
        return (String) contextHolder.get();  
    }  
      
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}  