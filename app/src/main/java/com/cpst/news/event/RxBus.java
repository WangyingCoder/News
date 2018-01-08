package com.cpst.news.event;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by wy on 2017/12/2.
 *
 */

public class RxBus {

    private final FlowableProcessor<Object> bus;

    public RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    //设置默认实例
    private static class RxBusHolder{
        private static final RxBus instance = new RxBus();
    }

    public static RxBus getDefualt(){
        return RxBusHolder.instance;
    }

    //发送
    public void post(Object o){
        bus.onNext(o);
    }

    //接收订阅事件
    public <T> Flowable<T> toFlowable(Class<T> eventType){
        return bus.ofType(eventType);
    }
}
