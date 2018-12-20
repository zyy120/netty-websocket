package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Comment;

import java.util.List;
import java.util.Map;

public interface ICommentService {
    int deleteByPrimaryKey(Integer cmid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer cmid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> getCommentList(Map<String,Object> param);
}
