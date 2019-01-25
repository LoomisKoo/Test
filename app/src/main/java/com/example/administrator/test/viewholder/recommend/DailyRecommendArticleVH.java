package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.DailyRecommendArticleEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: DailyRecommendArticleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/23 6:27 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/23 6:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailyRecommendArticleVH extends BaseViewHolder {
    RecyclerView rvArticle;
    TextView     tvType;

    public DailyRecommendArticleVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        tvType = retrieveView(R.id.tv_title);
        rvArticle = retrieveView(R.id.rv_article);

        rvArticle.setLayoutManager(new LinearLayoutManager(context));
        //设置不需要焦点 否则切换tab后，嵌套的recyclerview会自动滚动
        rvArticle.setFocusableInTouchMode(false);
        rvArticle.requestFocus();
    }

    public void setData(List<DailyRecommendArticleEntity> entityList) {
        if (entityList.size() > 0) {
            tvType.setText(entityList.get(0)
                                     .getType());
            rvArticle.setAdapter(new DailyRecommendArticleAdapter(entityList));
        }

    }

    DailyRecommendArticleAdapter adapter;

    class DailyRecommendArticleAdapter extends RecyclerView.Adapter<DailyRecommendArticleAdapter.ArticleVH> {

        private List<DailyRecommendArticleEntity> data;

        public DailyRecommendArticleAdapter(List<DailyRecommendArticleEntity> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ArticleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //实例化得到Item布局文件的View对象
            View v = LayoutInflater.from(context)
                                   .inflate(R.layout.recommend_daily_vh_article_list_item, parent, false);
            AnimatorHelper.setViewTouchListener(v);
            ArticleVH vh = new ArticleVH(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleVH holder, int position) {
            holder.tvTitle.setText(data.get(position)
                                       .getDesc());
            holder.itemView.setOnClickListener(v -> {
                if (null != clickCallBack) {
                    clickCallBack.onItemClick(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ArticleVH extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public ArticleVH(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tv_title);
            }
        }
    }

    ClickCallBack clickCallBack;

    interface ClickCallBack {
        void onItemClick(int position);
    }
}