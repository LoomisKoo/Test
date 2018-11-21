package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.mvp.base.IBasePresenter;
import com.example.administrator.test.mvp.base.IBaseView;

/**
 * @author koo
 */
public interface TestContract {
    interface Model {
        double add(double a, double b);
    }

    interface View extends IBaseView {
        void addResult(double result);
    }

    interface Presenter extends IBasePresenter {
        void calculate(double a, double b);
    }
}
