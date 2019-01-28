package com.example.administrator.test.fragment;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.mvp.contract.RecommendWelfareContract;
import com.example.administrator.test.mvp.model.RecommendWelfareModel;
import com.example.administrator.test.mvp.presenter.RecommendWelfarePresenter;
import com.example.administrator.test.viewholder.recommend.WelfareVH;
import com.example.administrator.test.widget.imgViewPager.GlideSimpleLoader;
import com.example.administrator.test.widget.imgViewPager.ImageWatcherHelper;

import java.util.ArrayList;

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
public class RecommendWelfareFragment extends BaseListFragment<String, RecommendWelfarePresenter> implements RecommendWelfareContract.View {
    private static final int                    GRID_LAYOUT_ROWS = 2;
    private              ImageWatcherHelper     iwHelper;
    private              SparseArray<ImageView> mapping;

    @Override
    protected void getData(int page, int pageSize) {
        presenter.getWelfare("福利", pageSize, page);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mapping = new SparseArray<>();
        iwHelper = ImageWatcherHelper.with(getActivity(), new GlideSimpleLoader());
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<String>(getContext(), R.layout.recommend_welfare_vh_item) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, String item, int viewType, int position) {
                ((WelfareVH) holder).setData((ArrayList<String>) data, position);
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new GridLayoutHelper(GRID_LAYOUT_ROWS);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                WelfareVH welfareVH = new WelfareVH(getActivity(), parent, R.layout.recommend_welfare_vh_item);
                welfareVH.setIsRecyclable(false);

                return welfareVH;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                mapping.append(position, ((WelfareVH) holder).imageView);
                ((WelfareVH) holder).imageView.setOnClickListener(v -> iwHelper.show(((WelfareVH) holder).imageView, mapping, data, position));
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
    public void onSuccess(String imgUrl) {
        stopRefresh();
        adapter.add(imgUrl);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
    }
}
