package com.gz.easyhtml.core;

import com.gz.easyhtml.core.enums.*;
import com.gz.easyhtml.core.pojo.TableStyleConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HtmlTable {

    /**
     * @description html
     * @date 2024-4-12 14:51
     * @param null
     * @return null
     **/
    private static StringBuffer html = new StringBuffer();

    HtmlTable(TableStyleConfig config) {
    }


    /**
     * 导出
     *
     * @param outputStream
     * @param titleName
     * @param headers
     * @param data
     * @param <T>
     */
    public <T> void write(OutputStream outputStream, String titleName, List<String> headers, List<T> data) {
        //默认全局样式前
        defaultGlobalStyleBefore();
        //初始化body
        initBodyHtml(titleName,headers,data);
        //默认全局样式后
        defaultGlobalStyleAfter();
        //写入html
        writeHtml(outputStream);

    }
    /**
     * @description 初始化body
     * @date 2024-4-12 14:31
     * @param titleName
     * @param headers
     * @param data
     **/
    private <T> void initBodyHtml(String titleName, List<String> headers, List<T> data) {
        // 初始化body html标签前
        doBodyHtmlBefore();
        // 初始化标题
        doTitleHtml(titleName);
        // 初始化table html标签前
        doTableHtmlBefore();
        // 初始化表头
        doHeaders(headers);
        // 初始数据行
        doBodyRows(data);
        // 初始化table html标签后
        doTableHtmlAfter();
        // 初始化body html标签后
        doBodyHtmlAfter();

    }

    private void doTableHtmlAfter() {
        html.append("</table>");

    }

    private void doTableHtmlBefore() {
        html.append("<table>");

    }

    private void doBodyHtmlAfter() {
        html.append("</body>");

    }

    private void doBodyHtmlBefore() {
        html.append("<body>");
    }

    /**
     * @description 初始化标题
     * @date 2024-4-12 14:33
     * @param titleName
     **/
    private void doTitleHtml(String titleName) {
        html.append("<h2>").append(titleName).append("</h2>");
    }

    /**
     * 导出html文件
     *
     * @param outputStream
     */
    public static void writeHtml(OutputStream outputStream) {
        //输出html文件
        try {
            // 将HTML内容写入输出流
            outputStream.write(html.toString().getBytes(StandardCharsets.UTF_8));
            // 刷新输出流
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void defaultGlobalStyleAfter() {
        html.append("</html>");
    }

    private <T> void doBodyRows(List<T> data) {

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
                    html.append("<td >%s</td>"
                            .formatted(value.toString()));
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
    private void defaultGlobalStyleBefore() {
        html.append("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Styled HTML Table</title>
                    <style>
                        table {
                            border-collapse: collapse;
                            width: 100%;
                        }
                        th, td {
                            border: 1px solid #ddd; /* 设置单元格边框为1像素宽且灰色 */
                            padding: 8px;
                        }
                        th {
                            background-color: #f2f2f2; /* 设置表头背景色 */
                        }
                    </style>
                </head>
                """);
    }

    private void doHeaders(List<String> headers) {
        initHeaderBefore();
        //列表头
        doInitHeader(headers);
        initHeaderAfter();

    }

    private void initHeaderBefore() {
        html.append("""
                <thead>
                """);
    }

    private void initHeaderAfter() {
        html.append("""
                </thead>
                """);
    }

    private void doInitHeader(List<String> columns) {
        html.append("""
            <tr>
            """);
        columns.forEach(column -> html.append("""
            <th>%s</th>
            """.formatted(column)));
        html.append("""
                </tr>
            """);
    }
}
