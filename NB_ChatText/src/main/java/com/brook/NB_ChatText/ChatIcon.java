package com.brook.NB_ChatText;

/**
 * Created by brook.shi (niubye@126.com) on 10/17/2014.
 */
public class ChatIcon {

    private String mIconStr;

    public ChatIcon(int unicode)
    {
        if (Character.charCount(unicode) == 1) {
            mIconStr = String.valueOf(unicode);
        } else {
            mIconStr = new String(Character.toChars(unicode));
        }
    }

    public ChatIcon(char ch)
    {
        mIconStr = Character.toString(ch);
    }

    public ChatIcon(String iconStr)
    {
        mIconStr = iconStr;
    }

    public String GetIconString()
    {
        return mIconStr;
    }
}
