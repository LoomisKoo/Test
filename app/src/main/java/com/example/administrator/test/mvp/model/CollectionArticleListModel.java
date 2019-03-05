package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.CollectionArticleListContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: CollectionArticleListModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/3/5 15:29
 * @UpdateUser:
 * @UpdateDate: 2019/3/5 15:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CollectionArticleListModel implements CollectionArticleListContract.Model {
    @Override
    public void getCollectionArticleList(int page, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService()
                                                 .getCollectionArticleList(page);
        Api.query(observable, httpCallback);
    }

    @Override
    public void unCollectArticle(int articleID, int originId, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService()
                                                 .unCollectArticleList(articleID, originId);
        Api.query(observable, httpCallback);
    }
}
