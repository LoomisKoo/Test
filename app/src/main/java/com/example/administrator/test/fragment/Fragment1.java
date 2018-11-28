package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.mvp.base.IBaseModel;
import com.example.administrator.test.mvp.contract.TestContract;
import com.example.administrator.test.util.OnMultiClickListener;
import com.orhanobut.logger.Logger;

public class Fragment1 extends BaseFragment implements TestContract.View {
    Button button;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initView(View view) {

        button = view.findViewById(R.id.button2);
        button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected Object getPresenter() {
        return null;
    }

    @Override
    public void addResult(double result) {
        button.setText("" + result);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(IBaseModel model) {

    }
}
