package com.zyy.wxbookserver.model;

public class Books {
    private Integer bkid;

    private Integer bkclass;

    private String bkname;

    private String bkauthor;

    private String bkpublisher;

    private String bkfile;

    private String bkcover;

    private Integer bkprice;

    public Integer getBkid() {
        return bkid;
    }

    public void setBkid(Integer bkid) {
        this.bkid = bkid;
    }

    public Integer getBkclass() {
        return bkclass;
    }

    public void setBkclass(Integer bkclass) {
        this.bkclass = bkclass;
    }

    public String getBkname() {
        return bkname;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname == null ? null : bkname.trim();
    }

    public String getBkauthor() {
        return bkauthor;
    }

    public void setBkauthor(String bkauthor) {
        this.bkauthor = bkauthor == null ? null : bkauthor.trim();
    }

    public String getBkpublisher() {
        return bkpublisher;
    }

    public void setBkpublisher(String bkpublisher) {
        this.bkpublisher = bkpublisher == null ? null : bkpublisher.trim();
    }

    public String getBkfile() {
        return bkfile;
    }

    public void setBkfile(String bkfile) {
        this.bkfile = bkfile == null ? null : bkfile.trim();
    }

    public String getBkcover() {
        return bkcover;
    }

    public void setBkcover(String bkcover) {
        this.bkcover = bkcover == null ? null : bkcover.trim();
    }

    public Integer getBkprice() {
        return bkprice;
    }

    public void setBkprice(Integer bkprice) {
        this.bkprice = bkprice;
    }
}