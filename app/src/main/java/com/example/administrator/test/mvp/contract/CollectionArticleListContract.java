package com.example.administrator.test.mvp.contract;

import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.base.IBasePresenter;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.contract
 * @ClassName: CollectionArticleListContract
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/3/5 15:29
 * @UpdateUser:
 * @UpdateDate: 2019/3/5 15:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface CollectionArticleListContract {
    interface Model {
        void getCollectionArticleList(int page, HttpCallback httpCallback);

        void unCollectArticle(int articleID, int originId, HttpCallback httpCallback);
    }

    interface View {
        void onGetDataSuccess(ArticleListEntity entity);

        void onGetDataError(String msg);

        void onUnCollectSuccess();

        void onUnCollectFail(String msg);
    }

    interface Presenter extends IBasePresenter {
        void getCollectionArticleList(int page);

        void unCollectArticleList(int articleID, int originId);
    }
}
