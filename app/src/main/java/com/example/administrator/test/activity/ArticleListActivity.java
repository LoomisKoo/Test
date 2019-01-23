package com.example.administrator.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.mvp.contract.ArticleListContract;
import com.example.administrator.test.mvp.model.ArticleListModel;
import com.example.administrator.test.mvp.presenter.ArticleListPresenter;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.util.UserUtil;
import com.example.administrator.test.viewholder.playandroid.PlayAndroidArticleListVH2;
import com.like.LikeButton;
import com.like.OnLikeListener;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: ArticleListActivity
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 1:31 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 1:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouterHelper.ROUTE_ACTIVITY_ARTICLE_LIST)
public class ArticleListActivity extends BaseListActivity<ArticleListEntity.DataBean.ArticleInfoBean, ArticleListPresenter> implements ArticleListContract.View {
    @Autowired
    int    cid;
    @Autowired
    String title;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        showCenterTitle(true);
        setCenterTitle(title);
    }

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getArticleList(page, cid);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<ArticleListEntity.DataBean.ArticleInfoBean>(this, R.layout.play_android_item_article_2) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, ArticleListEntity.DataBean.ArticleInfoBean item, int viewType, int position) {
                ((PlayAndroidArticleListVH2) holder).setData(item);
                LikeButton checkBox = holder.getView(R.id.cbCollect);
                checkBox.setOnLikeListener(new OnLikeListener() {
                    @Override
                    public void liked(LikeButton likeButton) {
                        collectArticle(likeButton, item);
                    }

                    @Override
                    public void unLiked(LikeButton likeButton) {
                        collectArticle(likeButton, item);
                    }
                });
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                PlayAndroidArticleListVH2 vh = new PlayAndroidArticleListVH2(context, parent, R.layout.play_android_item_article_2);
                vh.itemView.setOnClickListener(v -> {
                    int position = recyclerView.getChildAdapterPosition(v);

                    ArticleListEntity.DataBean.ArticleInfoBean entity = adapter.getData().get(position);
                    String                                     url    = entity.getLink();
                    String                                     title  = entity.getTitle();
                    ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_WEB).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withString("title", title).withString("url", url).withInt("x", AnimatorHelper.getDownX()).withInt("y", AnimatorHelper.getDownY()).navigation(context);
                });

                AnimatorHelper.setViewTouchListener(vh.itemView);
                return vh;
            }
        };
    }

    @Override
    public int bindTopLayout() {
        return 0;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    protected ArticleListPresenter createPresenter() {
        return new ArticleListPresenter(new ArticleListModel(), this);
    }

    @Override
    public void onSuccess(ArticleListEntity entity) {
        stopRefresh();
        maxPage = entity.getData().getPageCount();
        for (ArticleListEntity.DataBean.ArticleInfoBean bean : entity.getData().getArticleListBean()) {
            adapter.add(bean);
        }
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
    }


    /**
     * 收藏或者取消收藏文章
     * @param checkBox
     * @param entity
     */
    private void collectArticle(LikeButton checkBox, ArticleListEntity.DataBean.ArticleInfoBean entity) {
        if (!UserUtil.isLogin(this)) {
            checkBox.setLiked(!checkBox.isLiked());
            return;
        }

        int articleID = entity.getId();
        if (entity.isCollect()) {
            presenter.unCollectArticle(articleID, new ArticleListPresenter.CallBack() {
                @Override
                public void onSuccess() {
                    ToastUtils.showShort(getString(R.string.play_android_cancel_collection_successfully));
                    entity.setCollect(false);
                    checkBox.setLiked(false);
                }

                @Override
                public void onError() {
                    ToastUtils.showShort(getString(R.string.play_android_cancel_collection_failed));
                }
            });
        }
        else {
            presenter.collectArticle(articleID, new ArticleListPresenter.CallBack() {
                @Override
                public void onSuccess() {
                    ToastUtils.showShort(getString(R.string.play_android_collection_successfully));
                    entity.setCollect(true);
                    checkBox.setLiked(true);
                }

                @Override
                public void onError() {
                    ToastUtils.showShort(getString(R.string.play_android_collection_failed));
                }
            });
        }
    }
}
