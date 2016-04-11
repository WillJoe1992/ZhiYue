package com.lanou.mirror.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONAtlas;
import com.lanou.mirror.net.NetImageLoader;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by wyc on 16/4/6.
 */
public class AtlasAdapter extends RecyclerView.Adapter {
    private final int TYPE_VIDEO = 0;
    private final int TYPE_IMAGE = 1;
    private JSONAtlas data;

    public AtlasAdapter(JSONAtlas data) {
        this.data = data;
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
        String atlasType = data.getData().getList().get(0).getWear_video().get(0).getType();

        switch (type) {
            case TYPE_VIDEO:
                AtlasVideoViewHolder videoViewHolder = (AtlasVideoViewHolder) holder;
                if (Integer.valueOf(atlasType) == 8) {
                    videoViewHolder.jcVideoPlayer.setUp(data.getData().getList().get(0).getWear_video().get(0).getData()," ");

                }
                break;
            case TYPE_IMAGE:
                AtlasImageViewHolder imageViewHolder = (AtlasImageViewHolder) holder;
                if (Integer.valueOf(atlasType) != 1) {
                    NetImageLoader netImageLoader = new NetImageLoader();
                    netImageLoader.getImgOfLoader(imageViewHolder.imageView, data.getData().getList().get(0).getWear_video().get(position+1).getData());
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_VIDEO;
        } else {
            return TYPE_IMAGE;

        }
    }

    @Override
    public int getItemCount() {
        return data.getData().getList().size();
    }


    class AtlasImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public AtlasImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.atlas_item_image);
        }
    }

    class AtlasVideoViewHolder extends RecyclerView.ViewHolder {
        private JCVideoPlayer jcVideoPlayer;

        public AtlasVideoViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayer) itemView.findViewById(R.id.atlas_jcvideo);
        }
    }
}
