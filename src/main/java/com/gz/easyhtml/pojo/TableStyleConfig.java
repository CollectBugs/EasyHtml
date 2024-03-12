package com.gz.easyhtml.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 表格样式配置
 */
@Data
@Builder
public class TableStyleConfig {
    //头样式
    private Border headBorderStyle;
    //单元格样式
    private Border rowBorderStyle;

    /**
     * 边框
     */
    @Data
    @Builder
    public static class Border {
        //颜色
        private String color;
        //边框宽度
        private String lineWith;
        //边框形状：solid
        private String lineStyle;

    }
}
