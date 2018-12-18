package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.AccessMapper;
import com.zyy.wxbookserver.model.Access;
import com.zyy.wxbookserver.service.IAccessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccessServiceImpl implements IAccessService {

    @Resource
    private AccessMapper accessMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return accessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Access record) {
        return accessMapper.insert(record);
    }

    @Override
    public int insertSelective(Access record) {
        return accessMapper.insertSelective(record);
    }

    @Override
    public Access selectByPrimaryKey(Integer id) {
        return accessMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Access record) {
        return accessMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Access record) {
        return accessMapper.updateByPrimaryKey(record);
    }
}
