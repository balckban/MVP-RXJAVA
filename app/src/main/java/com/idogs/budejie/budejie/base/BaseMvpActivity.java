package com.idogs.budejie.budejie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }
    protected abstract P createPresenter();

    @Override
    protected void loadViewLayout() {

    }

    @Override
    protected void bindViews() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
