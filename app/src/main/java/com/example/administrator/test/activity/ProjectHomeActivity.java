package com.example.administrator.test.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseAnimationActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouteHelper;

import androidx.appcompat.widget.Toolbar;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: ProjectHomeActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/18 5:33 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/18 5:33 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_PROJECT_HOME)
public class ProjectHomeActivity extends BaseAnimationActivity {
    private Toolbar mToolbar;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.project_home_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setStatusBarFullTransparent();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void setListener() {
        mToolbar.setNavigationOnClickListener(v -> finishActivity());
    }

    @Override
    public void initData(Context mContext) {

    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        //高于Android5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView()
                  .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                                 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        //高于Android 4.4
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
