package com.brook.NB_ChatText;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;

/**
 * Created by shibrook on 14-10-18.
 */
public class ChatIconRender {

    public static void renderText(final Context context, Spannable text, final int textSize){

        int length = text.length();
        DynamicDrawableSpan[] oldSpans = text.getSpans(0, length, DynamicDrawableSpan.class);
        for(DynamicDrawableSpan span : oldSpans){
            text.removeSpan(span);
        }

        int skip = 0;

        for(int i=0;i<length;i+=skip){

            int unicode = Character.codePointAt(text, i);
            skip = Character.charCount(unicode);
            final ChatIcon chatIcon = ChatIconManager.getInstance().getChatIcon(unicode);

            if(chatIcon != null){
                text.setSpan(new DynamicDrawableSpan() {
                    @Override
                    public Drawable getDrawable() {
                        Drawable drawable = context.getResources().getDrawable(chatIcon.getIconId());
                        drawable.setBounds(0, 0, textSize, textSize);
                        return drawable;
                    }
                }, i, i+skip, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
}
