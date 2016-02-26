package com.shu.microservice.model;

import java.io.BufferedReader;
import java.util.Date;

/**
 * Created by wxl on 2016/2/24.
 */
public class ServiceItem {
    private String picUrl;
    private String title;
    private String author;
    private Date createTime;

    public ServiceItem(String picUrl, String title, String author, Date createTime) {
        this.picUrl = picUrl;
        this.title = title;
        this.author = author;
        this.createTime = createTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
