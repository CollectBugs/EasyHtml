package com.gz.easyhtml.core.enums;

import lombok.Getter;

@Getter
public enum LineWidthEnum {
    WIDTH_1PX("1px"),
    WIDTH_2PX("2px"),
    WIDTH_3PX("3px");

    private final String width;

    LineWidthEnum(String width) {
        this.width = width;
    }
}
