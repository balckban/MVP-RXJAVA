package com.idogs.budejie.budejie.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.idogs.budejie.budejie.R;
import com.idogs.budejie.budejie.model.Notice;
import com.idogs.budejie.budejie.utils.RxBus;
import com.orhanobut.logger.Logger;


import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 *baseFragment
 * Created by Idogs on 2017/9/16 0016.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mContext;  //上下文
    protected boolean mIsFirstVisible = true;  //界面是否可见
    protected View rootView;  //view
    protected Disposable mDisposable; //Rx-mDisposable
    protected TextView mBarTitle;
    protected ImageView mBarBack;
    protected ImageView mBarRightIv;
    protected TextView mBarRightTv;
    public FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        return loadViewLayout(inflater, container);
    }
    /**
     * 加载布局
     */
    protected abstract View loadViewLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * oncreatview执行后执行,初始化
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext=getActivity();
        rootView=view;
        initView(view);
        initBarTitleView();
        initTitle();
        boolean isVis = isHidden() || getUserVisibleHint();  //isHidden=!isVisible
        if (isVis && mIsFirstVisible) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null){
            mDisposable.dispose();   //解除所有订阅
        }
    }
    /**
     * 初始化界面
     *
     * @param view
     */
    private void initView(View view) {
        bindViews(view);
        processLogic();
        setListener();
    }


    /**
     * base标题初始化
     */
    public void initBarTitleView() {

        mBarTitle = rootView.findViewById(R.id.fragment_title);
        mBarBack = rootView.findViewById(R.id.fragment_back);
        mBarRightIv =  rootView.findViewById(R.id.fragment_title_right);
        mBarRightTv =  rootView.findViewById(R.id.fragment_title_right_tv);

        if (mBarBack != null) {
            mBarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });
        }

//        if (mBarTitle != null) {
//            mBarTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    _mActivity.showFragmentStackHierarchyView();
//
//                }
//            });
//        }
    }
    /**
     * find控件
     *
     * @param view
     */
    protected abstract void bindViews(View view);



    protected abstract void initTitle();
    /**
     * 处理数据
     */
    protected abstract void processLogic();

    /**
     * 设置监听
     */
    protected abstract void setListener();
    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    /**
     * 获取控件
     *
     * @param id  控件的id
     * @param <E>
     * @return
     */
    protected <E extends View> E get(int id) {
        return (E) rootView.findViewById(id);
    }

    /**
     * 隐藏判断
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }
    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (mIsFirstVisible && isResumed()) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLog(String msg) {
        Logger.i(msg);
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
}
