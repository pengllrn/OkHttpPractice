package com.pengllrn.okhttppractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pengllrn.okhttppractice.dataClass.Device;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<Device> mDeviceList;
    public ImageLoader imageLoader;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View deviceView;
        TextView deviceName;
        TextView roomName;
        TextView useFlag;
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            deviceView = itemView;
            deviceName = (TextView) itemView.findViewById(R.id.tv_devicename);
            roomName = (TextView) itemView.findViewById(R.id.tv_roomname);
            useFlag = (TextView) itemView.findViewById(R.id.tv_devicenum);
            iv = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }
    public ItemAdapter(List<Device> device, Context context){
        mDeviceList = device;
        imageLoader = new ImageLoader(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.deviceView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Device device = mDeviceList.get(position);
                Toast.makeText(view.getContext(),"you clicked view "+device.getDeviceNum(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Device device = mDeviceList.get(position);
        holder.deviceName.setText(device.getDeviceType()+"  "+device.getUseFlag());
        holder.roomName.setText("房间 "+device.getRoomName()+"   "+device.getOrderNum());
        holder.useFlag.setText("编号 "+device.getDeviceNum());
        imageLoader.DisplayImage(device.getImgUrl(),holder.iv);
    }

    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

}
