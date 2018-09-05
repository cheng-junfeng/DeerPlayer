package com.deerplayer.ui.home.fragment;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.deerplayer.R;
import com.deerplayer.app.adapter.BaseDelegateAdapter;
import com.deerplayer.app.base.BaseFragment;
import com.deerplayer.ui.home.contract.HomeFragmentContract;
import com.deerplayer.ui.home.presenter.HomeFragmentPresenter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private HomeFragmentContract.Presenter presenter;
    private List<DelegateAdapter.Adapter> mAdapters;

    DelegateAdapter delegateAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    protected void init() {
        mAdapters = new LinkedList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        presenter = new HomeFragmentPresenter(this.getActivity());
        delegateAdapter = presenter.initRecyclerView(recyclerView);
        BaseDelegateAdapter bannerAdapter = presenter.initMenu1();
        mAdapters.add(bannerAdapter);

        //设置适配器
        delegateAdapter.setAdapters(mAdapters);
    }
}
