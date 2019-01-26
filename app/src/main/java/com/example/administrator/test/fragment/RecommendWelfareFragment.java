package com.example.administrator.test.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.RecommendWelfareEntity;
import com.example.administrator.test.mvp.contract.RecommendWelfareContract;
import com.example.administrator.test.mvp.model.RecommendWelfareModel;
import com.example.administrator.test.mvp.presenter.RecommendWelfarePresenter;
import com.example.administrator.test.viewholder.recommend.WelfareVH;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendWelfareFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:48 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendWelfareFragment extends BaseListFragment<RecommendWelfareEntity.WelfareBean, RecommendWelfarePresenter> implements RecommendWelfareContract.View {
    private static final int GRID_LAYOUT_ROWS = 2;

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getWelfare("福利", pageSize, page);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<RecommendWelfareEntity.WelfareBean>(getContext(), R.layout.recommend_welfare_vh_item) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, RecommendWelfareEntity.WelfareBean item, int viewType, int position) {
                ((WelfareVH) holder).setData(item.getUrl());
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new GridLayoutHelper(GRID_LAYOUT_ROWS);
//                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new WelfareVH(context, parent, R.layout.recommend_welfare_vh_item);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemCount() {
                return data.size();
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
    protected RecommendWelfarePresenter getPresenter() {
        return new RecommendWelfarePresenter(new RecommendWelfareModel(), this);
    }


    @Override
    public void onSuccess(List<RecommendWelfareEntity.WelfareBean> entity) {
        stopRefresh();
        //此处不用adapter.addAll(entity) 因为这样recyclerview会整体刷新，导致闪烁的效果
        for (RecommendWelfareEntity.WelfareBean data : entity) {
            adapter.add(data);
        }

    }

    @Override
    public void onError(String msg) {
        stopRefresh();
    }
}
