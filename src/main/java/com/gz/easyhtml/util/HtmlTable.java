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
    public static void exportHtml(String fileName, String htmlTable) {
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
    public static String convertHtmlLabel(List<String> headers, List<List<String>> data, TableStyleConfig tableStyleConfig) {
        StringBuilder html = new StringBuilder();
        //默认表格样式前
        defaultTableStyleBefore(html);
        // 初始化表头
        initHeaders(headers, html, tableStyleConfig);
        // 初始行
        initBodyRows(data, html, tableStyleConfig);
        //默认表格样式后
        defaultTableStyleAfter(html);
        return html.toString();
    }

    private static void defaultTableStyleAfter(StringBuilder html) {
        html.append("</table>");
    }

    private static void initBodyRows(List<List<String>> data, StringBuilder html, TableStyleConfig tableStyleConfig) {
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
                        tableStyleConfig.getRowBorderStyle().getLineWith(),
                        tableStyleConfig.getRowBorderStyle().getLineStyle(),
                        tableStyleConfig.getRowBorderStyle().getColor(),
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
    private static void defaultTableStyleBefore(StringBuilder html) {
        html.append("""
                <table style="margin: 0 auto; border-collapse: collapse;">
                """);
    }

    private static void initHeaders(List<String> headers, StringBuilder html, TableStyleConfig tableStyleConfig) {
        html.append("""
                <thead>
                    <tr>
                        """);
        for (String header : headers) {
            html.append("""
                    <th style="border: %s %s %s;">%s</th>
                    """.formatted(
                    tableStyleConfig.getHeadBorderStyle().getLineWith(),
                    tableStyleConfig.getHeadBorderStyle().getLineStyle(),
                    tableStyleConfig.getHeadBorderStyle().getColor(),
                    header
            ));
        }
        html.append("""
                    </tr>
                </thead>
                """);

    }
}
