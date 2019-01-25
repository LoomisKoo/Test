package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.DailyRecommendArticleEntity;
import com.example.administrator.test.util.ArouteHelper;

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
public class DailyRecommendPhotoVH extends BaseViewHolder {
    RecyclerView rvArticle;
    TextView     tvType;

    public DailyRecommendPhotoVH(Context context, ViewGroup parent, int layoutId) {
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
            holder.itemView.setOnClickListener(v -> {
                String url = data.get(position)
                                 .getUrl();
                ARouter.getInstance()
                       .build(ArouteHelper.ROUTE_ACTIVITY_BIG_IMAGE)
                       .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                       .withString("activityTitle", data.get(position)
                                                        .getType())
                       .withString("url", url)
                       .withInt("x", AnimatorHelper.getDownX())
                       .withInt("y", AnimatorHelper.getDownY())
                       .navigation(context);
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ArticleVH extends RecyclerView.ViewHolder {
            ImageView ivWelfare;

            public ArticleVH(@NonNull View itemView) {
                super(itemView);
                ivWelfare = itemView.findViewById(R.id.iv_welfare);
            }
        }
    }
}