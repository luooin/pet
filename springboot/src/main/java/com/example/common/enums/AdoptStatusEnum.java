package com.example.common.enums;

public enum AdoptStatusEnum {
    ADOPT_YES("领养中"),
    ADOPT_NO("已归还"),
    ;
    public String status;

    AdoptStatusEnum(String status) {
        this.status = status;
    }
}
