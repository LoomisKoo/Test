package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.DailyRecommendArticleEntity;
import com.example.administrator.test.entity.DailyRecommendEntity;
import com.example.administrator.test.entity.view.DailyRecommendViewEntity;
import com.example.administrator.test.mvp.contract.DailyRecommendContract;
import com.example.administrator.test.mvp.model.DailyRecommendModel;
import com.example.administrator.test.mvp.presenter.DailyRecommendPresenter;
import com.example.administrator.test.viewholder.recommend.DailyRecommendArticleVH;
import com.example.administrator.test.viewholder.recommend.DailyRecommendBannerVH;
import com.example.administrator.test.viewholder.recommend.DailyRecommendMenuVH;
import com.example.administrator.test.viewholder.recommend.DailyRecommendVideoVH;

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
public class RecommendDailyFragment extends BaseListFragment<DailyRecommendViewEntity, DailyRecommendPresenter> implements DailyRecommendContract.View {

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
        return new QuickDelegateAdapter<DailyRecommendViewEntity>(getContext(), 0) {
            @Override
            @SuppressWarnings("unchecked")
            protected void onSetItemData(BaseViewHolder holder, DailyRecommendViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case DailyRecommendViewEntity.VIEW_TYPE_BANNER:
                        ((DailyRecommendBannerVH) holder).setData((List<String>) item.getData());
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_MAIN_MENU:
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_ARTICLE:
                        ((DailyRecommendArticleVH) holder).setData((List<DailyRecommendArticleEntity>) item.getData());
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_VIDEO:
                        ((DailyRecommendVideoVH) holder).setData((List<DailyRecommendArticleEntity>) item.getData());
                        break;
                    case DailyRecommendViewEntity.VIEW_TYPE_PHOTO:
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
                    case DailyRecommendViewEntity.VIEW_TYPE_BANNER:
                        return new DailyRecommendBannerVH(getActivity(), parent, R.layout.recommend_daily_vh_banner);
                    case DailyRecommendViewEntity.VIEW_TYPE_MAIN_MENU:
                        return new DailyRecommendMenuVH(getActivity(), parent, R.layout.recommend_daily_vh_menu);
                    case DailyRecommendViewEntity.VIEW_TYPE_ARTICLE:
                        return new DailyRecommendArticleVH(getActivity(), parent, R.layout.recommend_daily_vh_article_list);
                    case DailyRecommendViewEntity.VIEW_TYPE_VIDEO:
                        return new DailyRecommendVideoVH(getActivity(), parent, R.layout.recommend_daily_vh_article_list);
                    case DailyRecommendViewEntity.VIEW_TYPE_PHOTO:
                        break;
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
    protected DailyRecommendPresenter getPresenter() {
        return new DailyRecommendPresenter(new DailyRecommendModel(), this);
    }

    @Override
    public void onSuccess(DailyRecommendEntity entity) {
        DailyRecommendViewEntity data;
        //android
        data = new DailyRecommendViewEntity(entity.getResults()
                                                  .getAndroid(), DailyRecommendViewEntity.VIEW_TYPE_ARTICLE);
        adapter.add(data);
        //ios
        data = new DailyRecommendViewEntity(entity.getResults()
                                                  .getIOS(), DailyRecommendViewEntity.VIEW_TYPE_ARTICLE);
        adapter.add(data);
//        //app
        data = new DailyRecommendViewEntity(entity.getResults()
                                                  .getApp(), DailyRecommendViewEntity.VIEW_TYPE_ARTICLE);
        adapter.add(data);
//        //拓展资源
        data = new DailyRecommendViewEntity(entity.getResults()
                                                  .getExpandBeanList(), DailyRecommendViewEntity.VIEW_TYPE_ARTICLE);
        adapter.add(data);
//        //视频
        data = new DailyRecommendViewEntity(entity.getResults().getVideoBeanList(), DailyRecommendViewEntity.VIEW_TYPE_VIDEO);
        adapter.add(data);
//        //瞎推荐
        data = new DailyRecommendViewEntity(entity.getResults()
                                                  .getRecommendBeanList(), DailyRecommendViewEntity.VIEW_TYPE_ARTICLE);
        adapter.add(data);
//        //福利
//        data = new DailyRecommendViewEntity(entity.getResults().getWelfareBeanList(), DailyRecommendViewEntity.VIEW_TYPE_PHOTO);
//        adapter.add(data);

        checkRvEmpty();
    }

    @Override
    public void onError(String msg) {
        checkRvEmpty();
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
        adapter.add(new DailyRecommendViewEntity(imgList, DailyRecommendViewEntity.VIEW_TYPE_BANNER));
    }

    /**
     * 主菜单
     */
    private void addMenu() {
        adapter.add(new DailyRecommendViewEntity(null, DailyRecommendViewEntity.VIEW_TYPE_MAIN_MENU));
    }
}
