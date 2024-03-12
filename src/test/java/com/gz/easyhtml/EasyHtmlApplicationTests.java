package com.gz.easyhtml;

import com.gz.easyhtml.enums.ColorEnum;
import com.gz.easyhtml.enums.LineStyleEnum;
import com.gz.easyhtml.enums.LineWidthEnum;
import com.gz.easyhtml.pojo.TableStyleConfig;
import com.gz.easyhtml.util.HtmlTable;
import io.github.birddevelper.salmos.HtmlReportMaker;
import io.github.birddevelper.salmos.ObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class EasyHtmlApplicationTests {


    @Test
    void listToHtmlTableTest()  {
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
                .headBorderStyle(TableStyleConfig.Border.builder().
                        lineWith(LineWidthEnum.WIDTH_1PX.getWidth()).
                        lineStyle(LineStyleEnum.SOLID.getStyle())
                        .color(ColorEnum.BLACK.getCode()).build())
                .rowBorderStyle(TableStyleConfig.Border.builder().
                        lineWith(LineWidthEnum.WIDTH_1PX.getWidth()).
                        lineStyle(LineStyleEnum.SOLID.getStyle())
                        .color(ColorEnum.BLACK.getCode()).build())
                .build();
        //转成html标签
        String htmlTable = HtmlTable.convertHtmlLabel(headers, data, config);
        //输出html文件
        HtmlTable.exportHtml("test.html",htmlTable);
    }



}
