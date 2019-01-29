package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.util.ArouteHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: CustomTitleVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/29 2:21 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/29 2:21 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomArticleVH extends BaseViewHolder {
    private TextView tvTitle, tvFrom, tvDate;
    private ImageView ivDemo;

    public CustomArticleVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        tvTitle = getView(R.id.tv_title);
        tvFrom = getView(R.id.tv_from);
        tvDate = getView(R.id.tv_date);
        ivDemo = getView(R.id.iv_demo);
    }

    public void setData(RecommendCustomEntity.CustomInfoEntity entity) {
        tvTitle.setText(entity.getDesc());
        tvFrom.setText(entity.getWho() + "." + entity.getType());
        tvDate.setText(entity.getPublishedAt());
        if (null != entity.getImages() && entity.getImages()
                                                .size() > 0) {
            Glide.with(context)
                 .load(entity.getImages()
                             .get(0))
                 .into(ivDemo);
        }
        AnimatorHelper.setViewTouchListener(itemView);
        itemView.setOnClickListener(v -> ArouteHelper.buildWebWithAnimator(context, entity.getDesc(), entity.getUrl()));
    }
}
