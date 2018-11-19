package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.util.OnMultiClickListener;
import com.orhanobut.logger.Logger;

public class Fragment1 extends BaseFragment {
    @Override
    protected int setView() {
        return R.layout.fragment_1;
    }

    @Override
    protected void init(View view) {
        view.findViewById(R.id.button2).setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                String userName = "test----------------------------------------------------------";
                Logger.i(userName);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
