package com.example.administrator.test.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.test.R;
import com.example.administrator.test.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public int bindMenu() {
        return R.menu.base_toolbar_menu;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {
        switch (menuId) {
            case R.id.action_search:
                showToast("search");
                break;
            case R.id.action_notification:
                showToast("Notification");
                break;

            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
