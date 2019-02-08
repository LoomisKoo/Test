package com.example.administrator.test.viewholder.movie;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.MovieDetailEntity;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.movie
 * @ClassName: MovieDetailHeadVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 3:36 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 3:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailIntroductionVH extends BaseViewHolder {
    private TextView tvAliasDetail, tvPlotDetail;

    public MovieDetailIntroductionVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        tvAliasDetail = getView(R.id.tv_alias_detail);
        tvPlotDetail = getView(R.id.tv_plot_detail);

    }

    public void setData(MovieDetailEntity entity) {
        String alisDetail = getAlias(entity);
        String plotDetail = entity.getSummary();

        tvAliasDetail.setText(alisDetail);
        tvPlotDetail.setText(plotDetail);
    }

    /**
     * 获取所有别名
     *
     * @param entity
     * @return
     */
    private String getAlias(MovieDetailEntity entity) {
        if (entity.getDirectors()
                  .size() == 0) {
            return "";
        }
        StringBuilder alias = new StringBuilder();
        alias.append(entity.getAka()
                                   .get(0));
        int aliasSize = entity.getAka()
                                 .size();
        for (int i = 1; i < aliasSize; i++) {
            alias.append("/")
                         .append(entity.getAka()
                                       .get(i));
        }
        return alias.toString();
    }
}
