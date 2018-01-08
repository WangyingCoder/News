package com.cpst.news.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wy on 2017/9/29.
 * 实体类
 */

public class GankItemEntity implements Serializable {
    /**
     * _id : 59618134421aa90c9203d34f
     * createdAt : 2017-07-09T09:04:52.414Z
     * desc : PowerShell 环境下节省时间的目录跳转命令
     * images : ["http://img.gank.io/cb2bb9c7-794b-4d43-9d11-b51cdb534fb1"]
     * publishedAt : 2017-07-11T13:46:33.911Z
     * source : chrome
     * type : 拓展资源
     * url : https://github.com/vincpa/z
     * used : true
     * who : jk2K
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

