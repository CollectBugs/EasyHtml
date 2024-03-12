package com.gz.easyhtml.pojo;

import com.gz.easyhtml.enums.ColorEnum;
import com.gz.easyhtml.enums.LineStyleEnum;
import com.gz.easyhtml.enums.LineWidthEnum;
import com.gz.easyhtml.enums.PositionEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 表格样式配置
 */
@Data
@Builder
public class TableStyleConfig {

    //标题样式
    private Style titleStyle;
    //列样式
    private Style columnStyle;
    //单元格样式
    private Style rowStyle;

    /**
     * 边框
     */
    @Data
    @Builder
    public static class Style {
        //边框颜色
        private String borderColor;
        //边框线宽度
        private String borderLineWith;
        //边框线形状：solid
        private String borderLineStyle;
        //合并单元格
        private Integer columnColspanNum;
        //文本位置
        private PositionEnum textPosition;
    }
}
