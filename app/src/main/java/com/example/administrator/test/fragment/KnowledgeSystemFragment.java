package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.KnowledgeSystemEntity;
import com.example.administrator.test.mvp.contract.KnowledgeSystemContract;
import com.example.administrator.test.mvp.model.KnowledgeSystemModel;
import com.example.administrator.test.mvp.presenter.KnowledgeSystemPresenter;
import com.example.administrator.test.viewholder.KnowledgeSystemVH;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: KnowledgeSystemFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/13 4:31 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/13 4:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KnowledgeSystemFragment extends BaseListFragment<KnowledgeSystemEntity.KnowledgeType, KnowledgeSystemPresenter> implements KnowledgeSystemContract.View {
    @Override
    protected void getData(int page, int pageSize) {
        presenter.loadData();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<KnowledgeSystemEntity.KnowledgeType>(getContext(), R.layout.fragment_knowledge_system) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, KnowledgeSystemEntity.KnowledgeType item, int viewType, int position) {
                ((KnowledgeSystemVH) holder).setData(item);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new KnowledgeSystemVH(getContext(), parent, R.layout.fragment_knowledge_system);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        setRefreshEnable(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected KnowledgeSystemPresenter getPresenter() {
        return new KnowledgeSystemPresenter(new KnowledgeSystemModel(), this);
    }

    @Override
    public void loadDataSuccess(KnowledgeSystemEntity entity) {
        stopRefresh();
        adapter.addAll(entity.getData());
    }

    @Override
    public void loadDataFail(String msg) {
        stopRefresh();
        showToast(msg);
    }
}
