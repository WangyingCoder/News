package com.cpst.news.utils;


import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wy on 2017/9/27.
 * 线程切换
 * subscribeOn() 指定 subscribe() 发生在 … 线程
 * observeOn()   指定的是它之后的操作所在的线程.
 */

public class SchedulersCompat {
    /**
     * 定义转换器 ioTransformer
     * 上游 进入IO线程执行
     * 然后 返回主线程。
     */
    private static FlowableTransformer ioTransformer = new FlowableTransformer() {
        @Override
        public Publisher apply(@NonNull Flowable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };


    public static <T> FlowableTransformer<T,T> applyIoShedulers(){
        return (FlowableTransformer<T,T>) ioTransformer;
    }
}