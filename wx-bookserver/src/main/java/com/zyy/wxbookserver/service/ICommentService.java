package com.zyy.wxbookserver.service;

import com.zyy.wxbookserver.model.Comment;

public interface ICommentService {
    int deleteByPrimaryKey(Integer cmid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer cmid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
