package com.idogs.budejie.budejie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by songjiaqi on 2017/9/16 0016.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mvpPresenter == null) mvpPresenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 数据懒加载
     */
    @Override
    protected void lazyLoad() {
        if (mvpPresenter == null) mvpPresenter = createPresenter();
        super.lazyLoad();
    }


    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        mvpPresenter.detachView();
        super.onDestroyView();
    }

}
