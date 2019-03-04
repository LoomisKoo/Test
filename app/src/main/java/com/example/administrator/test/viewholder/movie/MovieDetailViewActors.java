package com.example.administrator.test.viewholder.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.test.R;
import com.example.administrator.test.animation.AnimatorHelper;
import com.example.administrator.test.entity.MovieDetailEntity;
import com.example.administrator.test.util.ArouteHelper;
import com.example.administrator.test.util.GlideCornersTransform;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Test
 * @Package: com.example.administrator.test.viewholder.movie
 * @ClassName: MovieDetailViewInfo
 * @Description: java类作用描述
 * @Author: koo
 * @CreateDate: 2019/2/4 3:36 PM
 * @UpdateUser:
 * @UpdateDate: 2019/2/4 3:36 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MovieDetailViewActors {
    @BindView(R.id.rv_actors)
    RecyclerView rvActors;

    private Context context;

    public MovieDetailViewActors(Context context, View rootView) {
        this.context = context;
        ButterKnife.bind(this, rootView);
        //设置不需要焦点 否则切换tab后，嵌套的recyclerview会自动滚动
        rvActors.setFocusableInTouchMode(false);
        rvActors.requestFocus();
        rvActors.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setData(MovieDetailEntity entity) {
        List<MovieDetailEntity.ClerkEntity> clerkEntityList = new ArrayList<>();
        //导演和主演是分开的数据，这里做拼接
        clerkEntityList.addAll(entity.getDirectors());
        clerkEntityList.addAll(entity.getCasts());
        rvActors.setAdapter(new ActorAdapter(clerkEntityList));
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

        @SuppressLint("CheckResult")
        @Override
        public void onBindViewHolder(@NonNull ActorAdapterVH holder, int position) {
            AnimatorHelper.setViewTouchListener(holder.itemView);
            String imgUrl = data.get(position)
                                .getAvatars()
                                .getLarge();
            //头像
            RequestOptions options = new RequestOptions();
            Drawable defaultDrawable = context.getResources()
                                              .getDrawable(R.mipmap.ic_actor_avator_default);
            options.placeholder(defaultDrawable)
                   .error(defaultDrawable);

            Glide.with(context)
                 .setDefaultRequestOptions(options)
                 .load(imgUrl)
                 .apply(RequestOptions.bitmapTransform(new GlideCornersTransform(10)))
                 .into(holder.ivPortrait);
            //姓名
            String name = data.get(position)
                              .getName();
            //职位
            String clerk = data.get(position)
                               .getClerkType() == MovieDetailEntity.ClerkEntity.CLERK_TYPE_ACTOR ? context.getString(R.string.movie_detail_actor) : context.getString(R.string.movie_detail_director);
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
            @BindView(R.id.iv_portrait)
            ImageView ivPortrait;
            @BindView(R.id.tv_name)
            TextView  tvName;
            @BindView(R.id.tv_jobs)
            TextView  tvJobs;

            public ActorAdapterVH(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
