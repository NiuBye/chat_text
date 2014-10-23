package com.brook.NB_ChatText;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shibrook on 14-10-18.
 */
public class Utils {

    public static int sp2px(Context context, float sp){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * fontScale + 0.5f);
    }

    public static int dip2px(Context context,int dipValue) {
        float reSize = context.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static int px2dip(Context context,int pxValue) {
        float reSize = context.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static void hideKeyboard(final View editView){
        ((InputMethodManager) editView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(editView.getWindowToken(), 0);
    }

    public static void showKeyboard(final View editView){
        editView.requestFocus();
        editView.post(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) editView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .showSoftInput(editView, 0);
            }
        });
    }

    public static int getScreenHeight(Activity paramActivity) {
        Display display = paramActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getStatusBarHeight(Activity paramActivity) {
        Rect localRect = new Rect();
        paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        return localRect.top;

    }

    public static int getActionBarHeight(Activity paramActivity) {
        // test on samsung 9300 android 4.1.2, this value is 96px
        // but on galaxy nexus android 4.2, this value is 146px
        // statusbar height is 50px
        // I guess 4.1 Window.ID_ANDROID_CONTENT contain statusbar
        int contentViewTop = paramActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

//        return contentViewTop - getStatusBarHeight(paramActivity);

        int[] attrs = new int[] { android.R.attr.actionBarSize };
        TypedArray ta = paramActivity.obtainStyledAttributes(attrs);
        return ta.getDimensionPixelSize(0, dip2px(paramActivity, 48));
    }

    // below status bar,include actionbar, above softkeyboard
    public static int getAppHeight(Activity paramActivity) {
        Rect localRect = new Rect();
        paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        return localRect.height();
    }

    // below actionbar, above softkeyboard
    public static int getAppContentHeight(Activity paramActivity) {
        return getScreenHeight(paramActivity) - getStatusBarHeight(paramActivity)
                - getActionBarHeight(paramActivity) - getKeyboardHeight(paramActivity);
    }

    public static int getKeyboardHeight(Activity paramActivity) {
        int height = getScreenHeight(paramActivity) - getStatusBarHeight(paramActivity)
                - getAppHeight(paramActivity);
        SharedPreferences sp = paramActivity.getSharedPreferences("key", Context.MODE_PRIVATE);
        if (height == 0) {
            height = sp.getInt("keyboard_height", 400);
        }
        else {
            SharedPreferences.Editor et = sp.edit();
            et.putInt("keyboard_height", height);
            et.commit();
        }
        return height;
    }

    public static boolean isKeyBoardShow(Activity paramActivity) {
        int height = getScreenHeight(paramActivity) - getStatusBarHeight(paramActivity)
                - getAppHeight(paramActivity);
        return height != 0;
    }
}
