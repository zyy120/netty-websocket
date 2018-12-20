package com.zyy.wxbookserver.controller;

import com.zyy.wxbookserver.model.Books;
import com.zyy.wxbookserver.model.Comment;
import com.zyy.wxbookserver.model.Users;
import com.zyy.wxbookserver.service.IBooksService;
import com.zyy.wxbookserver.service.ICommentService;
import com.zyy.wxbookserver.service.IUsersService;
import com.zyy.wxbookserver.util.ApiConstants;
import com.zyy.wxbookserver.util.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController extends BaseController {

    @Resource
    private ICommentService commentService;

    @Resource
    private IUsersService usersService;

    @Resource
    private IBooksService booksService;

    @ResponseBody
    @RequestMapping(value = "/write")
    public Map<String, Object> write(@RequestParam(value = "skey", required = false) String skey,
                                     @RequestParam(value = "content", required = false) String content,
                                     @RequestParam(value = "bookid", required = false) String bookid,
                                     @RequestParam(value = "formid", required = false) String formid) {
        if (StringUtils.isEmpty(skey) || StringUtils.isEmpty(content) || StringUtils.isEmpty(bookid)) {
            return super.getReturnError(ApiConstants.Result.FAILURE, "缺少请求参数skey字段，请检查后重试");
        }
        Users users = usersService.selectBySkey(skey);
        if (Objects.isNull(users)) {
            return super.getReturnError(ApiConstants.Result.FAILURE, "用户不存在");
        }
        Books books = booksService.selectByPrimaryKey(Integer.valueOf(bookid));
        if (Objects.isNull(books)) {
            return super.getReturnError(ApiConstants.Result.FAILURE, "未找到书本");
        }

        Comment comment = new Comment();
        comment.setCtime(new Date());
        comment.setCcontent(content);
        comment.setUid(users.getUid());
        comment.setUname(users.getUname());
        comment.setUavatar(users.getUavatar());
        comment.setBkid(Integer.valueOf(bookid));
        comment.setBkname(books.getBkname());
        int count = commentService.insertSelective(comment);

        return super.getReturnError(ApiConstants.Result.SUCCESS, "insert success!");
    }
}
