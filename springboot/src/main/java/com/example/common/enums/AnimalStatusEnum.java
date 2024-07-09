package com.example.common.enums;

public enum AnimalStatusEnum {
    ADOPT_NO("待领养"),
    ADOPT_YES("已领养"),
    ADOPT_CANCEL("已归还"),
    ;
    public String status;

    AnimalStatusEnum(String status) {
        this.status = status;
    }
}
