package com.zyy.wxbookserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: BaseController
 * @Description: controller基类,目前功能比较简单
 * @author gaogang
 * @date 2016年7月12日 下午3:02:14
 *
 */
public abstract class BaseController {
	

	public Logger logger = LoggerFactory.getLogger(this.getClass());


	public Map<String, Object> getReturnData(Map<String, Object> data,int resultFlag){
		Map<String, Object> parameters=new HashMap<String, Object>();
		data.put("result",resultFlag);
		//列表展示数据
		//data.put("data", data);
		return data;
		
	}
	public Map<String, Object> getReturnDatas(Map<String, Object> data,int resultFlag){
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("result",resultFlag);
		//列表展示数据
		parameters.put("data", data);
		return parameters;

	}

	public Map<String, Object> getReturnError(int result,String errmsg){
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("result",result);
		//列表展示数据
		parameters.put("errmsg", errmsg);
		return parameters;

	}
}