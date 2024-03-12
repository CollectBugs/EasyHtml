# 技术栈
* springboot 3.2.3
* jdk 17
# 功能
* 支持自定义表格标题
* 支持自定义标题样式
* 支持自定义列样式
* 支持自定义行样式
* 支持自定义文件名
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
```
# 项目效果展示
![image](https://github.com/CollectBugs/EasyHtml/assets/32507511/ad68c789-3549-4bb3-9601-8a7956104961)

