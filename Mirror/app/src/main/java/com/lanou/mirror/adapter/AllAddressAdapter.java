package com.lanou.mirror.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.AllAddressActivity;
import com.lanou.mirror.activity.BuyActivity;
import com.lanou.mirror.activity.ReviseAddressActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONAddress;

import java.util.ArrayList;

/**
 * Created by wyc on 16/4/15.
 */
public class AllAddressAdapter extends BaseAdapter {
    private JSONAddress data;
    private Context context;
    private onRightItemClickListener mListener;
    private int mRigthWidth = 0;
    private String token;
    private AllAddressItemListener listener;




    public AllAddressAdapter(JSONAddress data, Context context, String token, int mRigthWidth,AllAddressItemListener listener) {

        super();
        this.data = data;
        this.mRigthWidth = mRigthWidth;
        this.context = context;
        this.token = token;
        this.listener=listener;
    }

    public void setData(JSONAddress data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.getData().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getData().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.item_all_address_listview, null);

            holder = new MyViewHolder(convertView);

            convertView.setTag(holder);

        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.position = position;
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(mRigthWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        holder.itemRight.setLayoutParams(lp1);

        Log.d("position", String.valueOf(position));
        holder.numberTv.setText("电话 ：" + data.getData().getList().get(position).getCellphone());
        holder.addressTv.setText("地址: " + data.getData().getList().get(position).getAddr_info());
        holder.receiveTv.setText("收件人: " + data.getData().getList().get(position).getUsername());
        holder.editIv.setOnClickListener(new View.OnClickListener() { //编辑监听，跳转编辑页面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviseAddressActivity.class);
                intent.putExtra("name", data.getData().getList().get(position).getUsername());
                intent.putExtra("address", data.getData().getList().get(position).getAddr_info());
                intent.putExtra("phoneNumber", data.getData().getList().get(position).getCellphone());
                intent.putExtra("addressId", data.getData().getList().get(position).getAddr_id());
                context.startActivity(intent);
                ((AllAddressActivity)context).finish();

            }


        }
        );
        return convertView;
    }

    class MyViewHolder {
        private TextView receiveTv, addressTv, numberTv, rigthTv;
        private ImageView editIv;
        private RelativeLayout itemRight,item_left;
        private int position;


        MyViewHolder(View view) {
            itemRight = (RelativeLayout) view.findViewById(R.id.item_right);
            receiveTv = (TextView) view.findViewById(R.id.item_listview_activity_myaddress_receiveman);
            addressTv = (TextView) view.findViewById(R.id.item_listview_activity_myaddress_address);
            numberTv = (TextView) view.findViewById(R.id.item_listview_activity_myaddress_number);
            editIv = (ImageView) view.findViewById(R.id.item_listview_activity_myaddress_edit);
            rigthTv = (TextView) view.findViewById(R.id.item_right_txt);
            item_left= (RelativeLayout) view.findViewById(R.id.item_left);
            itemRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRightItemClick(v, position);
                    }
                }
            });
            item_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 listener.myItemListener(receiveTv.getText().toString(),addressTv.getText().toString(),numberTv.getText().toString());
                }
            });

        }

    }
    public interface AllAddressItemListener {

        void  myItemListener(String receive,String address,String number);
    }

    public void setOnRightItemClickListener(onRightItemClickListener listener) {
        mListener = listener;
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }

}
