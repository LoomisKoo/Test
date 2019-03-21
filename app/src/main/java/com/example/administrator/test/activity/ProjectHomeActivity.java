package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.base.activity.BaseAnimationActivity;
import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.util.ArouteHelper;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.project_home_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BarUtils.setStatusBarAlpha(this, 0);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this)
                   .unbind();
        super.onDestroy();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void setListener() {
        toolbar.setNavigationOnClickListener(v -> finishActivity());
    }

    @Override
    public void initData(Context mContext) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
