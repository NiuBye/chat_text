package com.brook.NB_ChatText;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shibrook on 14-10-18.
 */
public class ChatIconManager {

    private static String TAG = "ChatIconManager";
    private final Map<String, List<ChatIcon>> mIconMap = new HashMap<String, List<ChatIcon>>();
    //private Map<Object, ChatIcon> mChatIconMap = new HashMap<Object, ChatIcon>();
    private static ChatIconManager instance = new ChatIconManager();
    public static ChatIconManager getInstance() {
        return instance;
    }

    private ChatIconManager() {
        initEmojiIcon("emoji");
    }

    public void initEmojiIcon(String tabName)
    {
        addChatIconTab(tabName, Emoji.Icons);
    }

    public void addChatIconTab(String tabName, Object[] keys, int[] iconIds) {

        if (keys.length != iconIds.length) {
            Log.d(TAG, "length of keys is not equal to length of icons");
            return;
        }
        if(TextUtils.isEmpty(tabName) || mIconMap.containsKey(tabName)){
            Log.d(TAG, "key is empty or exists already.");
            return;
        }

        List<ChatIcon> chatIconList = new ArrayList<ChatIcon>();
        for(int i = 0; i<keys.length; i++) {
            chatIconList.add(new ChatIcon((Integer)keys[i], iconIds[i]));
        }
        mIconMap.put(tabName, chatIconList);
    }

    public void addChatIconTab(Context context, int tabNameId, Object[] keys, int[] iconIds){
        String tabName = context.getString(tabNameId);
        addChatIconTab(tabName, keys, iconIds);
    }

    public void addChatIconTab(String tabName, ChatIcon[] chatIcons){
        mIconMap.put(tabName, Arrays.asList(chatIcons));
    }

    public void addChatIconTab(Context context, int tabNameId, ChatIcon[] chatIcons){
        String tabName = context.getString(tabNameId);
        mIconMap.put(tabName, Arrays.asList(chatIcons));
    }

    public ChatIcon getChatIcon(int unicode){
        for(List<ChatIcon> list : mIconMap.values()){
            for(ChatIcon chatIcon : list){
                if(chatIcon.getUnicode() == unicode){
                    return chatIcon;
                }
            }
        }
        return null;
    }

    public Map<String, List<ChatIcon>> getIconMap() {
        return mIconMap;
    }
}
