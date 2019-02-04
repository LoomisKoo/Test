package com.example.administrator.test.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.RecommendDailyArticleEntity;
import com.example.administrator.test.entity.RecommendDailyEntity;
import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.mvp.contract.RecommendDailyContract;
import com.example.administrator.test.mvp.model.RecommendDailyModel;
import com.example.administrator.test.mvp.presenter.RecommendDailyPresenter;
import com.example.administrator.test.viewholder.recommend.DailyArticleVH;
import com.example.administrator.test.viewholder.recommend.DailyBannerVH;
import com.example.administrator.test.viewholder.recommend.DailyMenuVH;
import com.example.administrator.test.viewholder.recommend.DailyPhotoVH;
import com.example.administrator.test.viewholder.recommend.DailyVideoVH;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendDailyFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/22 6:13 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/22 6:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendDailyFragment extends BaseListFragment<BaseViewEntity, RecommendDailyPresenter> implements RecommendDailyContract.View {
    private DailyPhotoVH dailyPhotoVH;

    @Override
    protected void getData(int page, int pageSize) {
        addBanner();
        addMenu();
        presenter.getDailyRecommend();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public int bindTopLayout() {
        return 0;
    }

    @Override
    public int bindBottomLayout() {
        return 0;
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<BaseViewEntity>(getContext(), 0) {
            @Override
            @SuppressWarnings("unchecked")
            protected void onSetItemData(BaseViewHolder holder, BaseViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_BANNER:
                        ((DailyBannerVH) holder).setData((List<String>) item.getData());
                        break;
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_MAIN_MENU:
                        break;
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE:
                        ((DailyArticleVH) holder).setData((List<RecommendDailyArticleEntity>) item.getData());
                        break;
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_VIDEO:
                        ((DailyVideoVH) holder).setData((List<RecommendDailyArticleEntity>) item.getData());
                        break;
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_PHOTO:
                        ((DailyPhotoVH) holder).setData((List<RecommendDailyArticleEntity>) item.getData());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public LayoutHelper onCreateLayoutHelper() {
                return new LinearLayoutHelper();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType) {
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_BANNER:
                        return new DailyBannerVH(getActivity(), parent, R.layout.recommend_daily_vh_banner);
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_MAIN_MENU:
                        return new DailyMenuVH(getActivity(), parent, R.layout.recommend_daily_vh_menu);
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE:
                        return new DailyArticleVH(getActivity(), parent, R.layout.recommend_daily_vh_common);
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_VIDEO:
                        return new DailyVideoVH(getActivity(), parent, R.layout.recommend_daily_vh_common);
                    case BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_PHOTO:
                        dailyPhotoVH = new DailyPhotoVH(getActivity(), parent, R.layout.recommend_daily_vh_common);
                        return dailyPhotoVH;
                    default:
                        break;
                }

                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return getItem(position).getViewType();
            }

            @Override
            public int getItemCount() {
                return adapter.getData()
                              .size();
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        };
    }

    @Override
    protected RecommendDailyPresenter getPresenter() {
        return new RecommendDailyPresenter(new RecommendDailyModel(), this);
    }

    @Override
    public void onSuccess(RecommendDailyEntity entity) {
        //福利
        addArticleData(entity.getResults()
                             .getWelfareBeanList(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_PHOTO);
        //android
        addArticleData(entity.getResults()
                             .getAndroid(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE);
        //ios
        addArticleData(entity.getResults()
                             .getIOS(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE);
        //app
        addArticleData(entity.getResults()
                             .getApp(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE);
        //拓展资源
        addArticleData(entity.getResults()
                             .getExpandBeanList(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE);
        //视频
        addArticleData(entity.getResults()
                             .getVideoBeanList(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_VIDEO);
        //瞎推荐
        addArticleData(entity.getResults()
                             .getRecommendBeanList(), BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_ARTICLE);
        checkRvEmpty();
    }

    @Override
    public void onError(String msg) {
        checkRvEmpty();
        showToast(msg);
    }

    /**
     * 列表为空则显示空提示
     */
    private void checkRvEmpty() {
        stopRefresh();
        checkEmpty(getString(R.string.common_empty_list_load_failed), R.mipmap.ic_load_err);
    }

    /**
     * banner数据
     */
    private void addBanner() {
        //TODO 暂时写死图片
        List<String> imgList = new ArrayList<>();
        //特斯拉logo
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548312536332&di=77ecb35f710f883f5e12ad9e90bcb003&imgtype=0&src=http%3A%2F%2Fwww.deskier.com%2Fuploads%2Fallimg%2F170706%2F1-1FF6153306.jpg");
        //钢铁侠logo
        imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1548318266090&di=a85615596dec381e7d33b1074e8c14e3&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Ffaf2b2119313b07e9f2e75030dd7912397dd8c2d.jpg");
        adapter.add(new BaseViewEntity(imgList, BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_BANNER));
    }

    /**
     * 主菜单
     */
    private void addMenu() {
        adapter.add(new BaseViewEntity(null, BaseViewEntity.RECOMMEND_DAILY_VIEW_TYPE_MAIN_MENU));
    }

    /**
     * 根据不同该数据加入不同布局
     *
     * @param entities
     * @param viewType
     */
    public void addArticleData(List<RecommendDailyArticleEntity> entities, int viewType) {
        BaseViewEntity data = new BaseViewEntity(entities, viewType);
        adapter.add(data);
    }

    @Override
    public boolean onBackPressed() {
        return dailyPhotoVH.onBackPressed();
    }
}
