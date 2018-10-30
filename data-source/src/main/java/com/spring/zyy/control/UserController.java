package com.spring.zyy.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.spring.zyy.datasources.DataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.zyy.datasources.DataSourceInstances;
import com.spring.zyy.datasources.DataSourceSwitch;
import com.spring.zyy.pojo.User;
import com.spring.zyy.service.impl.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {
  private static final Logger log=Logger.getLogger(UserController.class);
	@Resource
	private IUserService userService;

	@DataSource(value = DataSourceInstances.IN)
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		log.info("into toIndex…… end!");
		return "index";
	}

	/**
	 * @param null
	 * @Description: 方法说明 不基于注解 aop 数据源切换模式
	 * @Author: zyy
	 * @date: 2018/10/30 11:27
	 */
	@RequestMapping("/data")
	public String testDataSources(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = null;
				user=this.userService.getUserById(userId);
		model.addAttribute("user", user);
		
		
		log.info("test1这是一个测试,获取默认数据连接data1:"+user.toString()); 
			user=this.userService.getUserById(userId);
			
	        DataSourceSwitch.setDataSourceType(DataSourceInstances.IN);  
	        user=this.userService.getUserById(userId);
	     log.info("test2这是一个测试,获取数据连接data1:"+user.toString());  
	     
	        DataSourceSwitch.setDataSourceType(DataSourceInstances.OUT);  
	        user=this.userService.getUserById(userId);
	      log.info("test3这是一个测试,获取数据连接data2:"+user.toString());  
		
		
		log.info("into toIndex…… end!");
		return "index";
	}

	@DataSource(value = DataSourceInstances.IN)
	@RequestMapping("/datain")
	public String datain(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		log.info("into toIndex…… end!");
		return "index";
	}
	@DataSource(value = DataSourceInstances.OUT)
	@RequestMapping("/dataout")
	public String dataout(HttpServletRequest request,Model model){
		log.info("into toIndex…… start!");
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		log.info("into toIndex…… end!");
		return "index";
	}
}