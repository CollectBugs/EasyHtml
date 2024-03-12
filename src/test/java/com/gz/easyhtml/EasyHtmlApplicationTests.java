package com.gz.easyhtml;

import com.gz.easyhtml.enums.*;
import com.gz.easyhtml.pojo.Person;
import com.gz.easyhtml.pojo.TableStyleConfig;
import com.gz.easyhtml.util.HtmlTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EasyHtmlApplicationTests {


    @Test
    void listToHtmlTableTest() {
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
                        .columnColspanNum(4)
                        .build()).build();

        //构建对象
        HtmlTable ht = new HtmlTable(config);
        //转成html标签
        String htmlTable = ht.convertHtmlLabel("人员信息表", column,data);
        //输出html文件
        ht.exportHtml("test.html", htmlTable);
    }


}
