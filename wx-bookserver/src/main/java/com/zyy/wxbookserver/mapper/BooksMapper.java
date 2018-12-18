package com.zyy.wxbookserver.mapper;

import com.zyy.wxbookserver.basemapper.IBaseMapper;
import com.zyy.wxbookserver.model.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BooksMapper extends IBaseMapper {
    int deleteByPrimaryKey(Integer bkid);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bkid);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

    List<Books> getBookList(Map<String,Object> param);
}