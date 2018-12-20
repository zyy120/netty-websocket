package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.model.Comment;
import com.zyy.wxbookserver.service.IBooksService;
import com.zyy.wxbookserver.service.ICommentService;
import com.zyy.wxbookserver.service.IOrdersService;
import com.zyy.wxbookserver.util.ApiConstants;
import com.zyy.wxbookserver.util.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/book")
public class BooksController extends BaseController {

    private static final Logger log=Logger.getLogger(BooksController.class);

    private  static final String TIME="yyyy-mm-dd HH:mm:ss";

    @Resource
    private IBooksService booksService;

    @Resource
    private IOrdersService ordersService;

    @Resource
    private ICommentService commentService;


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

    /**
     * @param
     * @Description: 方法说明 书本详情 获取评论
     * @Author: zyy
     * @date: 2018/12/20 14:01
     */
    @ResponseBody
    @RequestMapping(value = "/queryBook" )
    public Map<String,Object> queryBook(int bookid ,String skey){
        Map<String,Object> data=new HashMap<>();
        Map<String, Object> param =new HashMap<>();
        param.put("skey",skey);
        param.put("bkid",bookid);
        int byCount=ordersService.getOrderyBuyCount(param);

        if(byCount == 1){
            data.put("is_buy",1);
        }else {
            data.put("is_buy",0);
        }
        List<Comment> lists= commentService.getCommentList(param);
        SimpleDateFormat sdFormat=new SimpleDateFormat(TIME);
         lists.forEach(comment ->{
            comment.setsCtime(sdFormat.format(comment.getCtime()));
        });
        data.put("lists",lists);
        return super.getReturnDatas(data, ApiConstants.Result.SUCCESS);
    }
}
