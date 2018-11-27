package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @author koo
 */
public interface GetBookContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter extends IBasePresenter {
        /**
         *
         */
        void getData();
    }
}
