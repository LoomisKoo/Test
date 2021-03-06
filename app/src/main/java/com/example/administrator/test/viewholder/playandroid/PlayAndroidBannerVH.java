package com.example.administrator.test.viewholder.playandroid;

import android.content.Context;
import android.view.ViewGroup;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.BannerEntity;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.util.GlideImageLoader;
import com.loomis.banner.Banner;
import com.loomis.banner.BannerConfig;
import com.loomis.banner.Transformer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder
 * @ClassName: PlayAndroidBannerVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/11/27 2:58 PM
 * @UpdateUser:
 * @UpdateDate: 2018/11/27 2:58 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidBannerVH extends BaseViewHolder {
    @BindView(R.id.banner)
    Banner banner;

    public PlayAndroidBannerVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }

    public void setData(BannerEntity data) {
        startBanner(data.getData());
    }

    private void startBanner(List<BannerEntity.DataBean> entity) {
        if (null == entity || 0 == entity.size()) {
            Logger.e("list empty");
            return;
        }

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(getBannerImgUrls(entity));
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
                  String title = entity.get(position)
                                       .getTitle();
                  String url = entity.get(position)
                                     .getUrl();

                  ArouteHelper.buildWebWithAnimator(context, title, url);
              })
              //必须最后调用的方法，启动轮播图。
              .start();

//        AnimatorHelper.setViewTouchListener(banner);
    }

    /**
     * 获取Banner的title
     *
     * @param entity
     * @return
     */
    private List<String> getBannerTitle(List<BannerEntity.DataBean> entity) {
        List<String> titles = new ArrayList<>();
        for (BannerEntity.DataBean data : entity) {
            titles.add(data.getTitle());
        }
        return titles;
    }

    /**
     * 获取Banner的图片链接
     *
     * @param entity
     * @return
     */
    private List<String> getBannerImgUrls(List<BannerEntity.DataBean> entity) {
        List<String> imgUrls = new ArrayList<>();
        for (BannerEntity.DataBean data : entity) {
            imgUrls.add(data.getImagePath());
        }
        return imgUrls;
    }
}
