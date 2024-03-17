package com.gz.easyhtml.core.pojo;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StyleBase {

    //边框颜色
    private String borderColor;
    //边框线宽度
    private String borderLineWith;
    //边框线形状：solid
    private String borderLineStyle;
}
