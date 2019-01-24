package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

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
public class DailyRecommendMenuVH extends BaseViewHolder {
    private ImageButton ibReading;
    private ImageButton ibDailyRecommend;
    private ImageButton ibPlayAndroid;
    private ImageButton ibHitMovies;

    public DailyRecommendMenuVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        ibReading = getView(R.id.ib_reading);
        ibDailyRecommend = getView(R.id.ib_daily_recommend);
        ibPlayAndroid = getView(R.id.ib_play_android);
        ibHitMovies = getView(R.id.ib_hit_movies);

        ibReading.setOnClickListener(v -> {
            ToastUtils.showShort("干活阅读");
        });
        ibDailyRecommend.setOnClickListener(v -> {
            ToastUtils.showShort("每日推荐");
        });
        ibPlayAndroid.setOnClickListener(v -> {
            ToastUtils.showShort("玩安卓");
        });
        ibHitMovies.setOnClickListener(v -> {
            ToastUtils.showShort("热映榜");
        });
    }
}