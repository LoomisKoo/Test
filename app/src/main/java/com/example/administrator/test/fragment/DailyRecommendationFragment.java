package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.DailyRecommendEntity;
import com.example.administrator.test.entity.view.DailyRecommendViewEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.mvp.contract.DailyRecommendContract;
import com.example.administrator.test.mvp.model.DailyRecommendModel;
import com.example.administrator.test.mvp.presenter.DailyRecommendPresenter;
import com.example.administrator.test.viewholder.PlayAndroidArticleListVH;
import com.example.administrator.test.viewholder.PlayAndroidBannerVH;
import com.like.LikeButton;
import com.like.OnLikeListener;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: DailyRecommendationFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/22 6:13 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/22 6:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyRecommendationFragment extends BaseListFragment<DailyRecommendViewEntity, DailyRecommendPresenter> implements DailyRecommendContract.View {

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getDailyRecommend();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<DailyRecommendViewEntity>(getContext(), 0) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, DailyRecommendViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case PlayAndroidViewEntity.VIEW_TYPE_BANNER:
                        ((PlayAndroidBannerVH) holder).setData((BannerEntity) item.getData());
                        break;
                    case PlayAndroidViewEntity.VIEW_TYPE_ARTICLE_LIST:
                        ArticleListEntity.DataBean.ArticleInfoBean entity = (ArticleListEntity.DataBean.ArticleInfoBean) item.getData();
                        ((PlayAndroidArticleListVH) holder).setData(entity);
                        LikeButton checkBox = holder.getView(R.id.cbCollect);

                        checkBox.setOnLikeListener(new OnLikeListener() {
                            @Override
                            public void liked(LikeButton likeButton) {
//                                collectArticle(likeButton, entity);
                            }

                            @Override
                            public void unLiked(LikeButton likeButton) {
//                                collectArticle(likeButton, entity);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case DailyRecommendViewEntity.VIEW_TYPE_BANNER:
                        PlayAndroidBannerVH bannerVH = new PlayAndroidBannerVH(getActivity(), parent, R.layout.header_play_android);
                        AnimatorHelper.setViewTouchListener(bannerVH.itemView);
                        return bannerVH;
                    case DailyRecommendViewEntity.VIEW_TYPE_MAIN_MENU:
//                        PlayAndroidArticleListVH vh = new PlayAndroidArticleListVH(getContext(), parent, R.layout.play_android_item_article);
//                        vh.itemView.setOnClickListener(v -> {
//                            int position = recyclerView.getChildAdapterPosition(v);
//
//                            PlayAndroidViewEntity                      entity = adapter.getData().get(position);
//                            ArticleListEntity.DataBean.ArticleInfoBean bean   = (ArticleListEntity.DataBean.ArticleInfoBean) entity.getData();
//                            String                                     title  = bean.getTitle();
//                            ARouter.getPlayAndroidInstance().build(ArouterHelper.ROUTE_ACTIVITY_WEB).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withString("title", title).withString("url", bean.getLink()).withInt("x", AnimatorHelper.getDownX()).withInt("y", AnimatorHelper.getDownY()).navigation(getActivity());
//                        });

//                        AnimatorHelper.setViewTouchListener(vh.itemView);
//                        return vh;
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_ARTICLE:
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_VIDEO:
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_PHOTO:
                        break;
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return getItem(position).getViewType();
            }
        };
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected DailyRecommendPresenter getPresenter() {
        return new DailyRecommendPresenter(new DailyRecommendModel(), this);
    }

    @Override
    public void onSuccess(DailyRecommendEntity entity) {
        stopRefresh();
        checkEmpty(getString(R.string.common_empty_list_load_failed), R.mipmap.ic_load_err);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
        checkEmpty(getString(R.string.common_empty_list_load_failed), R.mipmap.ic_load_err);
    }
}
