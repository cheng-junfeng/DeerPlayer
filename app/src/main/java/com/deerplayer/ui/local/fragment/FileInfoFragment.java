package com.deerplayer.ui.local.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.deerplayer.PlayerApplication;
import com.deerplayer.R;
import com.deerplayer.app.base.BaseFragment;
import com.deerplayer.ui.local.adapter.FileInfoAdapter;
import com.deerplayer.ui.local.config.FileInfo;
import com.deerplayer.ui.local.util.FileUtils;
import com.deerplayer.ui.player.PlayerLocalActivity;
import com.deerplayer.ui.player.PlayerOnlineActivity;
import com.hint.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;


public class FileInfoFragment extends BaseFragment {

    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.pb)
    ProgressBar pb;

    private int mType = FileInfo.TYPE_MP4;
    private List<FileInfo> mFileInfoList;
    private FileInfoAdapter mFileInfoAdapter;

    @SuppressLint("ValidFragment")
    public FileInfoFragment() {
        super();
    }

    @SuppressLint("ValidFragment")
    public FileInfoFragment(int type) {
        super();
        this.mType = type;
    }

    public static FileInfoFragment newInstance(int type) {
        FileInfoFragment fragment = new FileInfoFragment(type);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_apk;
    }

    @Override
    protected void init() {
        if (mType == FileInfo.TYPE_JPG) { //图片
            gv.setNumColumns(3);
        } else if (mType == FileInfo.TYPE_MP4) { //视频
            gv.setNumColumns(1);
        }

        if (mType == FileInfo.TYPE_JPG) {
            new GetFileInfoListTask(getContext(), FileInfo.TYPE_JPG).executeOnExecutor(PlayerApplication.MAIN_EXECUTOR);
        } else if (mType == FileInfo.TYPE_MP4) {
            new GetFileInfoListTask(getContext(), FileInfo.TYPE_MP4).executeOnExecutor(PlayerApplication.MAIN_EXECUTOR);
        }

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileInfo fileInfo = mFileInfoList.get(position);
//                FileUtils.openFile(mContext, fileInfo.getFilePath());
                Intent intent = new Intent(mContext, PlayerLocalActivity.class);
                intent.putExtra(PlayerLocalActivity.BUNDLE_KEY_TITLE, fileInfo.getName());
                intent.putExtra(PlayerLocalActivity.BUNDLE_KEY_PATH, fileInfo.getFilePath());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        updateFileInfoAdapter();
        super.onResume();
    }

    public void updateFileInfoAdapter() {
        if (mFileInfoAdapter != null) {
            mFileInfoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showProgressBar() {
        if (pb != null) {
            pb.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (pb != null && pb.isShown()) {
            pb.setVisibility(View.GONE);
        }
    }

    class GetFileInfoListTask extends AsyncTask<String, Integer, List<FileInfo>> {
        Context sContext = null;
        int sType = FileInfo.TYPE_JPG;
        List<FileInfo> sFileInfoList = null;

        public GetFileInfoListTask(Context sContext, int type) {
            this.sContext = sContext;
            this.sType = type;
        }

        @Override
        protected void onPreExecute() {
            showProgressBar();
            super.onPreExecute();
        }

        @Override
        protected List doInBackground(String... params) {
            //FileUtils.getSpecificTypeFiles 只获取FileInfo的属性 filePath与size
            if (sType == FileInfo.TYPE_JPG) {
                sFileInfoList = FileUtils.getSpecificTypeFiles(sContext, new String[]{FileInfo.EXTEND_JPG, FileInfo.EXTEND_JPEG});
                sFileInfoList = FileUtils.getDetailFileInfos(sContext, sFileInfoList, FileInfo.TYPE_JPG);
            } else if (sType == FileInfo.TYPE_MP4) {
                sFileInfoList = FileUtils.getSpecificTypeFiles(sContext, new String[]{FileInfo.EXTEND_MP4});
                sFileInfoList = FileUtils.getDetailFileInfos(sContext, sFileInfoList, FileInfo.TYPE_MP4);
            }

            mFileInfoList = sFileInfoList;

            return sFileInfoList;
        }


        @Override
        protected void onPostExecute(List<FileInfo> list) {
            hideProgressBar();
            if (sFileInfoList != null && sFileInfoList.size() > 0) {
                if (mType == FileInfo.TYPE_JPG) { //图片
                    mFileInfoAdapter = new FileInfoAdapter(sContext, sFileInfoList, FileInfo.TYPE_JPG);
                    gv.setAdapter(mFileInfoAdapter);
                } else if (mType == FileInfo.TYPE_MP4) { //视频
                    mFileInfoAdapter = new FileInfoAdapter(sContext, sFileInfoList, FileInfo.TYPE_MP4);
                    gv.setAdapter(mFileInfoAdapter);
                }
            } else {
                ToastUtils.showToast(sContext, "没有信息");
            }
        }
    }
}
