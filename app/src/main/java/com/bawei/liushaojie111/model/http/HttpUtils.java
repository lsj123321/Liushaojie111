package com.bawei.liushaojie111.model.http;

import android.os.Handler;
import android.os.Message;
import android.util.TimeUtils;

import com.bawei.liushaojie111.model.bean.ShopBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils<T> {

    private final OkHttpClient httpClient;

    //静态式单例
    private HttpUtils(){
        //抽出okhttp
        //设置超时时间
        httpClient = new OkHttpClient.Builder()//设置超时时间
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }
    public static HttpUtils getInstance(){
        return HttpUtilsInstance.httpUtils;
    }
    private static class HttpUtilsInstance{
        private static HttpUtils httpUtils=new HttpUtils();
    }
    private CallBackData mCallBackData;
    //okhttp GET请求
    public void getData(String url,final Class<T> tClass,final CallBackData callBackData){
        this.mCallBackData=callBackData;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=handler.obtainMessage();
                message.what=1;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                T t= (T) gson.fromJson(string,ShopBean.class);
                Message message=handler.obtainMessage();
                message.what=0;
                message.obj=t;
                handler.sendMessage(message);
            }
        });

    }
    //okhttp POST请求
    public void postData(String url, final Class<T> tClass, HashMap<String,String> hashMap,final CallBackData callBackData){
        this.mCallBackData=callBackData;
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            builder.add(key,value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=handler.obtainMessage();
                message.what=1;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                T t = (T) gson.fromJson(string, ShopBean.class);
                Message message=handler.obtainMessage();
                message.what=0;
                message.obj=t;
                handler.sendMessage(message);
            }
        });
    }

    public interface CallBackData<D>{
        public void onResponse(D d);
        public void onFail(String err);
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    T t= (T) msg.obj;
                    mCallBackData.onResponse(t);
                    break;
                case 1:
                    String err= (String) msg.obj;
                    mCallBackData.onFail(err);
                    break;
            }
        }
    };
}
