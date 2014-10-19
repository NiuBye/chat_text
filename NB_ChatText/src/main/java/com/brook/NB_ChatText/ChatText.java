package com.brook.NB_ChatText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.input.InputManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


/**
 * Created by shibrook on 14-10-18.
 */
public class ChatText extends LinearLayout {

    private ChatEditText mEditText;
    private ToggleButton mFaceBtn;
    private GridView mEmotionView;
    private ChatIconAdapter mAdapter;
    private LinearLayout mChatTextContainer;
    private InputMethodManager mInputMethodMgr;

    public ChatText(Context context) {
        super(context);
    }

    public ChatText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.chat_text, this, true);
        mInputMethodMgr = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mEditText = (ChatEditText)this.findViewById(R.id.chat_text_content);
        mFaceBtn = (ToggleButton)this.findViewById(R.id.chat_text_facebtn);
        mEmotionView = (GridView)this.findViewById(R.id.chat_text_facegrid);
        mChatTextContainer = (LinearLayout)this.findViewById(R.id.chat_text_container);
        mAdapter = new ChatIconAdapter(getContext(), ChatIconManager.getInstance().getIconMap().get("emoji"));
        mEmotionView.setAdapter(mAdapter);

        mFaceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mFaceBtn.isChecked()) {
                    mInputMethodMgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    mEmotionView.setVisibility(View.VISIBLE);
                } else {
                    mInputMethodMgr.showSoftInput(view, InputMethodManager.SHOW_FORCED);
                    mEmotionView.setVisibility(View.GONE);
                }
            }
        });

        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    mChatTextContainer.setBackgroundResource(R.drawable.input_bar_bg_active);
                } else {
                    mChatTextContainer.setBackgroundResource(R.drawable.input_bar_bg_normal);
                }
            }
        });

        mEmotionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChatIcon chatIcon = (ChatIcon)adapterView.getItemAtPosition(i);
                if(chatIcon == null){
                    return;
                }
                int start = mEditText.getSelectionStart();
                int end = mEditText.getSelectionEnd();
                if(start < 0){
                    mEditText.append(chatIcon.getIconString());
                } else{
                    mEditText.getText().replace(Math.min(start, end), Math.max(start, end), chatIcon.getIconString(), 0, chatIcon.getIconString().length());
                }
            }
        });
    }

    private boolean isEmotionViewVisible(){
        return mEmotionView.getVisibility() == View.VISIBLE;
    }

    private void hideEmotionView(){
        if(isEmotionViewVisible()){
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mEmotionView.getLayoutParams();
            layoutParams.height = mEmotionView.getHeight();
            layoutParams.weight = 0.0f;
            mEmotionView.setVisibility(View.GONE);

        }
    }

    private void showEmotionView(){

    }
}
