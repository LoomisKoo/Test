package com.example.administrator.test.mvp.contract;

public interface TestContract {
    interface Model {
        double add(double a,double b);
    }

    interface View {
        void addResult(double result);
    }

    interface Presenter {
         void calculate(double a,double b);
    }
}
