package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
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
import com.example.administrator.test.viewholder.PlayAndroidHeadViewHolder;

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
        stopRefresh();
        playAndroidPresenter = new PlayAndroidPresenter(this, new PlayAndroidModel());
        playAndroidPresenter.getBannerImg();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<PlayAndroidViewEntity>(getContext(), R.layout.header_play_android) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, PlayAndroidViewEntity item, int viewType, int position) {
                holder.setData(item);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case HttpRequestType.REQUEST_TYPE_BANNER:
                        return new PlayAndroidHeadViewHolder(getContext(), parent, R.layout.header_play_android);
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(PlayAndroidViewEntity playAndroidViewEntity) {

        Message message = handler.obtainMessage();
        message.obj = playAndroidViewEntity;
        handler.sendMessage(message);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onComplete() {

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PlayAndroidViewEntity playAndroidViewEntity = (PlayAndroidViewEntity) msg.obj;
            ArrayList<PlayAndroidViewEntity> playAndroidViewEntities = new ArrayList<>();
            playAndroidViewEntities.add(playAndroidViewEntity);

            adapter.replaceAll(playAndroidViewEntities);
        }
    };

}
