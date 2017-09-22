package com.pengllrn.okhttppractice;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
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
    private Context mContext;
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

//                    OkHttpClient.Builder b = new OkHttpClient.Builder();
//                    b.connectTimeout(2, TimeUnit.SECONDS);
//                   /*RequestBody requestBody = new FormBody.Builder()
//                            .add("username","pl")
//                            .add("password","123")
//                            .build();*/
                    //用post提交键值对格式的数据
                    Request request = new Request.Builder()
                            .url(path)
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    if(response.isSuccessful()) {
                        String responseData = response.body().string();
                        List<Device> listNews=parseJson(responseData);
                        if(listNews.size()>0){
                            Message msg = new Message();
                            msg.what = PARSEOK;//通知UI线程Json解析完成
                            msg.obj = listNews;//将解析的数据传递给UI线程
                            handler.sendMessage(msg);
                        }
                    }
                    else {
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


    private List<Device> parseJson(String json) {
        List<Device> listNews = new ArrayList<>();
        try {
            File file = new File(mContext.getFilesDir(),"url.txt");
            FileOutputStream fos= new FileOutputStream(file);

            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jObject=jsonArray.getJSONObject(i);
                String deviceNum=jObject.getString("DeviceNum");
                String deviceType=jObject.getString("TypeId");
                String RoomName = jObject.getString("RoomId");
                String OrderNum = jObject.getString("OrderNum");
                String UseFlag = jObject.getString("UseFlag");
                String imgUrl = jObject.getString("imgUrl");
                fos.write(imgUrl.getBytes());
                fos.write("\n".getBytes());
                //fos.flush();

                Device device = new Device(deviceNum,deviceType,RoomName,OrderNum,UseFlag,imgUrl);
                listNews.add(device);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNews;
    }
}
