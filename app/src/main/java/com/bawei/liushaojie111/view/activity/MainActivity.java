package com.bawei.liushaojie111.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.liushaojie111.R;
import com.bawei.liushaojie111.model.bean.ShopBean;
import com.bawei.liushaojie111.presenter.MainPresenter;
import com.bawei.liushaojie111.view.fragment.Frag_my;
import com.bawei.liushaojie111.view.fragment.Frag_shop;
import com.bawei.liushaojie111.view.interfaces.IMainView;

public class MainActivity extends BaseActivity {


    private TextView shop;
    private TextView my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        shop = findViewById(R.id.shop_show);
        my = findViewById(R.id.my_show);
    }

    @Override
    public void initData() {
        //制作fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Frag_shop frag_shop=new Frag_shop();
        Frag_my frag_my=new Frag_my();
        transaction.add(R.id.frag,frag_shop);
        transaction.commit();
        //设置点击切换
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                Frag_shop frag_shop=new Frag_shop();
                Frag_my frag_my=new Frag_my();
                transaction1.replace(R.id.frag,frag_shop);
                transaction1.commit();
            }
        });
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                Frag_shop frag_shop=new Frag_shop();
                Frag_my frag_my=new Frag_my();
                transaction1.replace(R.id.frag,frag_my);
                transaction1.commit();
            }
        });
    }

    @Override
    public int bindView() {
        return R.layout.activity_main;
    }

}
