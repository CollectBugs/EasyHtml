package com.gz.easyhtml.util;

import com.gz.easyhtml.pojo.TableStyleConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlTable {
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
     * @param tableStyleConfig
     * @return
     */
    public String convertHtmlLabel(String titleName, List<String> headers, List<List<String>> data, TableStyleConfig tableStyleConfig) {
        StringBuilder html = new StringBuilder();
        //默认表格样式前
        defaultTableStyleBefore(html);
        // 初始化表头
        initHeaders(titleName, headers, html, tableStyleConfig);
        // 初始行
        initBodyRows(data, html, tableStyleConfig);
        //默认表格样式后
        defaultTableStyleAfter(html);
        return html.toString();
    }

    private void defaultTableStyleAfter(StringBuilder html) {
        html.append("</table>");
    }

    private void initBodyRows(List<List<String>> data, StringBuilder html, TableStyleConfig tableStyleConfig) {
        TableStyleConfig.Style rowStyle = tableStyleConfig.getRowStyle();

        html.append("""
                <tbody>
                    """);
        for (List<String> row : data) {
            html.append("""
                    <tr>
                        """);
            for (String cell : row) {
                html.append("""
                        <td style="border: %s %s %s;">%s</td>
                        """.formatted(
                        rowStyle.getBorderLineWith(),
                        rowStyle.getBorderLineStyle(),
                        rowStyle.getBorderColor(),
                        cell
                ));
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

    private void initHeaders(String titleName, List<String> columns, StringBuilder html, TableStyleConfig tableStyleConfig) {
        TableStyleConfig.Style columnStyle = tableStyleConfig.getColumnStyle();
        TableStyleConfig.Style titleStyle = tableStyleConfig.getTitleStyle();

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

    private void doInitColumn(TableStyleConfig.Style columnStyle, List<String> columns, StringBuilder html) {
        html.append("""
                <tr>
                """);
        columns.forEach(column -> html.append("""
                <th style="border: %s %s %s;">%s</th>
                """.formatted(
                columnStyle.getBorderLineWith(),
                columnStyle.getBorderLineStyle(),
                columnStyle.getBorderColor(),
                column
        )));
        html.append("""
                    </tr>
                """);
    }

    private void doInitTitle(TableStyleConfig.Style titleStyle, StringBuilder html, String titleName) {
        html.append("""
                <tr>
                    <th colspan="%d" style="text-align: %s;">%s</th>
                </tr>
                """.formatted(titleStyle.getColumnColspanNum(), titleStyle.getTextPosition(), titleName));
    }
}
