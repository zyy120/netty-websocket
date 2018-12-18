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


	public Map<String, Object> getReturnData(Object data,int resultFlag){
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("result",resultFlag);
		//列表展示数据
		parameters.put("data", data);
		return parameters;
		
	}

	
}