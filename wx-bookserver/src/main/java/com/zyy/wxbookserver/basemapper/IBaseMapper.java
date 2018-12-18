package com.zyy.wxbookserver.basemapper;

import java.util.List;
import java.util.Map;


public interface IBaseMapper<R, PK>
{
	
	
	int insert(R record);
	
	int insertSelective(R record);
	
	int updateByPrimaryKeySelective(R record);
	
	int updateByPrimaryKey(R record);
	
	int deleteByPrimaryKey(PK id);
	
	R selectByPrimaryKey(PK id);

    public List<R> queryListAll(Map<String, Object> parameter);
    
    public List<R> queryListByPage(Map<String, Object> parameter);
    
    int count(Map<String, Object> parameter);
   
}
