package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.CollectionArticleListContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: CollectionArticleListPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/3/5 15:29
 * @UpdateUser:
 * @UpdateDate: 2019/3/5 15:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CollectionArticleListPresenter implements CollectionArticleListContract.Presenter {
    private CollectionArticleListContract.Model model;
    private CollectionArticleListContract.View  view;

    public CollectionArticleListPresenter(CollectionArticleListContract.Model model, CollectionArticleListContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getCollectionArticleList(int page) {
        model.getCollectionArticleList(page, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                ArticleListEntity entity = null;
                String            s;
                try {
                    s = result.string();
                    entity = JSON.parseObject(s, ArticleListEntity.class);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    view.onGetDataError("请求失败");
                }
                view.onGetDataSuccess(entity);
            }

            @Override
            public void onError(String msg) {
                view.onGetDataError(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void unCollectArticleList(int articleID, int originId) {
        model.unCollectArticle(articleID, originId, new HttpCallback() {
            @Override
            public void onSuccess(Object result) {
                view.onUnCollectSuccess();
            }

            @Override
            public void onError(String msg) {
                view.onUnCollectFail(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
