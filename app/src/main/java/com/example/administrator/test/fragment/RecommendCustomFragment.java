package com.example.administrator.test.fragment;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.entity.view.RecommendCustomViewEntity;
import com.example.administrator.test.mvp.contract.RecommendCustomContract;
import com.example.administrator.test.mvp.contract.RecommendWelfareContract;
import com.example.administrator.test.mvp.model.RecommendCustomModel;
import com.example.administrator.test.mvp.model.RecommendWelfareModel;
import com.example.administrator.test.mvp.presenter.RecommendCustomPresenter;
import com.example.administrator.test.mvp.presenter.RecommendWelfarePresenter;
import com.example.administrator.test.viewholder.recommend.CustomArticleVH;
import com.example.administrator.test.viewholder.recommend.CustomTitleVH;
import com.example.administrator.test.viewholder.recommend.WelfareVH;
import com.example.administrator.test.widget.imgViewPager.GlideSimpleLoader;
import com.example.administrator.test.widget.imgViewPager.ImageWatcherHelper;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendCustomFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:48 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomFragment extends BaseListFragment<RecommendCustomViewEntity, RecommendCustomPresenter> implements RecommendCustomContract.View {
    private String dataType = "all";

    BoomMenuButton menuButton;

    @Override
    protected void getData(int page, int pageSize) {
        if (0 == page) {
            //加入title布局，不需要数据
            RecommendCustomViewEntity entity = new RecommendCustomViewEntity(null, RecommendCustomViewEntity.VIEW_TYPE_TITLE);
            adapter.add(entity);
        }
        //该api是page是从1开始
        page++;
        //请求文章列表数据
        presenter.getCustomData(dataType, pageSize, page);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        menuButton = new BoomMenuButton(getContext());
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<RecommendCustomViewEntity>(getContext(), R.layout.recommend_welfare_vh_item) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, RecommendCustomViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case RecommendCustomViewEntity.VIEW_TYPE_TITLE:
                        ((CustomTitleVH) holder).setData();
                        break;
                    case RecommendCustomViewEntity.VIEW_TYPE_ARTICLE_LIST:
                        if (item.getData() instanceof RecommendCustomEntity.CustomInfoEntity) {
                            ((CustomArticleVH) holder).setData((RecommendCustomEntity.CustomInfoEntity) item.getData());
                        }
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
                    case RecommendCustomViewEntity.VIEW_TYPE_TITLE:
                        CustomTitleVH vh = new CustomTitleVH(context, parent, R.layout.recommend_custom_title_vh);
                        vh.tvChooseType.setOnClickListener(v -> {
                            adapter.clear();
                            dataType = "休息视频";
                            refreshLayout.autoRefresh();
                        });
                        return vh;
                    case RecommendCustomViewEntity.VIEW_TYPE_ARTICLE_LIST:
                        return new CustomArticleVH(context, parent, R.layout.recommend_custom_article_vh);
                    default:
                        break;
                }
                return super.onCreateViewHolder(parent, viewType);
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
    public void onSuccess(RecommendCustomViewEntity entity) {
        stopRefresh();
        adapter.add(entity);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
    }
}
