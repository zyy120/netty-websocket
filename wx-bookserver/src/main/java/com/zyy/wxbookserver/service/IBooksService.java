package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Books;

import java.util.List;
import java.util.Map;

public interface IBooksService {

    int deleteByPrimaryKey(Integer bkid);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bkid);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

    List<Map<String,Object>  > getBookList(Map<String,Object> param);
}

