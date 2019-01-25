package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.DailyRecommendArticleEntity;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class DailyRecommendVideoVH extends DailyRecommendArticleVH {

    public DailyRecommendVideoVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }


    @Override
    public void setData(List<DailyRecommendArticleEntity> entityList) {
        super.setData(entityList);
        clickCallBack = position -> ArouteHelper.buildWebWithAnimator(context, entityList.get(0)
                                                                                         .getType(), entityList.get(0)
                                                                                                               .getUrl());
    }
}