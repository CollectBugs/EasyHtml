package com.gz.easyhtml;

import com.gz.easyhtml.pojo.TableStyleConfig;
import com.gz.easyhtml.util.HtmlTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class EasyHtmlApplicationTests {

    @Test
    void listToHtmlTableTest()  {
        List<String> headers = List.of("Name", "Age", "City");
        List<List<String>> data = List.of(
                List.of("gz", "25", "beijing"),
                List.of("jelly", "30", "he ze")
        );
        //表格配置
        var config = TableStyleConfig.builder()
                .tableStyle(TableStyleConfig.Table.builder()
                        .borderCollapse("collapse")
                        .margin("0 auto")
                        .textPosition("center")
                        .build())
                .headBorderStyle(TableStyleConfig.Border.builder().
                        lineWith("1px").
                        lineStyle("solid")
                        .color("#000000").build())
                .cellBorderStyle(TableStyleConfig.Border.builder().
                        lineWith("1px").
                        lineStyle("solid")
                        .color("#000000").build())
                .build();
        //转成html标签
        String htmlTable = HtmlTable.convertHtmlLabel(headers, data, config);
        //输出html文件
        HtmlTable.exportHtml("test.html",htmlTable);
    }

}
