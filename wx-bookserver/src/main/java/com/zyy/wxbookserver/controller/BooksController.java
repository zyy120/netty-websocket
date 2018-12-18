package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.service.IBooksService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/api/book")
public class BooksController {

    private static final Logger log=Logger.getLogger(BooksController.class);

    @Resource
    private IBooksService booksService;

    @ResponseBody
    @RequestMapping(value = "/getUser" )
    public List<Books> getBooks(){
        Map<String, Object> param =new HashMap<>();
        List<Books> list=  booksService.getBookList(param);
        return list;
    }

}
