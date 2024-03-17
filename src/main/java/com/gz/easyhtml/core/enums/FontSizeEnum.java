package com.gz.easyhtml.core.enums;

import lombok.Getter;

@Getter

public enum FontSizeEnum {
    SMALL("12px"),
    MEDIUM("14px"),
    LARGE("16px");

    private final String size;

    FontSizeEnum(String size) {
        this.size = size;
    }

}
