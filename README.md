# 技术栈
* springboot 3.2.3
* jdk 17
# 测试样例
```Java
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
        String htmlTable = HtmlTable.exportHtml(headers, data, config);
        //输出html文件
        String fileName = "output.html";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(htmlTable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
```
# 项目效果展示
![image](https://github.com/CollectBugs/EasyHtml/assets/32507511/e3a86f08-bd20-4e4e-9958-55165a9cdcea)
