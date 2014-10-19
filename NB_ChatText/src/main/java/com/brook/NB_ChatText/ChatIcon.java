package com.brook.NB_ChatText;

/**
 * Created by brook.shi (niubye@126.com) on 10/17/2014.
 */
public class ChatIcon {

    private String mIconStr;
    private int mIconId;
    private int mUnicode;

    public ChatIcon(int unicode, int iconId)
    {
        if (Character.charCount(unicode) == 1) {
            mIconStr = String.valueOf(unicode);
        } else {
            mIconStr = new String(Character.toChars(unicode));
        }
        mUnicode = unicode;
        mIconId = iconId;
    }

    public ChatIcon(char ch, int iconId)
    {
        mIconStr = Character.toString(ch);
        mUnicode = (int)ch;
        mIconId = iconId;
    }

//    public ChatIcon(String iconStr)
//    {
//        mIconStr = iconStr;
//    }

    public String getIconString()
    {
        return mIconStr;
    }

    public int getUnicode() {
        return mUnicode;
    }

    public int getIconId(){
        return mIconId;
    }
}
