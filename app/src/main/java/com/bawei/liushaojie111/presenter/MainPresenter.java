package com.bawei.liushaojie111.presenter;

import android.util.Log;

import com.bawei.liushaojie111.app.Constant;
import com.bawei.liushaojie111.model.bean.ShopBean;
import com.bawei.liushaojie111.model.http.HttpUtils;
import com.bawei.liushaojie111.view.interfaces.IMainView;

public class MainPresenter extends BasePresenter<IMainView<ShopBean>> {

    private final HttpUtils httpUtils;

    public MainPresenter(){
        httpUtils = HttpUtils.getInstance();
    }
    public void loadDataFromNet(){
        httpUtils.getData(Constant.BASE_URL, ShopBean.class, new HttpUtils.CallBackData<ShopBean>() {

            @Override
            public void onResponse(ShopBean shopBean) {
                if (getView()!=null){
                    getView().onSuccess(shopBean);
                }else {
                    Log.e("myMessage","view未绑定");
                }
            }

            @Override
            public void onFail(String err) {
                getView().onErr(err);
            }
        });
    }

}
