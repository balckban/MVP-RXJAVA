package com.idogs.budejie.budejie.ui.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idogs.budejie.budejie.R;
import com.idogs.budejie.budejie.base.BaseMvpFragment;
import com.idogs.budejie.budejie.presenter.HomePresenter;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter>{

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_layout, container,false);
    }

    @Override
    protected void bindViews(View view) {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void setListener() {

    }
}
