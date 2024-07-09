package com.example.common.enums;

public enum RoomStatusEnum {

    ROOM_OK("空闲"),
    ROOM_NO("占用"),
    ;
    public String status;

    RoomStatusEnum(String status) {
        this.status = status;
    }
}
