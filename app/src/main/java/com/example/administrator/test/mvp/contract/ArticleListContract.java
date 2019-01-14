package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: ArticleListContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 1:34 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 1:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ArticleListContract {
    interface Model {
        void getArticleList(int page, Integer cid, HttpCallback httpCallback);

        void collectArticle(int articleID, HttpCallback httpCallback);

        void unCollectArticle(int articleID, HttpCallback httpCallback);
    }

    interface View {
        /**
         *
         */
        void onSuccess(ArticleListEntity entity);

        void onError(String msg);
    }

    interface Presenter extends IBasePresenter {
        void getArticleList(int page, Integer cid);

        void collectArticle(int articleID);

        void unCollectArticle(int articleID);
    }
}
