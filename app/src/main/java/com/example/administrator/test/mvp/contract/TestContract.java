package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.mvp.base.IBaseLifecycleI;

/**
 * @author koo
 */
public interface TestContract {
    interface Model {
        double add(double a, double b);
    }

    interface View {
        void addResult(double result);
    }

    interface Presenter extends IBaseLifecycleI {
        void calculate(double a, double b);
    }
}
