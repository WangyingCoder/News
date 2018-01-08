package com.cpst.news.network;

import com.cpst.news.model.GankList;
import com.cpst.news.utils.SchedulersCompat;

import io.reactivex.Flowable;

/**
 * Created by wy on 2017/11/25.
 * 请求：
 * 传入参数、拼接API、URL
 * 返回数据
 */

public class GankRequest {

    public static final String GANK_BASE_URL = "http://gank.io/api/";

    public static Flowable<GankList> getDate(String category, int count, int page){
        return NetworkManager.getCommonClient(GANK_BASE_URL)
                .create(APIService.class)
                .getData(category,count,page)
                .compose(SchedulersCompat.applyIoShedulers());
    }
}
