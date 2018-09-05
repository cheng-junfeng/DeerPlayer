package com.deerplayer.ui.local.config;

import android.graphics.Bitmap;

import java.io.Serializable;


public class FileInfo implements Serializable{

    /**
     * 常见文件拓展名
     */
    public static final String EXTEND_JPEG = ".jpeg";
    public static final String EXTEND_JPG = ".jpg";
    public static final String EXTEND_MP4 = ".mp4";

    /**
     * 自定义文件类型
     */
    public static final int TYPE_JPG = 1;
    public static final int TYPE_MP4 = 2;


    //必要属性
    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private int fileType;

    /**
     * 文件大小
     */
    private long size;

    //非必要属性
    /**
     * 文件显示名称
     */
    private String name;

    /**
     * 文件大小描述
     */
    private String sizeDesc;

    /**
     * 文件缩略图 (mp4与apk可能需要)
     */
    private Bitmap bitmap;

    /**
     * 文件额外信息
     */
    private String extra;


    /**
     * 已经处理的（读或者写）
     */
    private long procceed;

    /**
     * 文件传送的结果
     */
    private int result;


    public FileInfo(){

    }

    public FileInfo(String filePath, long size) {
        this.filePath = filePath;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizeDesc() {
        return sizeDesc;
    }

    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", size=" + size +
                '}';
    }
}
