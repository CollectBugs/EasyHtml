package com.gz.easyhtml.util;

import com.gz.easyhtml.pojo.TableStyleConfig;

import java.util.List;

public class HtmlTable {
    /**
     * 导出html文件
     * @param headers
     * @param data
     * @param tableStyleConfig
     * @return
     */
    public static String exportHtml(List<String> headers, List<List<String>> data, TableStyleConfig tableStyleConfig) {
        StringBuilder html = new StringBuilder();
        html.append("<table style=\"text-align: ").append(tableStyleConfig.getTableStyle().getTextPosition())
                .append("; margin: ").append(tableStyleConfig.getTableStyle().getMargin())
                .append("; border-collapse: ").append(tableStyleConfig.getTableStyle().getBorderCollapse())
                .append(";\">");

        // table headers
        html.append("<thead><tr>");
        for (String header : headers) {
            html.append("<th style=\"border: ").append(tableStyleConfig.getHeadBorderStyle().getLineWith())
                    .append(" ").append(tableStyleConfig.getHeadBorderStyle().getLineStyle())
                    .append(" ").append(tableStyleConfig.getHeadBorderStyle().getColor()).append(";\">")
                    .append(header).append("</th>");
        }
        html.append("</tr></thead>");

        // table body
        html.append("<tbody>");
        for (List<String> row : data) {
            html.append("<tr>");
            for (String cell : row) {
                html.append("<td style=\"border: ").append(tableStyleConfig.getCellBorderStyle().getLineWith())
                        .append(" ").append(tableStyleConfig.getCellBorderStyle().getLineStyle())
                        .append(" ").append(tableStyleConfig.getCellBorderStyle().getColor()).append(";\">")
                        .append(cell).append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</tbody>");
        html.append("</table>");

        return html.toString();
    }
}
