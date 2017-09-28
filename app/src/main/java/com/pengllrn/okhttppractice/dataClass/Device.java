package com.pengllrn.okhttppractice.dataClass;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/9/19.
 */

public class Device {
    private String DeviceNum = "";
    private String TypeId = "";
    private String RoomId = "";
    private String OrderNum = "";
    private String UseFlag = "";
    private String imgUrl = "";

    public Device(String deviceNum, String deviceType, String roomName, String orderNum, String useFlag,String imgUrl) {
        this.DeviceNum = deviceNum;
        this.TypeId = deviceType;
        RoomId = roomName;
        OrderNum = orderNum;
        UseFlag = useFlag;
        this.imgUrl = imgUrl;
    }

    public String getDeviceNum() {
        return DeviceNum;
    }

    public String getDeviceType() {
        return TypeId;
    }

    public String getRoomName() {
        return RoomId;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public String getUseFlag() {
        return UseFlag;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
