package com.lanou.mirror.special;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/4/1.
 */
public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.HolderSpecialAdapter>{

    @Override
    public HolderSpecialAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HolderSpecialAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderSpecialAdapter extends RecyclerView.ViewHolder{
        public HolderSpecialAdapter(View itemView) {
            super(itemView);
        }
    }
}
