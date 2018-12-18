package com.zyy.wxbookserver.service.impl;

import com.zyy.wxbookserver.mapper.CommentMapper;
import com.zyy.wxbookserver.model.Comment;
import com.zyy.wxbookserver.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements ICommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public int deleteByPrimaryKey(Integer cmid) {
        return commentMapper.deleteByPrimaryKey(cmid);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer cmid) {
        return commentMapper.selectByPrimaryKey(cmid);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }
}
