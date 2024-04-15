package com.gz.easyhtml.example.controller;

import com.gz.easyhtml.core.pojo.HeaderStyle;
import com.gz.easyhtml.core.pojo.TableStyleConfig;
import com.gz.easyhtml.core.HtmlTableFactory;
import com.gz.easyhtml.example.pojo.Person;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guozhong
 */
@RestController
@RequestMapping("/api")
public class ImportHtmlController {
    /**
     * @param response
     * @description 导出html表格文件
     * @date 2024-4-12 16:37
     **/
    @GetMapping("/import")
    public void importHtml(HttpServletResponse response) throws Exception {

        //设置响应体
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".html");
        //导出html
        HtmlTableFactory
                .build(null)
                .write(response.getOutputStream(), "人员信息表", header(), data());
    }

    /**
     * @param response
     * @description 实现二级表头, 第一个为一个单元格, 后三个单元格合并
     * @date 2024-4-12 16:36
     **/
    @GetMapping("/import-custom-header")
    public void importCustomHeader(HttpServletResponse response) throws Exception {
        //设置响应体
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试自定义表头", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".html");
        //表头配置
        TableStyleConfig config = TableStyleConfig
                .builder()
                .headerStyle(
                        HeaderStyle.builder()
                                .mergerColumnNum(List.of(
                                        List.of(2, 2)
                                )).build())
                .build();
        //导出html
        HtmlTableFactory
                .build(config)
                .write(response.getOutputStream(), "人员信息表", customHeader(), data());
    }

    public List<List<String>> header() {
        return List.of(List.of("姓名", "年龄", "地址", "性别"));
    }

    public List<List<String>> customHeader() {
        return List.of(List.of("基本信息", "详细信息"),
                List.of("姓名", "性别", "年龄", "地址"));
    }

    public List<Person> data() {
        List<Person> list=new ArrayList<>();
        Person person=new Person();
        person.setName("jelly");
        person.setGender("男");
        person.setAge("18");
        person.setAddr("济南");
        list.add(person);
        return list;
    }
}
