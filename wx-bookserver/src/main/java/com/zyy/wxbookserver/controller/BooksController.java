package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.service.IBooksService;
import com.zyy.wxbookserver.util.ApiConstants;
import com.zyy.wxbookserver.util.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/book")
public class BooksController extends BaseController {

    private static final Logger log=Logger.getLogger(BooksController.class);

    @Resource
    private IBooksService booksService;

    /**
     * @param
     * @Description: 方法说明 获取书架列表
     * @Author: zyy
     * @date: 2018/12/19 19:01
     */
    @ResponseBody
    @RequestMapping(value = "/getBooks" )
    public Map<String,Object> getBooks(String is_all){
        Map<String,Object> data=new HashMap<>();
        Map<String, Object> param =new HashMap<>();
        List<Map<String,Object>> list=  booksService.getBookList(param);
        data.put("data",list);
        return super.getReturnData(data, ApiConstants.Result.SUCCESS);
    }

}
