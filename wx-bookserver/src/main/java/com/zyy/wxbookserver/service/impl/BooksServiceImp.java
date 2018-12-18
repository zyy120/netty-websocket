package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.BooksMapper;
import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.service.IBooksService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BooksServiceImp implements IBooksService {
    @Resource
    private BooksMapper booksMapper;
    @Override
    public int deleteByPrimaryKey(Integer bkid) {
        return booksMapper.deleteByPrimaryKey(bkid);
    }

    @Override
    public int insert(Books record) {
        return booksMapper.insert(record);
    }

    @Override
    public int insertSelective(Books record) {
        return booksMapper.insertSelective(record);
    }

    @Override
    public Books selectByPrimaryKey(Integer bkid) {
        return booksMapper.selectByPrimaryKey((bkid));
    }

    @Override
    public int updateByPrimaryKeySelective(Books record) {
        return booksMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Books record) {
        return booksMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Books> getBookList(Map<String, Object> param) {
        return booksMapper.getBookList(param);
    }
}
