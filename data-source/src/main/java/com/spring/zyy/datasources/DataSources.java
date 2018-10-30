package com.spring.zyy.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSources extends AbstractRoutingDataSource{  
	  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return DataSourceSwitch.getDataSourceType();  
    }


}  
