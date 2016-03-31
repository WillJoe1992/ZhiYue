package com.lanou.mirror.tool;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by dllo on 16/3/30.
 */
public class GetScreenHeight {
    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
}
