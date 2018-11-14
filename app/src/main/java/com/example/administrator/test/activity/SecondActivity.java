package com.example.administrator.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.base.BaseActivity;

/**
 * @author
 */

@Route(path = "/com/Activity1")
public class SecondActivity extends BaseActivity {
    private ImageView imageView;
    @Override
    public void widgetClick(View v) {

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
        return R.layout.activity_second;
    }

    @Override
    public int bindMenu() {
        return R.menu.base_toolbar_menu;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onMenuClickListener(int menuId) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
