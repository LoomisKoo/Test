package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.mvp.base.IBaseModel;
import com.example.administrator.test.mvp.contract.TestContract;
import com.example.administrator.test.util.OnMultiClickListener;
import com.example.administrator.test.weidge.CustomTextView;
import com.orhanobut.logger.Logger;

public class Fragment1 extends BaseFragment implements TestContract.View {
    Button button;
    private CustomTextView customTextView;

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
//        customTextView = view.findViewById(R.id.customTextView);
//        initView();

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

    private void initView() {
        customTextView.setLeftImg(getContext().getResources().getDrawable(R.mipmap.ic_launcher_round))
                      .setRightImg(getContext().getResources().getDrawable(R.mipmap.ic_launcher))
                      .setLeftTv("代码添加", "#c95fdc")
                      .setRightTv("代码添加", "#4be1c3")
                      .setCenterTv("代码", null)
                      .setLeftTopTv("上", null)
                      .setLeftBottomTv("下", null)
                      .setBottomLine("#1587e7")
                      .setCustomTvBackground("#4dacff");
        customTextView.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener() {
            @Override
            public void OnLeftImgClick() {
                Toast.makeText(getContext(), "左边图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnRightImgClick() {
                Toast.makeText(getContext(), "右边图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnTextViewClick() {
                Toast.makeText(getContext(), "布局", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
