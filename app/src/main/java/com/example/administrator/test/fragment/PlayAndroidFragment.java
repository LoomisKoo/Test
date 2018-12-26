package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.ArticleListEntity;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpRequestType;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;
import com.example.administrator.test.mvp.model.PlayAndroidModel;
import com.example.administrator.test.mvp.presenter.PlayAndroidPresenter;
import com.example.administrator.test.util.ArouterHelper;
import com.example.administrator.test.viewholder.PlayAndroidArticleListVH;
import com.example.administrator.test.viewholder.PlayAndroidBannerViewHolder;

import java.util.ArrayList;
import java.util.List;


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
        adapter.clear();
        presenter.getBannerImg();
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
                    case HttpRequestType.REQUEST_TYPE_ARTICEL_LIST:
                        ((PlayAndroidArticleListVH) holder).setData((ArticleListEntity.DataBean.ArticleInfoBean) item.getData());
                        holder.setOnClickListener(R.id.cbCollect, v -> {
                            ArticleListEntity.DataBean.ArticleInfoBean bean = (ArticleListEntity.DataBean.ArticleInfoBean) item.getData();
                            if (bean.isCollect()) {
                                presenter.unCollectArticle(bean.getId(), new PlayAndroidPresenter.CallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ToastUtils.showShort("取消收藏成功");
                                        bean.setCollect(false);
                                    }

                                    @Override
                                    public void onError() {
                                        ToastUtils.showShort("取消收藏失败");
                                    }
                                });
                            }
                            else {
                                presenter.collectArticle(bean.getId(), new PlayAndroidPresenter.CallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ToastUtils.showShort("收藏成功");
                                        bean.setCollect(true);
                                    }

                                    @Override
                                    public void onError() {
                                        ToastUtils.showShort("收藏失败");
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
                        return new PlayAndroidBannerViewHolder(getContext(), parent, R.layout.header_play_android);
                    case HttpRequestType.REQUEST_TYPE_ARTICEL_LIST:
                        PlayAndroidArticleListVH vh = new PlayAndroidArticleListVH(getContext(), parent, R.layout.play_android_item_article);
                        vh.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int position = recyclerView.getChildAdapterPosition(v);

                                PlayAndroidViewEntity entity = adapter.getData().get(position);
                                if (entity.getViewType() == HttpRequestType.REQUEST_TYPE_ARTICEL_LIST) {
                                    ArticleListEntity.DataBean.ArticleInfoBean bean = (ArticleListEntity.DataBean.ArticleInfoBean) entity.getData();
                                    ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_WEB).withString("title", "TestTitle").withString("url", bean.getLink()).navigation();
                                }
                            }
                        });
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
        }

                ;
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
        int viewType = playAndroidViewEntity.getViewType();
        switch (viewType) {
            case HttpRequestType.REQUEST_TYPE_BANNER:
                //先加载完banner数据再加载文章数据
                presenter.getArticleList(0);
                break;
            default:
                break;
        }
        ArrayList<PlayAndroidViewEntity> playAndroidViewEntities = new ArrayList<>();
        playAndroidViewEntities.add(playAndroidViewEntity);
        adapter.addAll(playAndroidViewEntities);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
        checkEmpty("加载失败，请拉下重试！", R.mipmap.ic_load_err);
    }

    @Override
    public void onComplete() {
        stopRefresh();
        checkEmpty("加载失败，请拉下重试！", R.mipmap.ic_load_err);
    }

    @Override
    public void onCollectSuccess() {

    }

    @Override
    public void onCollectFails() {

    }

    @Override
    public void onUnCollectSuccess() {

    }

    @Override
    public void onUnCollectFails() {

    }
}
