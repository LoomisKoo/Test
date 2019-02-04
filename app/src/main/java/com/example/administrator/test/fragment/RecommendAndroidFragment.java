package com.example.administrator.test.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.mvp.contract.RecommendCustomContract;
import com.example.administrator.test.mvp.model.RecommendCustomModel;
import com.example.administrator.test.mvp.presenter.RecommendCustomPresenter;
import com.example.administrator.test.viewholder.recommend.CustomArticleVH;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendAndroidFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:48 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendAndroidFragment extends BaseListFragment<BaseViewEntity, RecommendCustomPresenter> implements RecommendCustomContract.View {

    @Override
    protected void getData(int page, int pageSize) {
        //该api是page是从1开始
        page++;
        //请求文章列表数据
        presenter.getCustomData("Android", pageSize, page);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<BaseViewEntity>(getContext(), R.layout.recommend_welfare_vh_item) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, BaseViewEntity item, int viewType, int position) {
                if (item.getData() instanceof RecommendCustomEntity.CustomInfoEntity) {
                    ((CustomArticleVH) holder).setData((RecommendCustomEntity.CustomInfoEntity) item.getData());
                }
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new CustomArticleVH(context, parent, R.layout.recommend_custom_article_vh);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemViewType(int position) {
                return data.get(position)
                           .getViewType();
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
    protected RecommendCustomPresenter getPresenter() {
        return new RecommendCustomPresenter(new RecommendCustomModel(), this);
    }

    @Override
    public void onSuccess(BaseViewEntity entity) {
        stopRefresh();
        adapter.add(entity);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
        showToast(msg);
    }
}
