package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.mvp.contract.TestContract;
import com.example.administrator.test.mvp.model.TestModel;
import com.example.administrator.test.mvp.presenter.TestPresenter;
import com.example.administrator.test.util.OnMultiClickListener;
import com.orhanobut.logger.Logger;

public class Fragment1 extends BaseFragment implements TestContract.View {
    private TestContract.Presenter presenter;
    private TestModel model;
    Button button;

    @Override
    protected int setView() {
        return R.layout.fragment_1;
    }

    @Override
    protected void init(View view) {
        model = new TestModel();
        presenter = new TestPresenter(model, this);
        button = view.findViewById(R.id.button2);
        button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                String userName = "test----------------------------------------------------------";
                Logger.i(userName);
                presenter.calculate(1, 100);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addResult(double result) {
        button.setText("" + result);
    }
}
