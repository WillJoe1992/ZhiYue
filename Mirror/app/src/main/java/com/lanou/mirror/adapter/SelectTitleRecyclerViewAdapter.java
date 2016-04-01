package com.lanou.mirror.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/3/31.
 */
public class SelectTitleRecyclerViewAdapter extends RecyclerView.Adapter<SelectTitleRecyclerViewAdapter.TitleViewHolder> {
    private Context context;
    private SelectTitleRecyclerBean selectTitleRecyclerBean;
    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans =new ArrayList<>();


     private ClickListener clickListener ;

    public SelectTitleRecyclerViewAdapter(Context context,ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans) {
        this.context = context;
        this.selectTitleRecyclerBeans=selectTitleRecyclerBeans;

    }


    @Override
    public SelectTitleRecyclerViewAdapter.TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHome = LayoutInflater.from(context).inflate(R.layout.item_select_title_recyclerview, parent, false);
        return new TitleViewHolder(viewHome);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        SelectTitleRecyclerBean selectTitleRecyclerBean = selectTitleRecyclerBeans.get(position);
        holder.tvAll.setText(selectTitleRecyclerBean.getTitleName());
        holder.position=position;
        if(selectTitleRecyclerBean.isUnderLine()){
            holder.underLineAll.setVisibility(View.VISIBLE);
            holder.tvAll.setTextColor(0xFFffffff);
        }
    }


    @Override
    public int getItemCount() {
        return selectTitleRecyclerBeans.size();
    }



    class TitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout lineAll;
        private TextView tvAll;
        private ImageView underLineAll;
        private int position;

        public TitleViewHolder(final View itemView) {
            super(itemView);
            lineAll = (LinearLayout) itemView.findViewById(R.id.line_all);
            tvAll = (TextView) itemView.findViewById(R.id.tv_all);
            underLineAll = (ImageView) itemView.findViewById(R.id.under_line_all);

            lineAll.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
//            SelectTitleRecyclerBean selectTitleRecyclerBean = selectTitleRecyclerBeans.get(position);
//
//            Intent intent =new Intent();
//            intent.putExtra("n", tvAll.getText());

            clickListener.ClickListener(position);

            Log.d("TitleViewHolder", "!!!!!!!!!!");
            Log.e("TitleViewHolder", "position:" + position);

            SelectTitleRecyclerBean selectTitleRecyclerBean = selectTitleRecyclerBeans.get(position);


        }
    }
    public interface ClickListener {
        void ClickListener(int popMenuPosition);
    }
    public void setPositionClickListener(ClickListener listener){
        this.clickListener = listener;
    }
}
