package com.gz.easyhtml.enums;

import lombok.Getter;

@Getter
public enum ColorEnum {
    BLACK("#000000"),
    RED("#FF0000"),
    WHITE("#FFFFFF"),
    BLUE("#0000FF");

    private final String code;

    ColorEnum(String code) {
        this.code = code;
    }
}
