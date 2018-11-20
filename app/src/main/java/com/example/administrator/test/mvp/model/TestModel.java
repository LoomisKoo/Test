package com.example.administrator.test.mvp.model;

import com.example.administrator.test.mvp.contract.TestContract;

public class TestModel implements TestContract.Model {

    @Override
    public double add(double a, double b) {
        return a + b;
    }
}
