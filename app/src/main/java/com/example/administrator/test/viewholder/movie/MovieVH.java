package com.example.administrator.test.viewholder.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.MovieBriefInformation;

import butterknife.BindView;

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
    @BindView(R.id.iv_posters)
    public ImageView ivPosters;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_director)
    TextView tvDirector;
    @BindView(R.id.tv_casts)
    TextView tvCasts;
    @BindView(R.id.tv_genres)
    TextView tvGenres;
    @BindView(R.id.tv_score)
    TextView tvScore;

    public MovieVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
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
    }

    /**
     * 获取所有导演姓名
     *
     * @param entity
     * @return
     */
    private String getDirectorName(MovieBriefInformation entity) {
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
    private String getCastsName(MovieBriefInformation entity) {
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
    private String getGenres(MovieBriefInformation entity) {
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
