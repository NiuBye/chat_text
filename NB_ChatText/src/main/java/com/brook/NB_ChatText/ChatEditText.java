package com.brook.NB_ChatText;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by brook.shi (niubye@126.com) on 10/17/2014.
 */
public class ChatEditText extends EditText {

    private int mTextSize;
    private final static int mAdjsutSize = 4;
    private InputFilter mEmotionFilter;

    public ChatEditText(Context context) {
        super(context);
        init();
    }

    public ChatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        mTextSize = (int)getTextSize()+14;
        //renderText();
        final Context context = getContext();
        mEmotionFilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ("".equals(source)) {
                    return null;
                }
                int unicode = Character.codePointAt(source, 0);
                final ChatIcon chatIcon = ChatIconManager.getInstance().getChatIcon(unicode);
                if (chatIcon != null) {
                    DynamicDrawableSpan drawableSpan = ChatIconRender.buildDrawableSpan(context, mTextSize, chatIcon);
                    SpannableString emotionSpanned = new SpannableString(source.toString());
                    emotionSpanned.setSpan(drawableSpan, 0, source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return emotionSpanned;
                } else {
                    return source;
                }
            }
        };
        setFilters(new InputFilter[]{mEmotionFilter});
    }

    private void renderText()
    {
        ChatIconRender.renderText(getContext(), getText(), mTextSize);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        renderText();
    }
}
