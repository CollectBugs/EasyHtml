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
    private Border cellBorderStyle;
    //表格样式
    private Table tableStyle;

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

    /**
     * 表格
     */
    @Data
    @Builder
    public static class Table {
        //文本位置
        private String textPosition;
        //表格位置
        private String margin;
        //合并相邻单元格边框
        private String borderCollapse;
    }
}
