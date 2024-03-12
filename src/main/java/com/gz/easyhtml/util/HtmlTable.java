package com.gz.easyhtml.util;

import com.gz.easyhtml.pojo.Person;
import com.gz.easyhtml.pojo.TableStyleConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.List;

public class HtmlTable {

    //配置类
    private TableStyleConfig config;
    public HtmlTable(TableStyleConfig config) {
        this.config=config;
    }

    /**
     * 导出html文件
     *
     * @param fileName
     * @param htmlTable
     */
    public void exportHtml(String fileName, String htmlTable) {
        //输出html文件
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(htmlTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转成html标签
     *
     * @param headers
     * @param data
     * @return
     */
    public <T>String convertHtmlLabel(String titleName, List<String> headers, List<T> data) {
        StringBuilder html = new StringBuilder();
        //默认表格样式前
        defaultTableStyleBefore(html);
        // 初始化表头
        initHeaders(titleName, headers, html);
        // 初始行
        initBodyRows(data, html);
        //默认表格样式后
        defaultTableStyleAfter(html);
        return html.toString();
    }

    private void defaultTableStyleAfter(StringBuilder html) {
        html.append("</table>");
    }

    private <T> void initBodyRows(List<T> data, StringBuilder html) {
        TableStyleConfig.RowStyle rowStyle = config.getRowStyle();

        html.append("""
            <tbody>
                """);
        for (T row : data) {
            html.append("""
                <tr>
                    """);
            // 获取对象的所有属性
            Field[] fields = row.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    MethodHandle getter = MethodHandles.lookup().unreflectGetter(field);
                    Object value = getter.invoke(row);
                    html.append("<td style=\"border: %s %s %s; font-size: %s; font-family: %s;\">%s</td>"
                            .formatted(rowStyle.getBorderLineWith(),
                                    rowStyle.getBorderLineStyle(),
                                    rowStyle.getBorderColor(),
                                    rowStyle.getFontSize(),
                                    rowStyle.getFontType(),
                                    value.toString()));
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            html.append("""
                </tr>
                """);
        }
        html.append("""
            </tbody>
            """);
    }


    //默认表格样式
    private void defaultTableStyleBefore(StringBuilder html) {
        html.append("""
                <table style="margin: 0 auto; border-collapse: collapse;">
                """);
    }

    private void initHeaders(String titleName, List<String> columns, StringBuilder html) {
        TableStyleConfig.ColumnStyle columnStyle = config.getColumnStyle();
        TableStyleConfig.TitleStyle titleStyle = config.getTitleStyle();

        initHeaderBefore(html);

        //标题
        doInitTitle(titleStyle, html, titleName);
        //列表头
        doInitColumn(columnStyle, columns, html);

        initHeaderAfter(html);

    }

    private void initHeaderBefore(StringBuilder html) {
        html.append("""
                <thead>
                """);
    }

    private void initHeaderAfter(StringBuilder html) {
        html.append("""
                </thead>
                """);
    }

    private void doInitColumn(TableStyleConfig.ColumnStyle columnStyle, List<String> columns, StringBuilder html) {
        html.append("""
                <tr style="background-color: %s;">
                """.formatted(columnStyle.getBackGroundColor()));
        columns.forEach(column -> html.append("""
                <th style="border: %s %s %s; color: %s;">%s</th>
                """.formatted(
                columnStyle.getBorderLineWith(),
                columnStyle.getBorderLineStyle(),
                columnStyle.getBorderColor(),
                columnStyle.getTextColor(),
                column
        )));
        html.append("""
                    </tr>
                """);
    }

    private void doInitTitle(TableStyleConfig.TitleStyle titleStyle, StringBuilder html, String titleName) {
        html.append("""
                <tr>
                    <th colspan="%d" style="text-align: %s;">%s</th>
                </tr>
                """.formatted(titleStyle.getColumnColspanNum(), titleStyle.getTextPosition(), titleName));
    }
}
