package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.RecommendDailyArticleEntity;

import java.util.List;

import androidx.annotation.NonNull;
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
public class DailyArticleVH extends BaseViewHolder {
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.rv_article)
    RecyclerView rvArticle;

    public DailyArticleVH(Context context, ViewGroup parent, int layoutId) {
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
            rvArticle.setAdapter(new DailyRecommendArticleAdapter(entityList));
        }

    }

    /**
     * 列表点击回调
     *
     * @param clickCallBack
     */
    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    /**
     * 列表适配器
     */
    class DailyRecommendArticleAdapter extends RecyclerView.Adapter<DailyRecommendArticleAdapter.ArticleVH> {
        private List<RecommendDailyArticleEntity> data;

        public DailyRecommendArticleAdapter(List<RecommendDailyArticleEntity> data) {
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
                    clickCallBack.onItemClick(data, position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ArticleVH extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_title)
            TextView tvTitle;

            public ArticleVH(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    protected ClickCallBack clickCallBack;

    public interface ClickCallBack {
        void onItemClick(Object data, int position);
    }
}