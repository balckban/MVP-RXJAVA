package com.idogs.budejie.budejie.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.util.ArrayList;


/**
 * 主界面Fragment控制器
 */
public class FragmentController {

    private int containerId;
    private FragmentManager fm;  //fragment管理
    private ArrayList<Fragment> fragments; //fragment 集合

    private static FragmentController controller;
    private static boolean isReload;

    public static FragmentController getInstance(FragmentActivity activity, int containerId, boolean isReload) {
        FragmentController.isReload = isReload;
        if (controller == null) {
            controller = new FragmentController(activity, containerId);
        }
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    private FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        if (isReload) {

            fragments.add(new HomeFragment());
            fragments.add(new NewFragment());
            fragments.add(new NewFragment()); //此处为占位 中间Fragment占位
            fragments.add(new CharFragment());
            fragments.add(new MeFragment());

            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                ft.add(containerId, fragments.get(i), "" + i);
            }
            ft.commit();

        } else {
            for (int i = 0; i < 5; i++) {
                fragments.add( fm.findFragmentByTag(i+""));
            }
        }
    }

    /**
     * 显示fragment
     * @param position
     */
    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     */
    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 获取当前fragment位置
     * @param position
     * @return
     */
    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}