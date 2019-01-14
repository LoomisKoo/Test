package com.example.administrator.test.mvp.presenter;

import com.alibaba.fastjson.JSON;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.http.HttpCallback;
import com.example.administrator.test.mvp.contract.ArticleListContract;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.mvp.presenter
 * @ClassName: ArticleListPresenter
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 1:34 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 1:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArticleListPresenter implements ArticleListContract.Presenter {
    private ArticleListContract.Model model;
    private ArticleListContract.View  view;

    public ArticleListPresenter(ArticleListContract.Model model, ArticleListContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getArticleList(int page, Integer cid) {
        model.getArticleList(page, cid, new HttpCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                try {
                    ArticleListEntity entity = JSON.parseObject(result.string(), ArticleListEntity.class);
                    if (null == entity || -1 == entity.getErrorCode()) {
                        view.onError("获取数据失败");
                    }
                    else {
                        view.onSuccess(entity);
                    }
                }
                catch (IOException e) {
                    view.onError("获取数据失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {
                view.onError(msg);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void collectArticle(int articleID) {

    }

    @Override
    public void unCollectArticle(int articleID) {

    }
}
