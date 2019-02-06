package com.example.administrator.test.viewholder.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.MovieBriefInformation;
import com.example.administrator.test.entity.MovieTop250Entity;
import com.example.administrator.test.entity.MoviewHitEntity;
import com.example.administrator.test.util.ArouteHelper;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.movie
 * @ClassName: MovieVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/3 11:54 AM
 * @UpdateUser:
 * @UpdateDate: 2019/2/3 11:54 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieTop250VH extends BaseViewHolder {
    public ImageView ivPosters;
    private TextView  tvTitle, tvScore;

    public MovieTop250VH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        ivPosters = getView(R.id.iv_posters);
        tvTitle = getView(R.id.tv_title);
        tvScore = getView(R.id.tv_score);
    }

    @SuppressLint("CheckResult")
    public void setData(MovieBriefInformation entity) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher);
        options.placeholder(R.mipmap.ic_launcher);

        Glide.with(context)
             .setDefaultRequestOptions(options)
             .load(entity.getImages()
                         .getLarge())
             .into(ivPosters);

        String title = entity.getTitle();
        String score = "评分：" + String.valueOf(entity.getRating()
                                                    .getAverage());
        tvTitle.setText(title);
        tvScore.setText(score);

        itemView.setOnClickListener(v -> ARouter.getInstance()
                                        .build(ArouteHelper.ROUTE_ACTIVITY_MOVIE_DETAIL)
                                        .withString("movieID", entity.getId())
                                        .navigation());
    }
}
