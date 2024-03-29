package com.gz.easyhtml.core.enums;

import lombok.Getter;

@Getter
public enum PositionEnum {
    RIGHT("right"),
    CENTER("center"),
    LEFT("left");

    private final String position;

    PositionEnum(String position) {
        this.position = position;
    }
}
