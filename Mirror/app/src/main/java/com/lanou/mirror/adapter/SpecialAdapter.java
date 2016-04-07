package com.lanou.mirror.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.net.NetImageLoader;

/**
 * Created by dllo on 16/4/1.
 */
public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.HolderSpecialAdapter>{
private JSONSpecial jsonSpecial;
private SpecialOnClick specialOnClick;
    private NetImageLoader netImageLoader;
    public SpecialAdapter(JSONSpecial jsonSpecial) {
        this.jsonSpecial = jsonSpecial;
        netImageLoader=new NetImageLoader();
    }

    @Override
    public HolderSpecialAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special,parent,false);
        HolderSpecialAdapter holderSpecialAdapter=new HolderSpecialAdapter(view);
        return holderSpecialAdapter;
    }

    @Override
    public void onBindViewHolder(HolderSpecialAdapter holder, final int position) {
      holder.specialTitle.setText(jsonSpecial.getData().getList().get(position).getStory_title());
      netImageLoader.getImgOfLoader(holder.specialIv, jsonSpecial.getData().getList().get(position).getStory_img());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specialOnClick.specialOnClick(position);
                Log.d("Sysout", "relativeLayout");
            }
        });
    }

    @Override
    public int getItemCount() {
        return jsonSpecial.getData().getList().size();
    }

    public class HolderSpecialAdapter extends RecyclerView.ViewHolder{
        TextView specialTitle;
        ImageView specialIv;
        public HolderSpecialAdapter(View itemView) {
            super(itemView);
            specialTitle= (TextView) itemView.findViewById(R.id.special_title);
            specialIv= (ImageView) itemView.findViewById(R.id.special_iv);
        }
    }
   public interface SpecialOnClick{
        void specialOnClick(int position);
    }
    public void MySpecialOnClick(SpecialOnClick specialOnClick){
        this.specialOnClick=specialOnClick;
    }
}
