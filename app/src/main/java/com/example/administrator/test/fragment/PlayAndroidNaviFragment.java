package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.base.fragment.BaseFragment;
import com.example.administrator.test.entity.NaviEntity;
import com.example.administrator.test.mvp.contract.NaviContract;
import com.example.administrator.test.mvp.model.NaviModel;
import com.example.administrator.test.mvp.presenter.NaviPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

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
public class PlayAndroidNaviFragment extends BaseFragment<NaviPresenter> implements NaviContract.View {
    @BindView(R.id.rv_title)
    RecyclerView rvTitle;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;

    List<ContentTypeEntity> contentData;

    class ContentTypeEntity {
        private boolean isTitle = true;
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
    protected int setContentLayout() {
        return R.layout.play_android_navi_fragment;
    }

    @Override
    protected void initView(View view) {
        initRv();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter.getNaviData();
    }

    @Override
    protected NaviPresenter getPresenter() {
        return new NaviPresenter(this, new NaviModel());
    }

    @Override
    public void getDataSuccess(NaviEntity entity) {
        initTitleData(entity);
        initContentData(entity);
    }

    @Override
    public void getDataFail(String msg) {

    }

    private void initTitleData(NaviEntity entity) {
        List<String>              titles   = new ArrayList<>();
        int                       dataSize = entity.getData().size();
        List<NaviEntity.DataBean> data     = entity.getData();
        for (int i = 0; i < dataSize; i++) {
            titles.add(data.get(i).getName());
        }
        rvTitle.setAdapter(new TitleAdapter(titles));

    }

    private void initContentData(NaviEntity entity) {
        int                       dataSize   = entity.getData().size();
        List<NaviEntity.DataBean> originData = entity.getData();
        contentData = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            //title数据
            ContentTypeEntity viewEntity = new ContentTypeEntity();
            viewEntity.data = originData.get(i).getName();
            viewEntity.isTitle = true;
            contentData.add(viewEntity);
            //文章类型数据
            int articleSize = originData.get(i).getArticles().size();
            for (int j = 0; j < articleSize; j++) {
                viewEntity = new ContentTypeEntity();
                viewEntity.data = originData.get(i).getArticles().get(j);
                viewEntity.isTitle = false;
                contentData.add(viewEntity);
            }
        }
        rvContent.setAdapter(new ContentAdapter(contentData));
    }

    private void initRv() {
        rvTitle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTitle.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return contentData.get(position).isTitle() ? 2 : 1;
            }
        });
        rvContent.setLayoutManager(layoutManager);
    }

    class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.TitleVH> {
        private List<String> data;

        public TitleAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public TitleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.play_android_navi_item_title, parent, false);
            return new TitleVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull TitleVH holder, int position) {
            holder.tvTitle.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class TitleVH extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public TitleVH(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
            }
        }
    }

    class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentVH> {
        private List<ContentTypeEntity> data;

        public ContentAdapter(List<ContentTypeEntity> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ContentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = null;
            switch (viewType) {
                //content
                case 0:
                    v = LayoutInflater.from(getContext()).inflate(R.layout.play_android_navi_item_title, parent, false);
                    break;
                //title
                case 1:
                    v = LayoutInflater.from(getContext()).inflate(R.layout.play_android_navi_item_title, parent, false);
                    break;
                default:
                    break;
            }

            return new ContentVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ContentVH holder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {
                //content
                case 0:
                    NaviEntity.DataBean.ArticlesBean entity = (NaviEntity.DataBean.ArticlesBean) data.get(position).getData();
                    holder.tvTitle.setText(entity.getTitle());
                    break;
                //title
                case 1:
                    String title = (String) data.get(position).getData();
                    holder.tvTitle.setText(title);
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

        class ContentVH extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public ContentVH(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
            }
        }
    }
}
