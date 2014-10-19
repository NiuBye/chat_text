package com.brook.NB_ChatText;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by brook.shi (niubye@126.com) on 10/17/2014.
 */
public class ChatEditText extends EditText {

    private int mTextSize;
    private final static int mAdjsutSize = 4;

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
        renderText();
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
