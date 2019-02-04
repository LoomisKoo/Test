package com.example.administrator.test.viewholder.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.base.adapter.BaseViewHolder;
import com.example.administrator.test.entity.KnowledgeSystemEntity;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.viewholder.knowledgesystem.KnowledgeSystemVH;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class MovieDetailActorsVH extends BaseViewHolder {
    private RecyclerView recyclerView;

    public MovieDetailActorsVH(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
        recyclerView = getView(R.id.rv_actors);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //设置不需要焦点 否则切换tab后，嵌套的recyclerview会自动滚动
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.requestFocus();
    }

    public void setData(MovieDetailEntity entity) {
        List<MovieDetailEntity.ClerkEntity> clerkEntityList = new ArrayList<>();
        //导演和主演是分开的数据，这里做拼接
        clerkEntityList.addAll(entity.getDirectors());
        clerkEntityList.addAll(entity.getCasts());
        recyclerView.setAdapter(new ActorAdapter(clerkEntityList));
    }


    class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorAdapterVH> {
        private List<MovieDetailEntity.ClerkEntity> data;

        public ActorAdapter(List<MovieDetailEntity.ClerkEntity> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ActorAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //实例化得到Item布局文件的View对象
            View v = LayoutInflater.from(context)
                                   .inflate(R.layout.movie_detail_actors_vh_item, parent, false);
            ActorAdapterVH vh = new ActorAdapterVH(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ActorAdapterVH holder, int position) {
            AnimatorHelper.setViewTouchListener(holder.itemView);
            String imgUrl = data.get(position)
                                .getAvatars()
                                .getLarge();
            //头像
            Glide.with(context)
                 .load(imgUrl)
                 .into(holder.ivPortrait);
            //姓名
            String name  = data.get(position)
                               .getName();
            //职位
            String clerk = data.get(position)
                               .getClerkType() == MovieDetailEntity.ClerkEntity.CLERK_TYPE_ACTOR ? "演员" : "导演";

            holder.tvName.setText(name);
            holder.tvJobs.setText(clerk);

            //点击item跳转web
            holder.itemView.setOnClickListener(v -> {
                String actorInfoUrl = data.get(position)
                                          .getAlt();
                String actorName = data.get(position)
                                       .getName();

                ArouteHelper.buildWebWithAnimator(context, actorName, actorInfoUrl);
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ActorAdapterVH extends RecyclerView.ViewHolder {
            ImageView ivPortrait;
            TextView  tvName;
            TextView  tvJobs;

            public ActorAdapterVH(@NonNull View itemView) {
                super(itemView);
                ivPortrait = itemView.findViewById(R.id.iv_portrait);
                tvName = itemView.findViewById(R.id.tv_name);
                tvJobs = itemView.findViewById(R.id.tv_jobs);
            }
        }
    }
}
