package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.tool.GetScreenHeight;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by SAZ 16/3/29.
 */
public class SpecialPictureAdapter extends RecyclerView.Adapter<SpecialPictureAdapter.SpecialPictureHolder> {
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    JSONSpecial jsonSpecial;
    SpecialOnClickBack specialOnClickBack;
    int MainPosition;
    public SpecialPictureAdapter(Context context, JSONSpecial jsonSpecial,int MainPosition) {
        this.context = BaseApplication.getContext();
        this.jsonSpecial = jsonSpecial;
        this.MainPosition=MainPosition;
    }

    @Override
    public SpecialPictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载有内容的布局
        mHeaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_content_picture, parent, false);
        SpecialPictureHolder specialPictureHolder=null;
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            specialPictureHolder = new SpecialPictureHolder(mHeaderView);
            ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureItemRv.getLayoutParams();
            layoutParams.height= GetScreenHeight.getScreenHeight(context);
            specialPictureHolder.specialContentPictureItemRv.setLayoutParams(layoutParams);
            specialPictureHolder.shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareSDK.initSDK(BaseApplication.getContext());
                    OnekeyShare oks = new OnekeyShare();
                    //关闭sso授权
                    oks.disableSSOWhenAuthorize();

                    // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                    oks.setTitle("Mirror");
                    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                    oks.setTitleUrl(jsonSpecial.getData().getList().get(0).getStory_url());
                    // text是分享文本，所有平台都需要这个字段
                    oks.setText("  ");
                    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                    // url仅在微信（包括好友和朋友圈）中使用
                    oks.setUrl(jsonSpecial.getData().getList().get(0).getStory_url());
                    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                    oks.setComment("  ");
                    // site是分享此内容的网站名称，仅在QQ空间使用
//                    oks.setSite(getString(R.string.app_name));
                    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                    oks.setSiteUrl(jsonSpecial.getData().getList().get(0).getStory_url());

                    // 启动分享GUI
                    oks.show(BaseApplication.getContext());

                }
            });


        } else {
            //加载空的布局
            View nullView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_content_picture_null, parent, false);
            specialPictureHolder=new SpecialPictureHolder(nullView);
            ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureNullItemView.getLayoutParams();
            layoutParams.height= GetScreenHeight.getScreenHeight(context);
            specialPictureHolder.specialContentPictureNullItemView.setLayoutParams(layoutParams);
        }
        return specialPictureHolder;
    }

    @Override
    public void onBindViewHolder(SpecialPictureHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEADER){
            holder.specialContentItemShareTvContent.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSubTitle());
            holder.specialContentItemShareTvTitle.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSmallTitle());
            holder.specialContentItemShareTvBigTitle.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSmallTitle());
            holder.specialBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    specialOnClickBack.SpecialOnClickBack();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array()!=null?
                jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().size():0;
    }

    class SpecialPictureHolder extends RecyclerView.ViewHolder {
        RelativeLayout specialContentPictureItemRv;
        LinearLayout specialContentPictureNullItemView;
        ImageView specialBack;
        TextView specialContentItemShareTvTitle,specialContentItemShareTvBigTitle,specialContentItemShareTvContent;
        Button shareBtn;
        public SpecialPictureHolder(View itemView) {
            super(itemView);
            specialContentPictureItemRv = (RelativeLayout) itemView.findViewById(R.id.special_content_picture_item_rv);
            specialContentPictureNullItemView = (LinearLayout) itemView.findViewById(R.id.special_content_picture_null_item_view);
            specialContentItemShareTvTitle= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_title);
            specialContentItemShareTvBigTitle= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_big_title);
            specialContentItemShareTvContent= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_content);
            shareBtn = (Button) itemView.findViewById(R.id.special_content_item_share_btn);
            specialBack= (ImageView) itemView.findViewById(R.id.special_back);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }
    public interface SpecialOnClickBack{
        void SpecialOnClickBack();
    }
    public void SpecialOnClickBack(SpecialOnClickBack specialOnClickBack){
        this.specialOnClickBack=specialOnClickBack;
    }
}