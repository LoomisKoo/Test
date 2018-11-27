package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.mvp.contract.PlayAndroidContract;
import com.example.administrator.test.mvp.model.PlayAndroidModel;
import com.example.administrator.test.mvp.presenter.PlayAndroidPresenter;
import com.example.administrator.test.viewholder.PlayAndoidViewHolder;

import java.io.IOException;

import okhttp3.ResponseBody;

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
    protected int setContentLayout() {
        return R.layout.fragment_play_android;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData(int page, int pageSize) {

    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter(getContext(), 0) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, Object item, int viewType, int position) {

            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new PlayAndoidViewHolder(getContext(), parent, R.layout.fragment_base_list);
            }

        };
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        playAndroidPresenter = new PlayAndroidPresenter(this, new PlayAndroidModel());
        playAndroidPresenter.getBannerImg();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        BannerEntity bannerEntity = null;
        try {
            bannerEntity = JSON.parseObject(responseBody.string(), BannerEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != bannerEntity) {
            System.out.println(bannerEntity.getData());
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onComplete() {

    }

}
