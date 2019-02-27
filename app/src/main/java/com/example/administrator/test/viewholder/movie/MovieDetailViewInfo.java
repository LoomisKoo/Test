package com.example.administrator.test.viewholder.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.entity.MovieBriefInformation;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.movie
 * @ClassName: MovieDetailViewInfo
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 3:36 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 3:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailViewInfo {
    private Context   context;
    private ImageView ivPosters, ivBG;
    private TextView tvScore, tvNumOfScore, tvDirector, tvStarring, tvGenres, tvDate, tvRegion;

    public MovieDetailViewInfo(Context context, View rootView) {
        this.context = context;
        ivPosters = rootView.findViewById(R.id.iv_posters);
        ivBG = rootView.findViewById(R.id.iv_bg);
        tvScore = rootView.findViewById(R.id.tv_score);
        tvNumOfScore = rootView.findViewById(R.id.tv_num_of_score);
        tvDirector = rootView.findViewById(R.id.tv_director);
        tvStarring = rootView.findViewById(R.id.tv_starring);
        tvGenres = rootView.findViewById(R.id.tv_genres);
        tvDate = rootView.findViewById(R.id.tv_date);
        tvRegion = rootView.findViewById(R.id.tv_region);

    }

    public void setData(MovieBriefInformation entity) {
        if (null == entity) {
            return;
        }
        String imgUrl = entity.getImages()
                              .getLarge();

        initBackGround(imgUrl);

        Glide.with(context)
             .load(imgUrl)
             .transition(withCrossFade())
             .into(ivPosters);

        String score = "评分：" + entity.getRating()
                                     .getAverage() + "";
        String numOfScore = entity.getCollect_count() + "人评分";
        String director   = "导演：" + getDirectorName(entity);
        String starring   = "主演：" + getCastsName(entity);
        String genres     = "类型：" + getGenres(entity);
        String year       = "上映日期：" + entity.getYear();

        tvScore.setText(score);
        tvNumOfScore.setText(numOfScore);
        tvDirector.setText(director);
        tvStarring.setText(starring);
        tvGenres.setText(genres);
        tvDate.setText(year);
    }

    @SuppressLint("CheckResult")
    private void initBackGround(String imgUrl) {
        Glide.with(context)
             .load(imgUrl)
             .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 100)))
             .into(ivBG);
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
    public String getCastsName(MovieBriefInformation entity) {
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

    /**
     * 设置地区数据
     */
    public void setRegion(List<String> regions) {
        StringBuilder sbRegions = new StringBuilder();
        sbRegions.append("制片国家/地区：");

        if (regions.size() != 0) {
            sbRegions.append(regions.get(0));
            int regionSize = regions.size();

            for (int i = 1; i < regionSize; i++) {
                sbRegions.append("/")
                         .append(regions.get(i));
            }

        }
        tvRegion.setText(sbRegions.toString());
    }
}
