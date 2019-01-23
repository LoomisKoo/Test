package com.example.administrator.test.mvp.model;

import com.example.administrator.test.http.Api;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.http.HttpUtil;
import com.example.administrator.test.mvp.contract.ArticleListContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.model
 * @ClassName: ArticleListModel
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 1:34 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 1:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArticleListModel implements ArticleListContract.Model {
    @Override
    public void getArticleList(int page, Integer cid, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService().getArticleList(page, cid);
        Api.query(observable, httpCallback);
    }
    @Override
    public void collectArticle(int articleID, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService().collectArticle(articleID);
        Api.query(observable, httpCallback);
    }

    @Override
    public void unCollectArticle(int articleID, HttpCallback httpCallback) {
        Observable<ResponseBody> observable = Api.getPlayAndroidService().unCollectArticleOrigin(articleID);
        Api.query(observable, httpCallback);
    }

}
