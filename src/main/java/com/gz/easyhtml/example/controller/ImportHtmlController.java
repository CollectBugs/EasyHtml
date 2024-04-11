package com.gz.easyhtml.example.controller;

import com.gz.easyhtml.example.pojo.Person;
import com.gz.easyhtml.core.pojo.TableStyleConfig;
import com.gz.easyhtml.core.HtmlTableFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImportHtmlController {
    @GetMapping("/import")
    public void importHtml(HttpServletResponse response) throws Exception {
        //构造列
        List<String> column = List.of("姓名", "性别", "年龄", "城市");
        //表格样式配置
        var config = TableStyleConfig.builder()
                .titleStyle(TableStyleConfig.TitleStyle.builder()
                        .columnColspanNum(column.size)
                        .build()).build();

        //设置响应体
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".html");

        //导出html
        HtmlTableFactory
                .build(config)
                .write(response.getOutputStream(), "人员信息表", column, data());
    }

    private List<Person> data() {
        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Person person = new Person();
            person.setName("CX" + i);
            person.setAge("25" + i);
            person.setAddr("beijing");
            person.setGender("男");
            data.add(person);
        }
        return data;

    }


}
