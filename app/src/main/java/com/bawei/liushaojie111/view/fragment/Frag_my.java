package com.bawei.liushaojie111.view.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.TextView;

import com.bawei.liushaojie111.R;
import com.bawei.liushaojie111.model.bean.CircleView;

public class Frag_my extends BaseFrag{

    @Override
    public int bindLayout() {
        return R.layout.frag_my;
    }

    @Override
    protected void initView() {
        final TextView textView=getActivity().findViewById(R.id.donghua);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}
