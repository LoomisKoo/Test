package com.example.administrator.test.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.ArticleListEntity;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder
 * @ClassName: PlayAndroidArticleListVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2018/12/26 2:24 PM
 * @UpdateUser:
 * @UpdateDate: 2018/12/26 2:24 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PlayAndroidArticleListVH extends BaseViewHolder {
    private TextView tvSubTitle;
    private TextView tvTitle;
    private TextView tvPublishInfo;
    private CheckBox cbCollect;

    public PlayAndroidArticleListVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        tvSubTitle = retrieveView(R.id.tvSubTitle);
        tvTitle = retrieveView(R.id.tvTitle);
        tvPublishInfo = retrieveView(R.id.tvPublishInfo);
        cbCollect = retrieveView(R.id.cbCollect);
    }

    public PlayAndroidArticleListVH(Context context, View itemView) {
        super(context, itemView);
    }

    public void setData(ArticleListEntity.DataBean.ArticleInfoBean data) {

        tvSubTitle.setText(data.getChapterName());
        tvTitle.setText(data.getTitle());
        tvPublishInfo.setText(data.getNiceDate() + "·" + data.getAuthor());
        tvSubTitle.setText(data.getChapterName());
        cbCollect.setChecked(data.isCollect());
    }
}
