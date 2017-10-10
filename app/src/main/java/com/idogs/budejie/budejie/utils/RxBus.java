package com.idogs.budejie.budejie.utils;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
/**
 * 背压式写法防阻塞
 * Created by Administrator on 2017/9/16 0016.
 */

public class RxBus {
    // 主题
    private final FlowableProcessor<Object> bus;
    // PublishProcessor只会把在订阅发生时间点之后来自原始Observable的数据 发射给观察者
    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }


    // 提供了一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
