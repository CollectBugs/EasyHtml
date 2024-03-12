package com.gz.easyhtml;

import com.gz.easyhtml.enums.ColorEnum;
import com.gz.easyhtml.enums.LineStyleEnum;
import com.gz.easyhtml.enums.LineWidthEnum;
import com.gz.easyhtml.enums.PositionEnum;
import com.gz.easyhtml.pojo.TableStyleConfig;
import com.gz.easyhtml.util.HtmlTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class EasyHtmlApplicationTests {


    @Test
    void listToHtmlTableTest() {
        List<String> headers = List.of("Name", "Age", "City");
        List<List<String>> data = List.of(
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("gz", "25", "beijing"),
                List.of("jelly", "30", "he ze")
        );
        //表格样式配置
        var config = TableStyleConfig.builder()
                .titleStyle(TableStyleConfig.TitleStyle.builder()
                        .borderLineWith(LineWidthEnum.WIDTH_1PX.getWidth())
                        .borderLineStyle(LineStyleEnum.SOLID.getStyle())
                        .borderColor(ColorEnum.BLACK.getCode())
                        .columnColspanNum(3)
                        .textPosition(PositionEnum.CENTER)
                        .build())
                .columnStyle(TableStyleConfig.ColumnStyle.builder()
                        .borderLineWith(LineWidthEnum.WIDTH_1PX.getWidth())
                        .borderLineStyle(LineStyleEnum.SOLID.getStyle())
                        .borderColor(ColorEnum.BLACK.getCode())
                        .backGroundColor(ColorEnum.BLUE.getCode())
                        .textColor(ColorEnum.WHITE.getCode())
                        .build())
                .rowStyle(TableStyleConfig.RowStyle.builder()
                        .borderLineWith(LineWidthEnum.WIDTH_1PX.getWidth())
                        .borderLineStyle(LineStyleEnum.SOLID.getStyle())
                        .borderColor(ColorEnum.BLACK.getCode()).build())
                .build();
        //构建对象
        HtmlTable ht = new HtmlTable(config);
        //转成html标签
        String htmlTable = ht.convertHtmlLabel("人员信息表", headers, data);
        //输出html文件
        ht.exportHtml("test.html", htmlTable);
    }


}
