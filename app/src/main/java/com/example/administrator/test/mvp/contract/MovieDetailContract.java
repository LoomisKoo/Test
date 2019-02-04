package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: MovieDetailContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 2:38 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 2:38 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MovieDetailContract {
    interface Model {
        void getMovieDetail(String movieID, HttpCallback callback);
    }

    interface View {
        void onLoadSuccess(MovieDetailEntity entity);

        void onLoadFailed(String msg);
    }

    interface Presenter extends IBasePresenter {
        void getMovieDetail(String movieID);
    }
}
