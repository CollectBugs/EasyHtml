package com.gz.easyhtml.enums;

import lombok.Getter;

@Getter
public enum LineStyleEnum {
    SOLID("solid"),
    DASHED("dashed"),
    DOTTED("dotted");

    private final String style;

    LineStyleEnum(String style) {
        this.style = style;
    }
}
