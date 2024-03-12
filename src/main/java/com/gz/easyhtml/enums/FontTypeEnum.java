package com.gz.easyhtml.enums;

import lombok.Getter;

@Getter

public enum FontTypeEnum {
    ARIAL("Arial, sans-serif"),
    TIMES_NEW_ROMAN("Times New Roman, serif"),
    VERDANA("Verdana, Geneva, sans-serif");

    private final String type;

    FontTypeEnum(String type) {
        this.type = type;
    }
}
