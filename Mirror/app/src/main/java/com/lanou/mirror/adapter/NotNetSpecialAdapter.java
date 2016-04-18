package com.lanou.mirror.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.greendao.SpecialDao;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.tool.ShowToast;

/**
 * Created by dllo on 16/4/7.
 */
public class NotNetSpecialAdapter extends RecyclerView.Adapter<NotNetSpecialAdapter.NotNetHolderSpecialAdapter>{
    private ImageLoaderHelper imageLoaderHelper;
    private SpecialDao specialDao;

    public NotNetSpecialAdapter(SpecialDao specialDao) {
        this.specialDao = specialDao;
        imageLoaderHelper=new ImageLoaderHelper();
    }

    @Override
    public NotNetHolderSpecialAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special,parent,false);
        NotNetHolderSpecialAdapter notNetHolderSpecialAdapter=new NotNetHolderSpecialAdapter(view);
        return notNetHolderSpecialAdapter;
    }

    @Override
    public void onBindViewHolder(NotNetHolderSpecialAdapter holder, int position) {
       holder.specialTitle.setText(specialDao.loadAll().get(position).getStory_title());
        imageLoaderHelper.loadImage(specialDao.loadAll().get(position).getStory_img(),holder.specialIv);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
    }

    @Override
    public int getItemCount() {
        return specialDao!=null?specialDao.loadAll().size():0;
    }


    public class NotNetHolderSpecialAdapter extends RecyclerView.ViewHolder{
        TextView specialTitle;
        ImageView specialIv;
        public NotNetHolderSpecialAdapter(View itemView) {
            super(itemView);
            specialTitle= (TextView) itemView.findViewById(R.id.special_title);
            specialIv= (ImageView) itemView.findViewById(R.id.special_iv);

        }
    }

}
