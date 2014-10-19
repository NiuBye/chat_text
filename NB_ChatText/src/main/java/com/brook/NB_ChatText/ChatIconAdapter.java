package com.brook.NB_ChatText;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shibrook on 14-10-18.
 */
public class ChatIconAdapter extends ArrayAdapter<ChatIcon> {

    public ChatIconAdapter(Context context, List<ChatIcon> list){
        super(context, R.layout.chat_item, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder viewHolder = null;
        if(v == null){
            v = View.inflate(getContext(), R.layout.chat_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)v.findViewById(R.id.chat_item_id);
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)v.getTag();
        }
        ChatIcon chatIcon = getItem(position);
        viewHolder.imageView.setImageDrawable(getContext().getResources().getDrawable(chatIcon.getIconId()));
        return v;
    }

    class ViewHolder{
        ImageView imageView;
    }
}
