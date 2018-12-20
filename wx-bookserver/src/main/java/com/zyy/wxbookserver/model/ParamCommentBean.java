package com.zyy.wxbookserver.model;

public class ParamCommentBean {

    private String skey;
    private String content;
    private String bookid;
    private String formid;

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    @Override
    public String toString() {
        return "ParamCommentBean{" +
                "skey='" + skey + '\'' +
                ", content='" + content + '\'' +
                ", bookid='" + bookid + '\'' +
                ", formid='" + formid + '\'' +
                '}';
    }
}
