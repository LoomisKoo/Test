package com.example.administrator.test.viewholder.recommend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.util.ArouteHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityOptionsCompat;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.recommend
 * @ClassName: WelfareVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:46 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:46 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WelfareVH extends BaseViewHolder {
    ImageView imageView;

    public WelfareVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        imageView = getView(R.id.image);
    }

    @SuppressLint("CheckResult")
    public void setData(ArrayList<String> urlList, int curImgPosition) {

        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher)
               .placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
             .setDefaultRequestOptions(options)
             .load(urlList.get(curImgPosition))
             .into(imageView);
        imageView.setOnClickListener(v -> {
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imageView, "translation_img");

            ARouter.getInstance()
                   .build(ArouteHelper.ROUTE_ACTIVITY_BIG_IMAGE)
                   .withString("activityTitle", "福利")
                   .withInt("curImgPosition", curImgPosition)
                   .withOptionsCompat(optionsCompat)
                   .withStringArrayList("urlList", urlList)
                   .navigation(context);
        });
    }
}
