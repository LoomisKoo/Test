package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseFragmentNew;
import com.example.administrator.test.entity.NaviEntity;
import com.example.administrator.test.mvp.contract.NaviContract;
import com.example.administrator.test.mvp.model.NaviModel;
import com.example.administrator.test.mvp.presenter.NaviPresenter;
import com.example.administrator.test.util.ArouteHelper;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: PlayAndroidNaviFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/14 5:34 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/14 5:34 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidNaviFragment extends BaseFragmentNew<NaviPresenter> implements NaviContract.View {

    List<ContentTypeEntity> titleData;
    /**
     * 左边recyclerview
     */
    RecyclerView            rvName;
    /**
     * 右边recyclerview
     */
    RecyclerView            rvTitle;

    RecyclerView.SmoothScroller titleSmoothScroller;
    RecyclerView.SmoothScroller nameSmoothScroller;
    GridLayoutManager           titleLayoutManager;

    /**
     * 右边列表滑动监听
     */
    RecyclerViewListener titleScrollListener;

    NameAdapter  nameAdapter;
    TitleAdapter titleAdapter;

    class ContentTypeEntity {
        private boolean isTitle = false;
        private Object  data;

        public boolean isTitle() {
            return isTitle;
        }

        public void setTitle(boolean title) {
            isTitle = title;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    @Override
    protected void initView(View view) {
        rvName = llContent.findViewById(R.id.rv_name_new);
        rvTitle = llContent.findViewById(R.id.rv_title_new);
        initRv();
    }

    @Override
    public int bindContentLayout() {
        return R.layout.play_android_navi_fragment_new;
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
    protected void refreshData() {
        super.refreshData();
        if (null != nameAdapter) {
            nameAdapter.data.clear();
            nameAdapter.notifyDataSetChanged();
        }
        if (null != titleAdapter) {
            titleAdapter.data.clear();
            titleAdapter.notifyDataSetChanged();
        }
        presenter.getNaviData();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected NaviPresenter getPresenter() {
        return new NaviPresenter(this, new NaviModel());
    }

    @Override
    public void getDataSuccess(NaviEntity entity) {
        stopRefresh();
        initTitleData(entity);
        initContentData(entity);
        connectRecyclerView();
    }

    @Override
    public void getDataFail(String msg) {
        stopRefresh();
    }

    private void initTitleData(NaviEntity entity) {
        List<String> titles = new ArrayList<>();
        int dataSize = entity.getData()
                             .size();
        List<NaviEntity.DataBean> data = entity.getData();
        for (int i = 0; i < dataSize; i++) {
            titles.add(data.get(i)
                           .getName());
        }
        rvName.setAdapter(nameAdapter = new NameAdapter(titles));
    }

    /**
     * 联动两个recyclerview
     */
    private void connectRecyclerView() {
    }

    /**
     * 改装数据
     *
     * @param entity
     */
    private void initContentData(NaviEntity entity) {
        int dataSize = entity.getData()
                             .size();
        List<NaviEntity.DataBean> originData = entity.getData();
        titleData = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            //title数据
            ContentTypeEntity viewEntity = new ContentTypeEntity();
            viewEntity.data = originData.get(i)
                                        .getName();
            viewEntity.isTitle = false;
            titleData.add(viewEntity);
            //文章类型数据
            int articleSize = originData.get(i)
                                        .getArticles()
                                        .size();
            for (int j = 0; j < articleSize; j++) {
                viewEntity = new ContentTypeEntity();
                viewEntity.data = originData.get(i)
                                            .getArticles()
                                            .get(j);
                viewEntity.isTitle = true;
                titleData.add(viewEntity);
            }
        }
        rvTitle.setAdapter(titleAdapter = new TitleAdapter(titleData));
    }

    /**
     * 初始化recyclerview
     */
    private void initRv() {
        rvName.setLayoutManager(new LinearLayoutManager(getContext()));
        titleLayoutManager = new GridLayoutManager(getContext(), 2);
        titleLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return titleData.get(position)
                                .isTitle() ? 1 : 2;
            }
        });
        rvTitle.setLayoutManager(titleLayoutManager);
        initScroller();
    }

    /**
     * 左边列表适配器
     */
    class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameVH> {
        private List<String> data;
        private int          selectPosition = 0;

        public NameAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public NameVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext())
                                   .inflate(R.layout.play_android_navi_item_title, parent, false);
            return new NameVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull NameVH holder, int position) {
            holder.tvName.setText(data.get(position));
            //左边列表点击事件
            holder.tvName.setOnClickListener(v -> {
                isTitleRVMoving = true;
                selectPosition = position;
                //点击后，右边列表滑动到相应的位置
                String name          = data.get(position);
                int    titleDataSize = titleAdapter.data.size();
                for (int i = 0; i < titleDataSize; i++) {
                    if (!titleAdapter.data.get(i).isTitle) {
                        String chapterName = (String) titleAdapter.data.get(i)
                                                                       .getData();
                        if (StringUtils.equals(name, chapterName)) {
                            titleSmoothScroller.setTargetPosition(i);
                            titleLayoutManager.startSmoothScroll(titleSmoothScroller);
                            break;
                        }
                    }
                }
                notifyDataSetChanged();
            });
            if (selectPosition == position) {
                holder.tvName.setBackgroundResource(R.color.white);
            }
            else {
                holder.tvName.setBackgroundResource(R.color.gray_e1dada);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class NameVH extends RecyclerView.ViewHolder {
            TextView tvName;

            public NameVH(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tv_title);
            }
        }
    }

    /**
     * 右边列表适配器
     */
    class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleVH> {
        private List<ContentTypeEntity> data;

        public TitleAdapter(List<ContentTypeEntity> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public TitleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = null;
            switch (viewType) {
                //content
                case 0:
                    v = LayoutInflater.from(getContext())
                                      .inflate(R.layout.play_android_navi_item_name, parent, false);
                    break;
                //title
                case 1:
                    v = LayoutInflater.from(getContext())
                                      .inflate(R.layout.play_android_navi_item_title, parent, false);
                    break;
                default:
                    break;
            }

            return new TitleVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull TitleVH holder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {
                //name
                case 0:
                    String title = (String) data.get(position)
                                                .getData();
                    holder.tvName.setText(title);
                    break;
                //title
                case 1:
                    AnimatorHelper.setViewTouchListener(holder.tvTitle);
                    NaviEntity.DataBean.ArticlesBean entity = (NaviEntity.DataBean.ArticlesBean) data.get(position)
                                                                                                     .getData();
                    holder.tvTitle.setText(entity.getTitle());
                    holder.tvTitle.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(getActivity(), entity.getTitle(), entity.getLink()));
                    break;
                default:
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public int getItemViewType(int position) {
            return data.get(position).isTitle ? 1 : 0;
        }

        class TitleVH extends RecyclerView.ViewHolder {
            TextView tvTitle;
            TextView tvName;

            public TitleVH(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
                tvName = itemView.findViewById(R.id.tv_name);
            }
        }
    }

    /**
     * 右边列表是否滑动中
     */
    private boolean isTitleRVMoving = false;

    private void initScroller() {
        titleSmoothScroller = new LinearSmoothScroller(getContext()) {

            @Override

            protected int getVerticalSnapPreference() {

                return LinearSmoothScroller.SNAP_TO_START;

            }

        };
        nameSmoothScroller = new LinearSmoothScroller(getContext()) {

            @Override

            protected int getVerticalSnapPreference() {

                return LinearSmoothScroller.SNAP_TO_START;

            }

        };

        titleScrollListener = new RecyclerViewListener();
        rvTitle.addOnScrollListener(titleScrollListener);
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (isTitleRVMoving && newState == RecyclerView.SCROLL_STATE_IDLE) {
                isTitleRVMoving = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!isTitleRVMoving) {
                //TODO 该处滑动时候多次调用，待优化
                int firstVisiblePosition = titleLayoutManager.findFirstVisibleItemPosition();
                if (titleData.size() <= firstVisiblePosition && !titleData.get(firstVisiblePosition).isTitle) {
                    // 如果此项对应的是左边的大类的index
                    int nameSize = nameAdapter.data.size();
                    for (int i = 0; i < nameSize; i++) {
                        if (nameAdapter.data.get(i)
                                            .equals(titleData.get(firstVisiblePosition)
                                                             .getData())) {
                            nameAdapter.selectPosition = i;
                            rvName.scrollToPosition(i);
                            nameAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }
}
