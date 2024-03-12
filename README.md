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
        List<String> column = List.of("姓名", "性别", "年龄","城市");
        List<Person> data=new ArrayList<>();
        for(int i=0;i<50;i++){
            Person person=new Person();
            person.setName("gz"+i);
            person.setAge("25"+i);
            person.setAddr("heze");
            person.setGender("男");
            data.add(person);
        }

        //表格样式配置
        var config = TableStyleConfig.builder()
                .titleStyle(TableStyleConfig.TitleStyle.builder()
                        .borderLineWith(LineWidthEnum.WIDTH_1PX.getWidth())
                        .borderLineStyle(LineStyleEnum.SOLID.getStyle())
                        .borderColor(ColorEnum.BLACK.getCode())
                        .columnColspanNum(4)
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
                        .fontSize(FontSizeEnum.SMALL.getSize())
                        .fontType(FontTypeEnum.ARIAL.getType())
                        .borderLineWith(LineWidthEnum.WIDTH_1PX.getWidth())
                        .borderLineStyle(LineStyleEnum.SOLID.getStyle())
                        .borderColor(ColorEnum.BLACK.getCode()).build())
                .build();
        //构建对象
        HtmlTable ht = new HtmlTable(config);
        //转成html标签
        String htmlTable = ht.convertHtmlLabel("人员信息表", column,data);
        //输出html文件
        ht.exportHtml("test.html", htmlTable);

    }
```
# 项目效果展示
![image](https://github.com/CollectBugs/EasyHtml/assets/32507511/69c451f5-393a-432a-ac18-ba4612fda1a8)

