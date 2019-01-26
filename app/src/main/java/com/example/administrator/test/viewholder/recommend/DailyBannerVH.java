package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.ViewGroup;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.util.GlideImageLoader;
import com.loomis.banner.Banner;
import com.loomis.banner.BannerConfig;
import com.loomis.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

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
public class DailyBannerVH extends BaseViewHolder {
    Banner banner;

    public DailyBannerVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        banner = retrieveView(R.id.banner);
    }

    public void setData(List<String> data) {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            titles.add("");
        }
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
              //设置图片加载器，图片加载器在下方
              .setImageLoader(new GlideImageLoader())
              //设置图片网址或地址的集合
              .setImages(data)
              //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
              .setBannerAnimation(Transformer.Default)
              //设置轮播图的标题集合
              .setBannerTitles(titles)
              //设置轮播间隔时间
              .setDelayTime(3000)
              //设置是否为自动轮播，默认是“是”。
              .isAutoPlay(true)
              //设置指示器的位置，小点点，左中右。
              .setIndicatorGravity(BannerConfig.CENTER)
              //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//              .setOnBannerListener(position -> {
//              })
              //必须最后调用的方法，启动轮播图。
              .start();
    }
}