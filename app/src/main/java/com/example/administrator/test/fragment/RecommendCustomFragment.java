package com.example.administrator.test.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.example.administrator.test.R;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.base.adapter.QuickDelegateAdapter;
import com.example.administrator.test.base.fragment.BaseListFragment;
import com.example.administrator.test.entity.RecommendCustomEntity;
import com.example.administrator.test.entity.view.BaseViewEntity;
import com.example.administrator.test.mvp.contract.RecommendCustomContract;
import com.example.administrator.test.mvp.model.RecommendCustomModel;
import com.example.administrator.test.mvp.presenter.RecommendCustomPresenter;
import com.example.administrator.test.viewholder.recommend.CustomArticleVH;
import com.example.administrator.test.viewholder.recommend.CustomTitleVH;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.fragment
 * @ClassName: RecommendCustomFragment
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/26 1:48 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/26 1:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendCustomFragment extends BaseListFragment<BaseViewEntity, RecommendCustomPresenter> implements RecommendCustomContract.View {
    /**
     * 选择菜单的icon
     */
    private static final int[]          MENU_IC_LIST = new int[]{R.mipmap.recommend_ic_custom_all, R.mipmap.recommend_ic_custom_ios, R.mipmap.recommend_ic_custom_app, R.mipmap.recommend_ic_custom_web, R.mipmap.recommend_ic_custom_video, R.mipmap.recommend_ic_custom_more};
    /**
     * 定制类型
     */
    private              String[]       dataTypes;
    /**
     * 定制类型的请求码
     */
    private              String[]       dataRequestTypes;
    /**
     * 当前类型
     */
    private              String         dataType;
    /**
     * 当前类型请求码
     */
    private              String         dataRequestType;
    /**
     * 爆炸菜单按钮
     */
    private              BoomMenuButton menuButton;

    @Override
    protected void getData(int page, int pageSize) {
        if (0 == page) {
            //加入title布局，不需要数据
            BaseViewEntity entity = new BaseViewEntity(dataType, BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_TITLE);
            adapter.add(entity);
        }
        //该api是page是从1开始
        page++;
        //请求文章列表数据
        presenter.getCustomData(dataRequestType, pageSize, page);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView(View view) {
        super.initView(view);
        menuButton = new BoomMenuButton(getContext());
        menuButton.setId(R.id.view_menu_btn);
        basePagerListRoot.addView(menuButton);
    }

    @Override
    protected void initData() {
        super.initData();
        dataTypes = getActivity().getResources()
                                 .getStringArray(R.array.data_type);
        dataRequestTypes = getActivity().getResources()
                                        .getStringArray(R.array.request_data_type);

        dataType = dataTypes[0];
        dataRequestType = dataRequestTypes[0];

        initMenuBtnLayout();
        initMenuBtnEvent();
    }

    @Override
    protected QuickDelegateAdapter getAdapter() {
        return new QuickDelegateAdapter<BaseViewEntity>(getContext(), R.layout.recommend_welfare_vh_item) {
            @Override
            protected void onSetItemData(BaseViewHolder holder, BaseViewEntity item, int viewType, int position) {
                switch (viewType) {
                    case BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_TITLE:
                        if (item.getData() instanceof String) {
                            ((CustomTitleVH) holder).setData((String) item.getData());
                        }
                        break;
                    case BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_ARTICLE_LIST:
                        if (item.getData() instanceof RecommendCustomEntity.CustomInfoEntity) {
                            ((CustomArticleVH) holder).setData((RecommendCustomEntity.CustomInfoEntity) item.getData());
                        }
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
                    case BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_TITLE:
                        return new CustomTitleVH(context, parent, R.layout.recommend_custom_title_vh);
                    case BaseViewEntity.RECOMMEND_CUSTOM_VIEW_TYPE_ARTICLE_LIST:
                        return new CustomArticleVH(context, parent, R.layout.recommend_custom_article_vh);
                    default:
                        break;
                }
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }

            @Override
            public int getItemViewType(int position) {
                return data.get(position)
                           .getViewType();
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
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
    protected RecommendCustomPresenter getPresenter() {
        return new RecommendCustomPresenter(new RecommendCustomModel(), this);
    }

    @Override
    public void onSuccess(BaseViewEntity entity) {
        stopRefresh();
        adapter.add(entity);
    }

    @Override
    public void onError(String msg) {
        stopRefresh();
        showToast(msg);
    }

    /**
     * 初始化菜单按钮布局
     */
    private void initMenuBtnLayout() {
        ConstraintSet set = new ConstraintSet();
        set.clone(basePagerListRoot);
        set.connect(R.id.view_menu_btn, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        set.connect(R.id.view_menu_btn, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        set.setMargin(R.id.view_menu_btn, ConstraintSet.END, ConvertUtils.dp2px(10));
        set.setMargin(R.id.view_menu_btn, ConstraintSet.BOTTOM, ConvertUtils.dp2px(100));
        set.applyTo(basePagerListRoot);
    }


    public void resetMenuBtnLayout(int offset) {

        if (userVisibleManager.isVisibleToUser()) {
            ConstraintSet set = new ConstraintSet();
            set.clone(basePagerListRoot);
            set.setMargin(R.id.view_menu_btn, ConstraintSet.BOTTOM, ConvertUtils.dp2px(100) + offset);
            set.applyTo(basePagerListRoot);
        }
    }


    /**
     * 初始化菜单按钮的事件
     */
    private void initMenuBtnEvent() {
        menuButton.setButtonEnum(ButtonEnum.TextInsideCircle);
        menuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_6);
        menuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_1);
        int imgPadding = ConvertUtils.dp2px(15);
        for (int i = 0; i < menuButton.getButtonPlaceEnum()
                                      .buttonNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(MENU_IC_LIST[i])
                    .normalText(dataTypes[i])
                    .imagePadding(new Rect(imgPadding, imgPadding, imgPadding, imgPadding))
                    .listener(index -> {
                        adapter.clear();
                        basePagerListRefreshLayout.autoRefresh();
                        dataType = dataTypes[index];
                        dataRequestType = dataRequestTypes[index];
                    });
            menuButton.addBuilder(builder);
        }
    }
}
