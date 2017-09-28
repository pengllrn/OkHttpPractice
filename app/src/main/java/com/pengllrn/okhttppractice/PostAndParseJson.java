package com.pengllrn.okhttppractice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.pengllrn.okhttppractice.dataClass.All;
import com.pengllrn.okhttppractice.dataClass.Device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * PARSOK 转换成功后的返回码
 */
public class PostAndParseJson{
    public static final int PARSEOK = 0x2017;
    private Handler handler;
    private static Context mContext;
    public PostAndParseJson(Context context, Handler handler){
        this.mContext = context;
        this.handler = handler;
    }

    public void getJsonFromInternet(final String path,final RequestBody requestBody){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //用post提交键值对格式的数据
                    Request request = new Request.Builder()
                            .url(path)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        //List<Device> listNews=parseJson(responseData);
                        parseJson(responseData);

                    }
                    else {
                        //TODO 错误报告
                        Message msg = new Message();
                        msg.what = 0x22;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    Message msg = new Message();
                    msg.what = 0x22;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void parseJson(String json){
        Gson gson = new Gson();
        All all = gson.fromJson(json,All.class);
        List<Device> listNews = new ArrayList<>();
        listNews = all.getDevice();
//        List<SchoolById> listNews1 = new ArrayList<>();
//        for(int i=0;i<all.getSchoolbyid().size();i++){
//            listNews1.add(all.getSchoolbyid().get(i));
//        }
        Message msg = new Message();
        msg.what = PARSEOK;
        msg.obj = listNews;
        handler.sendMessage(msg);
    }
}
