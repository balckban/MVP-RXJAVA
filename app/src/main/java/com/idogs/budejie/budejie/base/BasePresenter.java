package com.idogs.budejie.budejie.base;

import com.idogs.budejie.budejie.model.Notice;
import com.idogs.budejie.budejie.utils.RxBus;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by idogs
 */

public class BasePresenter<V> implements Presenter<V> {
    public V mvpView;
    private CompositeDisposable mCompositeDisposable;


    public BasePresenter(V mvpView)
    {
        attachView(mvpView);
    }

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {

    }
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
    /**
     * 发送消息
     */
    public void post(Notice msg) {
        RxBus.getDefault().post(msg);
    }

    /**
     * 注册Rxjava
     * @param observable
     * @param subscriber
     */
    public void addSubscription(Observable observable,  ResourceSubscriber subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(Flowable.range(1, 8).subscribeWith(subscriber));

    }
}
