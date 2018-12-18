package com.zyy.wxbookserver.mapper;

import com.zyy.wxbookserver.basemapper.IBaseMapper;
import com.zyy.wxbookserver.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface CommentMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer cmid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer cmid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}