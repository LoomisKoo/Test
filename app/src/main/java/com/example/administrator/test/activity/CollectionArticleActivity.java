package com.example.administrator.test.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.activity.BaseListActivity;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.mvp.contract.CollectionArticleListContract;
import com.example.administrator.test.mvp.model.CollectionArticleListModel;
import com.example.administrator.test.mvp.presenter.CollectionArticleListPresenter;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.util.UserUtil;
import com.example.administrator.test.viewholder.playandroid.PlayAndroidArticleListVH;
import com.like.LikeButton;
import com.like.OnLikeListener;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.activity
 * @ClassName: CollectionArticleActivity
 * @Description: java类作用描述
 * @Author: koom
 * @CreateDate: 2019/3/5 15:21
 * @UpdateUser:
 * @UpdateDate: 2019/3/5 15:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Route(path = ArouteHelper.ROUTE_ACTIVITY_COLLECTION_ARTICLE_LIST)
public class CollectionArticleActivity extends BaseListActivity<ArticleListEntity.DataBean.ArticleInfoBean, CollectionArticleListPresenter> implements CollectionArticleListContract.View {
    /**
     * 即将删除的文章的position
     */
    private int unCollectPosition = 0;

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getCollectionArticleList(page);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<ArticleListEntity.DataBean.ArticleInfoBean>(this, 0) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, ArticleListEntity.DataBean.ArticleInfoBean item, int viewType, int position) {
                ((PlayAndroidArticleListVH) holder).setData(item);
                LikeButton checkBox = holder.getView(R.id.cbCollect);
                checkBox.setLiked(true);
                checkBox.setOnLikeListener(new OnLikeListener() {
                    @Override
                    public void liked(LikeButton likeButton) {
                    }

                    @Override
                    public void unLiked(LikeButton likeButton) {
                        unCollectPosition = position;
                        unCollectArticle(likeButton, item);
                    }
                });

            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                PlayAndroidArticleListVH vh = new PlayAndroidArticleListVH(CollectionArticleActivity.this, parent, R.layout.play_android_item_article);
                vh.itemView.setOnClickListener(v -> {
                    int                                        position = basePagerListRv.getChildAdapterPosition(v);
                    ArticleListEntity.DataBean.ArticleInfoBean bean     = adapter.getItem(position);
                    String                                     title    = bean.getTitle();
                    ArouteHelper.buildWebWithAnimator(CollectionArticleActivity.this, title, bean.getLink());
                });

                AnimatorHelper.setViewTouchListener(vh.itemView);
                return vh;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showCenterTitle(true);
        setCenterTitle("我的收藏");
        super.onCreate(savedInstanceState);
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
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    protected CollectionArticleListPresenter createPresenter() {
        return new CollectionArticleListPresenter(new CollectionArticleListModel(), this);
    }

    @Override
    public void onGetDataSuccess(ArticleListEntity entity) {
        stopRefresh();
        adapter.addAll(entity.getData()
                             .getArticleListBean());
    }

    @Override
    public void onGetDataError(String msg) {
        stopRefresh();
        showToast(msg);
    }

    @Override
    public void onUnCollectSuccess() {
        ToastUtils.showShort(getString(R.string.play_android_cancel_collection_successfully));
        adapter.remove(unCollectPosition);
        adapter.notifyItemChanged(unCollectPosition);
    }

    @Override
    public void onUnCollectFail(String msg) {
        ToastUtils.showShort(getString(R.string.play_android_cancel_collection_failed));
    }

    /**
     * 取消收藏文章
     *
     * @param checkBox
     * @param entity
     */
    private void unCollectArticle(LikeButton checkBox, ArticleListEntity.DataBean.ArticleInfoBean entity) {
        if (!UserUtil.isLogin(this)) {
            checkBox.setLiked(!checkBox.isLiked());
            return;
        }
        presenter.unCollectArticleList(entity.getId(), entity.getOriginId());
    }
}
