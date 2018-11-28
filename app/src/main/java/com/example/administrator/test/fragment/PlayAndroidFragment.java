package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.http.HttpRequestType;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;
import com.example.administrator.test.mvp.model.PlayAndroidModel;
import com.example.administrator.test.mvp.presenter.PlayAndroidPresenter;
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
public class PlayAndroidFragment extends BaseListFragment implements PlayAndroidContract.View {
    private PlayAndroidPresenter playAndroidPresenter;

    @Override
    protected void getData(int page, int pageSize) {
        playAndroidPresenter.getBannerImg();
        playAndroidPresenter.getArticleList();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<PlayAndroidViewEntity>(getContext(), R.layout.header_play_android) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, PlayAndroidViewEntity item, int viewType, int position) {
                ((PlayAndroidBannerViewHolder) holder).setData(item);
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
        playAndroidPresenter = new PlayAndroidPresenter(this, new PlayAndroidModel());
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
        ArrayList<PlayAndroidViewEntity> playAndroidViewEntities = new ArrayList<>();
        playAndroidViewEntities.add(playAndroidViewEntity);
        adapter.replaceAll(playAndroidViewEntities);
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
}
