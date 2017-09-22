package com.pengllrn.okhttppractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    private List<Device> listDevice = new ArrayList<>();
    private String path = "http://192.168.1.20:9999/getdeviceinfo/";
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case PostAndParseJson.PARSEOK:
                    listDevice=(List<Device>) msg.obj;
                    Toast.makeText(MainActivity.this,"设备已成功找到", Toast.LENGTH_SHORT).show();
                    showOnRecycler(listDevice);
                    break;
                case 0x22:
                    Toast.makeText(MainActivity.this,"服务器访问失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PostAndParseJson getAndParseJson = new PostAndParseJson(this, mHandler);
        RequestBody requestBody = new FormBody.Builder()
                .add("username","pl")
                .add("password","123")
                .build();
        getAndParseJson.getJsonFromInternet(path,requestBody);

    }

    public void showOnRecycler(List<Device> listDevice){
        //设置recycler显示的内容---
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(listDevice,MainActivity.this);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));//设置分割线
        //-----设置recycler显示的内容
    }
}
