package com.brook.NB_ChatText;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmotionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmotionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmotionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ChatEditText mEditText;
    private ToggleButton mFaceBtn;
    private RelativeLayout mEmotionView;
    private GridView mFaceGrid;
    private ChatIconAdapter mAdapter;
    private LinearLayout mChatText;
    private InputMethodManager mInputMethodMgr;
    private Button mSendBtn;

    public static EmotionFragment newInstance() {
        EmotionFragment fragment = new EmotionFragment();
        return fragment;
    }

    public EmotionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emotion, container, false);
        mInputMethodMgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mEditText = (ChatEditText) v.findViewById(R.id.chat_text_content);
        mFaceBtn = (ToggleButton) v.findViewById(R.id.chat_text_facebtn);
        mEmotionView = (RelativeLayout) v.findViewById(R.id.chat_text_facepanel);
        mFaceGrid = (GridView) v.findViewById(R.id.chat_text_facegrid);
        mChatText = (LinearLayout) v.findViewById(R.id.chat_text);
        mSendBtn = (Button) v.findViewById(R.id.chat_text_sendbtn);
        mAdapter = new ChatIconAdapter(getActivity(), ChatIconManager.getInstance().getIconMap().get("emoji"));
        mFaceGrid.setAdapter(mAdapter);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFaceBtn.isChecked()) {
                    showEmotionView();
                } else {
                    hideEmotionView();
                }
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mChatText.setBackgroundResource(R.drawable.input_bar_bg_active);
                } else {
                    mChatText.setBackgroundResource(R.drawable.input_bar_bg_normal);
                }
                mFaceBtn.setChecked(false);
                hideEmotionView();
            }
        });

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFaceBtn.setChecked(false);
                hideEmotionView();
            }
        });

        mFaceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChatIcon chatIcon = (ChatIcon) adapterView.getItemAtPosition(i);
                if (chatIcon == null) {
                    return;
                }
                int start = mEditText.getSelectionStart();
                int end = mEditText.getSelectionEnd();
                if (start < 0) {
                    mEditText.append(chatIcon.getIconString());
                } else {
                    mEditText.getText().replace(Math.min(start, end), Math.max(start, end), chatIcon.getIconString(), 0, chatIcon.getIconString().length());
                }
            }
        });

        mListener.bindingEvent(mEditText, mSendBtn);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void bindingEvent(ChatEditText editText, Button sendBtn);
    }


    private boolean isEmotionViewVisible() {
        return mEmotionView.getVisibility() == View.VISIBLE;
    }

    private void hideEmotionView() {
        if (isEmotionViewVisible()) {
            Utils.showKeyboard(mEditText);
            mEditText.postDelayed(new Runnable() {

                @Override
                public void run() {
                    mEmotionView.setVisibility(View.GONE);
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }

            }, 500L);
        } else {
            mEmotionView.setVisibility(View.GONE);
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    private void showEmotionView() {
        if (isEmotionViewVisible())
            return;
        int emotionHeight = Utils.getKeyboardHeight(getActivity());
        Utils.hideKeyboard(mEditText);
        mEmotionView.getLayoutParams().height = emotionHeight;
        mEmotionView.setVisibility(View.VISIBLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
