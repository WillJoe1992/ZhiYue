package com.lanou.mirror.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by dllo on 16/3/31.
 */
public class SelectTitleActivity extends BaseActivity{

    private TextView tvTop,tvAll,tvFlatGlass,tvSunGlass,tvSpecial,tvShoppingCart,tvGoback,tvExit,tvBottom;
    private LinearLayout lineAll,lineFlatGlass,lineSunGlass,lineSpecial,lineShoppingCart,lineGoback,lineExit;
    private ImageView underLineAll,underLineFlatGlass,underLineSunGlass,underLineSpecial,underShoppingCart,underGoback,underExit;

    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTop=BlindView(R.id.tv_top);
        tvBottom=BlindView(R.id.tv_bottom);
        selectTitleRc=BlindView(R.id.select_title_rc);

//        selectTitleRecyclerViewAdapter = new SelectTitleRecyclerViewAdapter(getContext());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        homePageRecyclerView.setLayoutManager(gridLayoutManager);
//        homePageRecyclerView.setAdapter(homePagerRecyclerViewAdapter);



    }

    @Override
    protected int setcontent() {
        return R.layout.activity_select_title;
    }
}
