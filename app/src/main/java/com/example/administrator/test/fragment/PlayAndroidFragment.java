package com.example.administrator.test.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.activity.WebActivity;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.LoginEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpRequestType;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;
import com.example.administrator.test.mvp.model.PlayAndroidModel;
import com.example.administrator.test.mvp.presenter.PlayAndroidPresenter;
import com.example.administrator.test.util.ACache;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.util.UserUtil;
import com.example.administrator.test.viewholder.PlayAndroidArticleListVH;
import com.example.administrator.test.viewholder.PlayAndroidBannerViewHolder;

import java.util.ArrayList;


/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: PlayAndroidFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/27 2:28 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/27 2:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidFragment extends BaseListFragment<PlayAndroidViewEntity, PlayAndroidPresenter> implements PlayAndroidContract.View {


    @Override
    protected void getData(int page, int pageSize) {
        if (1 == page) {
            presenter.getBannerImg();
        }
        presenter.getArticleList(page);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<PlayAndroidViewEntity>(getContext(), R.layout.header_play_android) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, PlayAndroidViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case HttpRequestType.REQUEST_TYPE_BANNER:
                        ((PlayAndroidBannerViewHolder) holder).setData((BannerEntity) item.getData());
                        break;
                    case HttpRequestType.REQUEST_TYPE_ARTICLE_LIST:
                        ((PlayAndroidArticleListVH) holder).setData((ArticleListEntity.DataBean.ArticleInfoBean) item.getData());
                        CheckBox checkBox = holder.getView(R.id.cbCollect);
                        checkBox.setOnClickListener(v -> {

                            ArticleListEntity.DataBean.ArticleInfoBean bean = (ArticleListEntity.DataBean.ArticleInfoBean) item.getData();
                            if (!UserUtil.isLogin(context)) {
                                checkBox.setChecked(!checkBox.isChecked());
                                return;
                            }


                            int articleID = bean.getId();
                            if (bean.isCollect()) {
                                presenter.unCollectArticle(articleID, new PlayAndroidPresenter.CallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ToastUtils.showShort(getString(R.string.play_android_cancel_collection_successfully));
                                        bean.setCollect(false);
                                    }

                                    @Override
                                    public void onError() {
                                        ToastUtils.showShort(getString(R.string.play_android_cancel_collection_failed));
                                    }
                                });
                            }
                            else {
                                presenter.collectArticle(articleID, new PlayAndroidPresenter.CallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ToastUtils.showShort(getString(R.string.play_android_collection_successfully));
                                        bean.setCollect(true);
                                    }

                                    @Override
                                    public void onError() {
                                        ToastUtils.showShort(getString(R.string.play_android_collection_failed));
                                    }
                                });
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
                    case HttpRequestType.REQUEST_TYPE_BANNER:
                        PlayAndroidBannerViewHolder bannerVH = new PlayAndroidBannerViewHolder(getActivity(), parent, R.layout.header_play_android);
                        AnimatorHelper.setViewTouchListener(bannerVH.itemView);
                        return bannerVH;
                    case HttpRequestType.REQUEST_TYPE_ARTICLE_LIST:
                        PlayAndroidArticleListVH vh = new PlayAndroidArticleListVH(getContext(), parent, R.layout.play_android_item_article);
                        vh.itemView.setOnClickListener(v -> {
                            int position = recyclerView.getChildAdapterPosition(v);

                            PlayAndroidViewEntity entity = adapter.getData().get(position);
                            if (entity.getViewType() == HttpRequestType.REQUEST_TYPE_ARTICLE_LIST) {
                                ArticleListEntity.DataBean.ArticleInfoBean bean  = (ArticleListEntity.DataBean.ArticleInfoBean) entity.getData();
                                String                                     title = bean.getTitle();
                                ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_WEB).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withString("title", title).withString("url", bean.getLink()).withInt("x", AnimatorHelper.getDownX()).withInt("y", AnimatorHelper.getDownY()).navigation(getActivity());
                            }
                        });

                        AnimatorHelper.setViewTouchListener(vh.itemView);
                        return vh;
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
    protected PlayAndroidPresenter getPresenter() {
        return new PlayAndroidPresenter(this, new PlayAndroidModel());
    }

    @Override
    public void showLoading() {
//        refresh();
    }

    @Override
    public void hideLoading() {
        stopRefresh();
    }

    @Override
    public void onSuccess(PlayAndroidViewEntity playAndroidViewEntity) {
        stopRefresh();
        ArrayList<PlayAndroidViewEntity> playAndroidViewEntities = new ArrayList<>();
        playAndroidViewEntities.add(playAndroidViewEntity);
        adapter.addAll(playAndroidViewEntities);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
        checkEmpty(getString(R.string.common_empty_list_load_failed), R.mipmap.ic_load_err);
    }

    @Override
    public void onComplete() {
        stopRefresh();
        checkEmpty(getString(R.string.common_empty_list_load_failed), R.mipmap.ic_load_err);
    }
}
