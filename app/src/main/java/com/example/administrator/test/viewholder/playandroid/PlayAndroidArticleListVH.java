package com.example.administrator.test.viewholder.playandroid;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.ArticleListEntity;
import com.like.LikeButton;

import butterknife.BindView;

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
    @BindView(R.id.tvSubTitle)
    TextView   tvSubTitle;
    @BindView(R.id.tvTitle)
    TextView   tvTitle;
    @BindView(R.id.tvPublishInfo)
    TextView   tvPublishInfo;
    @BindView(R.id.cbCollect)
    LikeButton cbCollect;

    public PlayAndroidArticleListVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }

    public void setData(ArticleListEntity.DataBean.ArticleInfoBean data) {
        tvSubTitle.setText(data.getChapterName());
        tvTitle.setText(data.getTitle());
        tvPublishInfo.setText(data.getNiceDate() + "·" + data.getAuthor());
        tvSubTitle.setText(data.getChapterName());
        cbCollect.setLiked(data.isCollect());
    }
}
