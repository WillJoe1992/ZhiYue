package com.lanou.mirror.special;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lanou.mirror.R;

/**
 * Created by SAZ 16/3/29.
 */
public class SpecialPictureAdapter extends RecyclerView.Adapter<SpecialPictureAdapter.SpecialPictureHolder> {
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public SpecialPictureAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SpecialPictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mHeaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_content_picture_item, parent, false);
        if (mHeaderView!= null && viewType == TYPE_HEADER) {
            SpecialPictureHolder specialPictureHolder = new SpecialPictureHolder(mHeaderView);
            ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureItemRv.getLayoutParams();
            layoutParams.height = getScreenHeight(context);
            specialPictureHolder.specialContentPictureItemRv.setLayoutParams(layoutParams);
            return specialPictureHolder;
        }else {
        View nullView = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_content_picture_null_item, parent, false);
        SpecialPictureHolder specialPictureHolder = new SpecialPictureHolder(nullView);
        ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureNullItemView.getLayoutParams();
        layoutParams.height = getScreenHeight(context);
        specialPictureHolder.specialContentPictureNullItemView.setLayoutParams(layoutParams);
        return specialPictureHolder;
        }
    }

    @Override
    public void onBindViewHolder(SpecialPictureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class SpecialPictureHolder extends RecyclerView.ViewHolder {
        RelativeLayout specialContentPictureItemRv;
        LinearLayout specialContentPictureNullItemView;

        public SpecialPictureHolder(View itemView) {
            super(itemView);
            specialContentPictureItemRv = (RelativeLayout) itemView.findViewById(R.id.special_content_picture_item_rv);
            specialContentPictureNullItemView = (LinearLayout) itemView.findViewById(R.id.special_content_picture_null_item_view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
}
