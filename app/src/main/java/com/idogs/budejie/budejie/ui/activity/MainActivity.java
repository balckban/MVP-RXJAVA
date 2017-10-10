package com.idogs.budejie.budejie.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idogs.budejie.budejie.R;
import com.idogs.budejie.budejie.base.BaseActivity;
import com.idogs.budejie.budejie.ui.fragment.FragmentController;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity{

    @BindView(R.id.content_layout)
    RelativeLayout content_layout;   //fragment容器
    @BindView(R.id.llBottom)
    LinearLayout llBottom;

    @BindView(R.id.home_img)
    ImageView home_img; //home图片
    @BindView(R.id.home_tv)
    TextView home_tv; //home文字
    @BindView(R.id.new_img)
    ImageView new_img; //new图片
    @BindView(R.id.new_tv)
    TextView new_tv; //new文字
    @BindView(R.id.msg_img)
    ImageView msg_img; //chat图片
    @BindView(R.id.msg_tv)
    TextView msg_tv; //chat文字
    @BindView(R.id.person_img)
    ImageView person_img; //me图片
    @BindView(R.id.person_tv)
    TextView person_tv; //me文字
    private FragmentController mController;



    @Override
    protected void loadViewLayout() {
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);
    }

    @Override
    protected void bindViews() {

    }

    /**
     * 初始化fragment控制器
     * @param savedInstanceState
     */
    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mController = FragmentController.getInstance(this, R.id.content_layout, true);
        mController.showFragment(0);  //显示选中第一个
    }

    //最新选中的fragment
    private View lastSelectedIcon;
    private View lastSelectedText;
        @Override
        protected void setListener() {
            for (int i = 0; i < 5; i++) {
                if (i == 0) {
                    //默认选中首页
                    setSelectIcon(home_img, home_tv);
                }
                final int position = i;
                llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断是否是中间的layout
                        if (position ==2) {

                        }else {
                            if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                            if (lastSelectedText != null) lastSelectedText.setSelected(false);

                            LinearLayout rl = (LinearLayout) v;
                            ImageView icon = (ImageView) rl.getChildAt(0);
                            TextView text = (TextView) rl.getChildAt(1);
                            mController.showFragment(position);
                            setSelectIcon(icon, text);
                        }
                }
                });
            }
        }
    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();
    }
}
