package com.bawei.liushaojie111.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liushaojie111.R;
import com.bawei.liushaojie111.model.bean.ShopBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ShopBean arr;
    //定义一个变量
    private float price_all=0;

    public ShopAdapter(Context context) {
        this.context = context;
    }

    public void setShopBeans(ShopBean shopBeans) {
        this.arr=shopBeans;
        //判断是否为空
        /*if (shopBeans!=null){
            arr.addAll(shopBeans);
            notifyDataSetChanged();
        }*/
    }
    //主界面传过来的textView
    private TextView all_prce;

    public void setAll_prce(TextView textView) {
        this.all_prce = textView;
    }

    //主界面传过来的checkbox
    private CheckBox mcheck;

    public void setMcheck(CheckBox mcheck) {
        this.mcheck = mcheck;
    }
    @Override
    public int getGroupCount() {
        if (arr == null){
            return 0;
        }
        return arr.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (arr == null){
            return 0;
        }
        return arr.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGrop vh;
        if (convertView!=null){
            convertView=View.inflate(context, R.layout.item_shop,null);
            vh=new ViewHolderGrop();
            //vh.checkBox=convertView.findViewById(R.id.check_group);
            vh.textView=convertView.findViewById(R.id.name_group);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolderGrop) convertView.getTag();
        }
        vh.textView.setText(arr.getData().get(groupPosition).getSellerName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolderChild vh;
        if (convertView!=null){
            convertView=View.inflate(context, R.layout.item_child,null);
            vh=new ViewHolderChild();
            //vh.checkBox=convertView.findViewById(R.id.check_group);
            vh.name=convertView.findViewById(R.id.name_child);
            vh.price=convertView.findViewById(R.id.price_child);
            vh.imageView=convertView.findViewById(R.id.price_child);
            vh.checkBox=convertView.findViewById(R.id.check_child);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolderChild) convertView.getTag();
        }
        vh.name.setText(arr.getData().get(groupPosition).getList().get(childPosition).getTitle());
        vh.price.setText("￥："+arr.getData().get(groupPosition).getList().get(childPosition).getPrice());
        Glide.with(context).load(arr.getData().get(groupPosition).getList().get(childPosition).getImages()).into(vh.imageView);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    private boolean isSelectGruop(int gruopPosition){
        for (int i = 0; i <arr.getData().get(gruopPosition).getList().size() ; i++) {
            ShopBean.DataBean.ListBean spusBean = arr.getData().get(gruopPosition).getList().get(i);
           /* boolean check = spusBean.isCheck();
            if(!check){
                return false;
            }*/
           return false;
        }
        return true;
    }
    class ViewHolderGrop{
        CheckBox checkBox;
        TextView textView;
    }
    class ViewHolderChild{
        CheckBox checkBox;
        TextView name,price;
        ImageView imageView;

    }

}
