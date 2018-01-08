package com.cpst.news.model;

import java.util.List;

/**
 * Created by wy on 2018/1/6.
 * 获取 图片数据
 */

public class ImageList {
    private boolean has_more;
    private String message;
    private List<ImageBean> data;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ImageBean> getData() {
        return data;
    }

    public void setData(List<ImageBean> data) {
        this.data = data;
    }
}
