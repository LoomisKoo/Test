package com.example.administrator.test.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.KnowledgeSystemEntity;
import com.example.administrator.test.util.ArouterHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder
 * @ClassName: KnowledgeSystemVH
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/1/13 5:48 PM
 * @UpdateUser:
 * @UpdateDate: 2019/1/13 5:48 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KnowledgeSystemVH extends BaseViewHolder {
    @BindView(R.id.tv_title)
    TextView     tvTitle;
    @BindView(R.id.rv_knowledge_point)
    RecyclerView rvKnowledgePoint;

    private KnowledgeSystemAdapter adapter;

    public KnowledgeSystemVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        rvKnowledgePoint = retrieveView(R.id.rv_knowledge_point);
        tvTitle = retrieveView(R.id.tv_title);
        //TODO 此处可用流式布局进行优化
        rvKnowledgePoint.setLayoutManager(new LinearLayoutManager(context));

    }

    public void setData(KnowledgeSystemEntity.KnowledgeType knowledgeType) {
        tvTitle.setText(knowledgeType.getName());
        rvKnowledgePoint.setAdapter(adapter = new KnowledgeSystemAdapter(knowledgeType.getChildren()));
    }

    class KnowledgeSystemAdapter extends RecyclerView.Adapter<KnowledgeSystemAdapter.KnowledgeSystemPointVH> {
        private List<KnowledgeSystemEntity.KnowledgeType.KnowledgePoint> data;

        public KnowledgeSystemAdapter(List<KnowledgeSystemEntity.KnowledgeType.KnowledgePoint> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public KnowledgeSystemPointVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //实例化得到Item布局文件的View对象
            View v = LayoutInflater.from(context).inflate(R.layout.play_android_item_knowledge_point, parent, false);
            AnimatorHelper.setViewTouchListener(v);
            v.setOnClickListener(v1 -> {
                KnowledgeSystemEntity.KnowledgeType.KnowledgePoint entity = data.get(getAdapterPosition());
                ARouter.getInstance().build(ArouterHelper.ROUTE_ACTIVITY_ARTICLE_LIST).withFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).withInt("x", AnimatorHelper.getDownX()).withInt("y", AnimatorHelper.getDownY()).withInt("cid", entity.getId()).withString("title", entity.getName()).navigation();
            });
            KnowledgeSystemPointVH vh = new KnowledgeSystemPointVH(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull KnowledgeSystemPointVH holder, int position) {
            holder.tvPoint.setText(data.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class KnowledgeSystemPointVH extends RecyclerView.ViewHolder {
            TextView tvPoint;

            public KnowledgeSystemPointVH(@NonNull View itemView) {
                super(itemView);
                tvPoint = itemView.findViewById(R.id.tv_point);
            }
        }
    }

}
