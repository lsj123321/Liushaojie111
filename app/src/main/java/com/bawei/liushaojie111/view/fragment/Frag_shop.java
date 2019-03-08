package com.bawei.liushaojie111.view.fragment;

import android.support.v7.widget.LinearLayoutManager;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bawei.liushaojie111.R;
import com.bawei.liushaojie111.model.bean.ShopBean;
import com.bawei.liushaojie111.presenter.MainPresenter;
import com.bawei.liushaojie111.view.adapter.ShopAdapter;
import com.bawei.liushaojie111.view.interfaces.IMainView;

public class Frag_shop extends BaseFrag implements IMainView<ShopBean> {
    private MainPresenter mainPresenter;
    private ExpandableListView recyclerView;
    private CheckBox check_all;
    private TextView price_all;
    private LinearLayoutManager linearLayoutManager;
    private ShopAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.frag_shop;
    }

    @Override
    protected void initView() {
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        check_all = getActivity().findViewById(R.id.check_All);
        price_all = getActivity().findViewById(R.id.price_All);
    }

    @Override
    protected void initData() {
        /*linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);*/

        mainPresenter = new MainPresenter();
        mainPresenter.setView(this);
        mainPresenter.loadDataFromNet();

    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        Log.e("myMessage",shopBean+"");
        adapter = new ShopAdapter(getActivity());
        adapter.setShopBeans(shopBean);
        adapter.setAll_prce(price_all);
        //recyclerView.setAdapter(adapter);

    }

    @Override
    public void onErr(String errMsg) {

    }
}
