package com.gz.easyhtml.core.pojo;

import com.gz.easyhtml.core.enums.PositionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 表格样式配置
 */
@Data
@Builder
public class TableStyleConfig {

    //标题样式
    private TitleStyle titleStyle;
    //列样式
    private ColumnStyle columnStyle;
    //单元格样式
    private RowStyle rowStyle;

    /**
     * 边框
     */
    @Data
    @SuperBuilder
    public static class TitleStyle {
        //合并单元格
        private Integer columnColspanNum;
        //文本位置
        private PositionEnum textPosition;

    }
    /**
     * 边框
     */
    @Data
    @SuperBuilder
    public static class ColumnStyle extends StyleBase {

        //背景色
        private String backGroundColor;
        //字体颜色
        private String textColor;

    }
    /**
     * 边框
     */
    @Data
    @SuperBuilder
    public static class RowStyle extends StyleBase {
        //字体类型
        private String fontType;
        //字体大小
        private String fontSize;
    }
}
