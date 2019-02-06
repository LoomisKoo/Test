package com.example.administrator.test.viewholder.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
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
public class MovieVH extends BaseViewHolder {
    private ImageView ivPosters;
    private TextView  tvTitle, tvDirector, tvCasts, tvGenres, tvScore;

    public MovieVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        ivPosters = getView(R.id.iv_posters);
        tvTitle = getView(R.id.tv_title);
        tvDirector = getView(R.id.tv_director);
        tvCasts = getView(R.id.tv_casts);
        tvGenres = getView(R.id.tv_genres);
        tvScore = getView(R.id.tv_score);
    }

    @SuppressLint("CheckResult")
    public void setData(MoviewHitEntity.SubjectsEntity entity) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher);
        options.placeholder(R.mipmap.ic_launcher);

        Glide.with(context)
             .setDefaultRequestOptions(options)
             .load(entity.getImages()
                         .getLarge())
             .into(ivPosters);

        String title         = entity.getTitle();
        String directorsName = "导演：" + getDirectorName(entity);
        String castsName     = "主演：" + getCastsName(entity);
        String genres        = "类型：" + getGenres(entity);
        String score = "评分：" + String.valueOf(entity.getRating()
                                                    .getAverage());

        tvTitle.setText(title);
        tvDirector.setText(directorsName);
        tvCasts.setText(castsName);
        tvGenres.setText(genres);
        tvScore.setText(score);

        AnimatorHelper.setViewTouchListener(itemView);
        //点击跳转web
        itemView.setOnClickListener(v -> ARouter.getInstance()
                                                .build(ArouteHelper.ROUTE_ACTIVITY_MOVIE_DETAIL)
                                                .withString("movieID", entity.getId())
                                                .withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                .withInt("x", AnimatorHelper.getDownX())
                                                .withInt("y", AnimatorHelper.getDownY())
                                                .navigation());
    }

    /**
     * 获取所有导演姓名
     *
     * @param entity
     * @return
     */
    private String getDirectorName(MoviewHitEntity.SubjectsEntity entity) {
        if (entity.getDirectors()
                  .size() == 0) {
            return "";
        }
        StringBuilder directorsName = new StringBuilder();
        directorsName.append(entity.getDirectors()
                                   .get(0)
                                   .getName());
        int directorSize = entity.getDirectors()
                                 .size();
        for (int i = 1; i < directorSize; i++) {
            directorsName.append("/")
                         .append(entity.getDirectors()
                                       .get(i)
                                       .getName());
        }
        return directorsName.toString();
    }

    /**
     * 获取所有主演姓名
     *
     * @param entity
     * @return
     */
    private String getCastsName(MoviewHitEntity.SubjectsEntity entity) {
        if (entity.getCasts()
                  .size() == 0) {
            return "";
        }
        StringBuilder castsName = new StringBuilder();

        castsName.append(entity.getCasts()
                               .get(0)
                               .getName());
        int castsNameSize = entity.getCasts()
                                  .size();
        for (int i = 1; i < castsNameSize; i++) {
            castsName.append("/")
                     .append(entity.getCasts()
                                   .get(i)
                                   .getName());
        }
        return castsName.toString();
    }

    /**
     * 获取电影类型
     *
     * @param entity
     * @return
     */
    private String getGenres(MoviewHitEntity.SubjectsEntity entity) {
        if (entity.getGenres()
                  .size() == 0) {
            return "";
        }
        StringBuilder genres = new StringBuilder();
        genres.append(entity.getGenres()
                            .get(0));
        int genresSize = entity.getGenres()
                               .size();
        for (int i = 1; i < genresSize; i++) {
            genres.append("/")
                  .append(entity.getGenres()
                                .get(i));
        }
        return genres.toString();
    }
}
