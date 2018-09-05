package com.deerplayer.ui.local.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deerplayer.R;
import com.deerplayer.app.adapter.CommonAdapter;
import com.deerplayer.ui.local.config.FileInfo;

import java.util.List;


public class FileInfoAdapter extends CommonAdapter<FileInfo> {

    private int mType = FileInfo.TYPE_MP4;

    public FileInfoAdapter(Context context, List<FileInfo> dataList) {
        super(context, dataList);
    }

    public FileInfoAdapter(Context context, List<FileInfo> dataList, int type) {
        super(context, dataList);
        this.mType = type;
    }

    @Override
    public View convertView(int position, View convertView) {
        FileInfo fileInfo = getDataList().get(position);

        if(mType == FileInfo.TYPE_JPG){ //JPG convertView
            JpgViewHolder viewHolder = null;
            if(convertView == null){
                convertView = View.inflate(getContext(), R.layout.item_jpg, null);
                viewHolder = new JpgViewHolder();
                viewHolder.iv_shortcut = (ImageView) convertView.findViewById(R.id.iv_shortcut);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (JpgViewHolder) convertView.getTag();
            }

            if(getDataList() != null && getDataList().get(position) != null){
                Glide
                    .with(getContext())
                    .load(fileInfo.getFilePath())
                    .centerCrop()
                    .placeholder(R.mipmap.icon_jpg)
                    .crossFade()
                    .into(viewHolder.iv_shortcut);
            }
        }
        else if(mType == FileInfo.TYPE_MP4){ //MP4 convertView
            Mp4ViewHolder viewHolder = null;
            if(convertView == null){
                convertView = View.inflate(getContext(), R.layout.item_mp4, null);
                viewHolder = new Mp4ViewHolder();
                viewHolder.iv_shortcut = (ImageView) convertView.findViewById(R.id.iv_shortcut);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (Mp4ViewHolder) convertView.getTag();
            }

            if(getDataList() != null && getDataList().get(position) != null){
                viewHolder.iv_shortcut.setImageBitmap(fileInfo.getBitmap());
                viewHolder.tv_name.setText(fileInfo.getName() == null ? "" : fileInfo.getName());
                viewHolder.tv_size.setText(fileInfo.getSizeDesc() == null ? "" : fileInfo.getSizeDesc());
            }
        }

        return convertView;
    }

    static class JpgViewHolder {
        ImageView iv_shortcut;
    }

    static class Mp4ViewHolder {
        ImageView iv_shortcut;
        TextView tv_name;
        TextView tv_size;
    }
}
