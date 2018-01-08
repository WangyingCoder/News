package com.cpst.news.network;

import com.cpst.news.model.ImageList;
import com.cpst.news.utils.SchedulersCompat;

import io.reactivex.Flowable;

/**
 * Created by wy on 2018/1/6.
 *  图片
 */

public class ImageRequest {
    public static final String Image_Url = "https://www.toutiao.com/api/article/feed/?";

    public static Flowable<ImageList> getImageData(){
        return NetworkManager.getCommonClient(Image_Url)
                .create(APIService.class)
                .getImageData()
                .compose(SchedulersCompat.applyIoShedulers());
    }
}
