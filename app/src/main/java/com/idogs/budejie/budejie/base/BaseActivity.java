package com.idogs.budejie.budejie.base;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.budejie.budejie.model.Notice;
import com.idogs.budejie.budejie.ui.view.SwipeBackLayout;
import com.idogs.budejie.budejie.utils.RxBus;
import com.orhanobut.logger.Logger;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private CompositeDisposable mCompositeDisposable;
    protected ResourceSubscriber mResourceSubscriber;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        initView(savedInstanceState);
    }

    /**
     * 初始化界面
     */
    protected void initView(Bundle savedInstanceState) {
        loadViewLayout();
        bindViews();
        processLogic(savedInstanceState);
        setListener();
    }

    protected void showLog(String log) {
        Logger.i(log);
    }


    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) findViewById(id);
    }

    /**
     * 加载布局
     */
    protected abstract void loadViewLayout();

    /**
     * find控件
     */
    protected abstract void bindViews();


    /**
     * 处理数据
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 界面跳转
     *
     * @param path
     */
    protected void intent2Activity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
//        ToastUtils.showToast(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册事件通知
     */
    public Flowable<Notice> toObservable() {
        return RxBus.getDefault().toFlowable(Notice.class);
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
    public void addSubscription(Observable observable, ResourceSubscriber subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(Flowable.range(1, 8).subscribeWith(subscriber));

    }
}
