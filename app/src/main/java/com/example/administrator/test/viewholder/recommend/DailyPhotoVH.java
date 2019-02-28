package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.RecommendDailyArticleEntity;
import com.example.administrator.test.widget.imgViewPager.GlideSimpleLoader;
import com.example.administrator.test.widget.imgViewPager.ImageWatcherHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: DailyArticleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 6:27 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 6:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyPhotoVH extends BaseViewHolder {


    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.rv_article)
    RecyclerView rvArticle;


    public DailyPhotoVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);

        rvArticle.setLayoutManager(new LinearLayoutManager(context));
        //设置不需要焦点 否则切换tab后，嵌套的recyclerview会自动滚动
        rvArticle.setFocusableInTouchMode(false);
        rvArticle.requestFocus();

        rvArticle.setNestedScrollingEnabled(false);
    }

    public void setData(List<RecommendDailyArticleEntity> entityList) {

        if (entityList.size() > 0) {
            tvTitle.setText(entityList.get(0)
                                      .getType());
            rvArticle.setAdapter(adapter = new DailyRecommendArticleAdapter(entityList));
        }

    }

    DailyRecommendArticleAdapter adapter;

    class DailyRecommendArticleAdapter extends RecyclerView.Adapter<DailyRecommendArticleAdapter.ArticleVH> {
        private ImageWatcherHelper     iwHelper;
        private SparseArray<ImageView> mapping;

        private List<RecommendDailyArticleEntity> data;
        private List<String>                      urlList;

        public DailyRecommendArticleAdapter(List<RecommendDailyArticleEntity> data) {
            this.data = data;
            initUrlList();
            mapping = new SparseArray<>();
            iwHelper = ImageWatcherHelper.with((FragmentActivity) context, new GlideSimpleLoader());
        }

        @NonNull
        @Override
        public ArticleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //实例化得到Item布局文件的View对象
            View v = LayoutInflater.from(context)
                                   .inflate(R.layout.recommend_daily_vh_photo_list_item, parent, false);
            AnimatorHelper.setViewTouchListener(v);
            ArticleVH vh = new ArticleVH(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleVH holder, int position) {
            Glide.with(context)
                 .load(data.get(position)
                           .getUrl())
                 .into(holder.ivWelfare);

            mapping.append(position, holder.ivWelfare);
            //点击图片显示大图
            holder.itemView.setOnClickListener(v -> iwHelper.show(holder.ivWelfare, mapping, urlList, position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ArticleVH extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_welfare)
            ImageView ivWelfare;

            public ArticleVH(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        private void initUrlList() {
            urlList = new ArrayList<>();
            int dataSize = data.size();
            for (int i = 0; i < dataSize; i++) {
                urlList.add(data.get(i)
                                .getUrl());
            }
        }

        public boolean onBackPressed() {
            if (!iwHelper.handleBackPressed()) {
                return false;
            }
            return true;
        }
    }

    /**
     * 返回事件
     *
     * @return
     */
    public boolean onBackPressed() {
        return adapter.onBackPressed();
    }

}