package com.gz.easyhtml.pojo;

import com.gz.easyhtml.enums.ColorEnum;
import com.gz.easyhtml.enums.LineStyleEnum;
import com.gz.easyhtml.enums.LineWidthEnum;
import com.gz.easyhtml.enums.PositionEnum;
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
    public static class TitleStyle extends StyleBase {
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


    }
}
