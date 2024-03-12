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
```
# 项目效果展示
![image](https://github.com/CollectBugs/EasyHtml/assets/32507511/e3a86f08-bd20-4e4e-9958-55165a9cdcea)
