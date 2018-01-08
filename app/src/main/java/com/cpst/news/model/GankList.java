package com.cpst.news.model;

import java.util.List;

/**
 * Created by wy on 2017/9/29.
 * 服务器返回数据 resulte
 */

public class GankList {
    /**
     * error : false
     * results : [{"_id":"595f7f6d421aa90ca209c416","createdAt":"2017-07-07T20:32:45.22Z","desc":"根据实际开发中遇到的需求，使用 Gradle 对应用的不同版本进行个性化定制。","images":["http://img.gank.io/0be70b9b-dc5a-4778-bb7b-0f5ff0e73d2a"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"http://www.imliujun.com/gradle1.html","used":true,"who":"LiuJun"},{"_id":"59618134421aa90c9203d34f","createdAt":"2017-07-09T09:04:52.414Z","desc":"PowerShell 环境下节省时间的目录跳转命令","images":["http://img.gank.io/cb2bb9c7-794b-4d43-9d11-b51cdb534fb1"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"拓展资源","url":"https://github.com/vincpa/z","used":true,"who":"jk2K"},{"_id":"5961c646421aa90ca209c41d","createdAt":"2017-07-09T13:59:34.883Z","desc":"浪漫爱情短片《你我这辈子》","publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/FbkGU6CPr?fid=1034:e081f758e64a902ae77593da4d725fb2","used":true,"who":"lxxself"}]
     */
    private boolean error;
    private List<GankItemEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankItemEntity> getResults() {
        return results;
    }

    public void setResults(List<GankItemEntity> results) {
        this.results = results;
    }
}
