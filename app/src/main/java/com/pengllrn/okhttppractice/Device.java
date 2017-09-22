package com.pengllrn.okhttppractice;

/**
 * @author Administrator
 * @version $Rev$
 * @des ${UTODO}
 * @updateAuthor ${Author}$
 * @updateDate2017/9/19.
 */

public class Device {
    private String deviceNum = "";
    private String deviceType = "";
    private String RoomName = "";
    private String OrderNum = "";
    private String UseFlag = "";
    private String imgUrl = "";

    public Device(String deviceNum, String deviceType, String roomName, String orderNum, String useFlag,String imgUrl) {
        this.deviceNum = deviceNum;
        this.deviceType = deviceType;
        RoomName = roomName;
        OrderNum = orderNum;
        UseFlag = useFlag;
        this.imgUrl = imgUrl;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getRoomName() {
        return RoomName;
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
