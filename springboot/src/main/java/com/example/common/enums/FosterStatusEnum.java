package com.example.common.enums;

public enum FosterStatusEnum {
    ADOPT_NO("待确认"),
    ADOPTING("寄养中"),
    ADOPTED("已领回"),
    ;
    public String status;

    FosterStatusEnum(String status) {
        this.status = status;
    }
}
