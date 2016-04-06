package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.lanou.mirror.R;

/**
 * Created by wyc on 16/4/6.
 */
public class AtlasAdapter extends RecyclerView.Adapter {
    private final int TYPE_VIDEO = 0;
    private final int TYPE_IMAGE = 1;

    public AtlasAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_VIDEO:
                View videoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_atlas_vadio, null);
                holder = new AtlasVideoViewHolder(videoView);
                break;
            case TYPE_IMAGE:
                View imageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_atlas_image, null);
                holder = new AtlasImageViewHolder(imageView);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_VIDEO:

                break;
            case TYPE_IMAGE:

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class AtlasImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public AtlasImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.atlas_item_image);
        }
    }

    class AtlasVideoViewHolder extends RecyclerView.ViewHolder {
        private VideoView videoView;

        public AtlasVideoViewHolder(View itemView) {
            super(itemView);
            videoView = (VideoView) itemView.findViewById(R.id.atlas_item_video);
        }
    }
}
