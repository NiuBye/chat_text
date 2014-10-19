package com.brook.NB_ChatText;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shibrook on 14-10-18.
 */
public class Utils {

    public static int sp2px(Context context, float sp){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * fontScale + 0.5f);
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
}
