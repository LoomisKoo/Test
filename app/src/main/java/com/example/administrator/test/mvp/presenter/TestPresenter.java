package com.example.administrator.test.mvp.presenter;

import com.example.administrator.test.mvp.contract.TestContract;

/**
 * @author koo
 */
public class TestPresenter implements TestContract.Presenter {
    private TestContract.Model model;
    private TestContract.View view;

    public TestPresenter(TestContract.Model model, TestContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void calculate(double a, double b) {
        view.addResult(model.add(a, b));
    }
}
