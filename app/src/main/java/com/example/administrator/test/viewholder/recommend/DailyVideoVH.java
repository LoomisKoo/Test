package com.example.administrator.test.viewholder.recommend;

import android.content.Context;
import android.view.ViewGroup;

import com.example.administrator.test.entity.RecommendDailyArticleEntity;
import com.example.administrator.test.util.ArouteHelper;

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
public class DailyVideoVH extends DailyArticleVH {

    public DailyVideoVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }


    @Override
    public void setData(List<RecommendDailyArticleEntity> entityList) {
        super.setData(entityList);
        clickCallBack = (data, position) -> ArouteHelper.buildWebWithAnimator(context, entityList.get(0)
                                                                                                 .getType(), entityList.get(0)
                                                                                                                       .getUrl());
    }
}