package com.brook.NB_ChatText;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shibrook on 14-10-18.
 */
public class ChatTextView extends TextView {

    private int mTextSize;

    public ChatTextView(Context context) {
        super(context);
        init();
    }

    public ChatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
        mTextSize = (int)getTextSize();
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        ChatIconRender.renderText(getContext(), builder, mTextSize);
        super.setText(builder, type);
    }
}
