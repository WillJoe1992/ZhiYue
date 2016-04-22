package com.lanou.mirror.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONAtlas;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.tool.GalleryActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by wyc on 16/4/6.
 */
public class AtlasAdapter extends RecyclerView.Adapter {
    private final int TYPE_VIDEO = 0;
    private final int TYPE_IMAGE = 1;
    private JSONAtlas data;
    private Activity activity;
    private String[] urls=new String[10];

    public AtlasAdapter(Activity activity, JSONAtlas data) {
        this.data = data;
        this.activity=activity;
        for(int i= 0; i<data.getData().getList().get(0).getWear_video().size()-2;i++){
            urls[i]=data.getData().getList().get(0).getWear_video().get(i+2).getData();
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        String atlasType = data.getData().getList().get(0).getWear_video().get(0).getType();

        Log.d("AtlasAdapter", atlasType);
        switch (type) {
            case TYPE_VIDEO:
                AtlasVideoViewHolder videoViewHolder = (AtlasVideoViewHolder) holder;
                if (Integer.valueOf(atlasType) == 8) {
                    videoViewHolder.jcVideoPlayer.setUp(data.getData().getList().get(0).getWear_video().get(0).getData(), " ");


                    Log.d("AtlasAdapter", data.getData().getList().get(0).getWear_video().get(1).getData());
                        NetImageLoader netImageLoader = new NetImageLoader();
                        netImageLoader.getImgOfLoader(videoViewHolder.jcVideoPlayer.ivThumb,data.getData().getList().get(0).getWear_video().get(1).getData());

                }

                break;
            case TYPE_IMAGE:
                AtlasImageViewHolder imageViewHolder = (AtlasImageViewHolder) holder;
                if (Integer.valueOf(atlasType) != 1) {
                    NetImageLoader netImageLoader = new NetImageLoader();
                    netImageLoader.getImgOfLoader(imageViewHolder.imageView, data.getData().getList().get(0).getWear_video().get(position+1).getData());
                }


                imageViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int[] location = new int[2];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            Rect frame = new Rect();
                            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                            int statusBarHeight = frame.top;
                            v.getLocationOnScreen(location);
                            location[1] += statusBarHeight;
                        } else {
                            v.getLocationOnScreen(location);
                        }
                        v.invalidate();
                        int width = v.getWidth();
                        int height = v.getHeight();

                        Intent intent = new Intent(activity, GalleryActivity.class);
                        Bundle b = new Bundle();
                        b.putStringArray(GalleryActivity.PHOTO_SOURCE_ID,urls);
                        intent.putExtras(b);
                        intent.putExtra(GalleryActivity.PHOTO_SELECT_POSITION, position);
                        intent.putExtra(GalleryActivity.PHOTO_SELECT_X_TAG, location[0]);
                        intent.putExtra(GalleryActivity.PHOTO_SELECT_Y_TAG, location[1]);
                        intent.putExtra(GalleryActivity.PHOTO_SELECT_W_TAG, width);
                        intent.putExtra(GalleryActivity.PHOTO_SELECT_H_TAG, height);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(0, 0);
                    }
                });


                break;
            case 9:

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
