package com.example.administrator.test.interpolator;

import android.view.animation.LinearInterpolator;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.interpolator
 * @ClassName: LoginInterpolator
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/8 1:52 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/8 1:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginInterpolator extends LinearInterpolator {
    private float factor;

    public LoginInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}