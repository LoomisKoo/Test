package com.example.administrator.test.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.entity.view.PlayAndroidViewEntity;
import com.example.administrator.test.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder
 * @ClassName: PlayAndroidHeadViewHolder
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/27 2:58 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/27 2:58 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidHeadViewHolder extends BaseViewHolder {
    Banner banner;

    public PlayAndroidHeadViewHolder(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        banner = retrieveView(R.id.banner);
    }

    public PlayAndroidHeadViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void setData(Object data) {
        startBanner((BannerEntity) ((PlayAndroidViewEntity) data).getData());
    }


    private void startBanner(BannerEntity entity) {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(getBannerImgs(entity));
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(getBannerTitle(entity));
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(position -> {

                })
                //必须最后调用的方法，启动轮播图。
                .start();

    }

    private List<String> getBannerTitle(BannerEntity entity) {
        List<String> titles = new ArrayList<>();

        for (BannerEntity.DataBean data : entity.getData()) {
            titles.add(data.getTitle());
        }
        return titles;
    }

    private List<String> getBannerImgs(BannerEntity entity) {
        List<String> imgs = new ArrayList<>();

        for (BannerEntity.DataBean data : entity.getData()) {
            imgs.add(data.getImagePath());
        }
        return imgs;
    }
}
