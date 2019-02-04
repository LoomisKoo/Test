package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.MovieTop250Entity;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.http.HttpCallback;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: MovieUpcomingContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 1:20 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 1:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface MovieTop250Contract {
    interface Model {
        void loadData(int start, int count, HttpCallback callback);
    }

    interface View {
        void onLoadSuccess(MovieTop250Entity entity);

        void onLoadFailed(String msg);
    }

    interface Presenter {
        void loadData(int start, int count);
    }
}
