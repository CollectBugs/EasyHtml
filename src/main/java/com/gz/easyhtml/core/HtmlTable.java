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

    //行边框颜色
    private String rowBorderColor;
    //行边框线宽度
    private String rowBorderLineWith;
    //行边框线形状
    private String rowBorderLineStyle;
    //行字体类型
    private String fontType;
    //行字体大小
    private String fontSize;

    //列边框颜色
    private String columnBorderColor;
    //列边框线宽度
    private String columnBorderLineWith;
    //列边框线形状
    private String columnBorderLineStyle;
    //列背景色
    private String backGroundColor;
    //列字体颜色
    private String textColor;


    //标题合并单元格
    private Integer columnColspanNum;
    //标题文本位置
    private PositionEnum textPosition;
    //html内容
    private static StringBuilder html = new StringBuilder();

    HtmlTable(TableStyleConfig config) {
        //标题
        var titleStyle = config.getTitleStyle();
        setTitleStyle(titleStyle);

        //行
        var rowStyle = config.getRowStyle();
        setRowStyle(rowStyle);

        //列
        var columnStyle = config.getColumnStyle();
        setColumnStyle(columnStyle);

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
        //默认表格样式前
        defaultTableStyleBefore();
        // 初始化表头
        initHeaders(titleName, headers);
        // 初始行
        initBodyRows(data);
        //默认表格样式后
        defaultTableStyleAfter();
        //写入html
        writeHtml(outputStream);

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

    private void defaultTableStyleAfter() {
        html.append("</table>");
    }

    private <T> void initBodyRows(List<T> data) {

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
                            .formatted(rowBorderLineWith,
                                    rowBorderLineStyle,
                                    rowBorderColor,
                                    fontSize,
                                    fontType,
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
    private void defaultTableStyleBefore() {
        html.append("""
                <table style="margin: 0 auto; border-collapse: collapse;">
                """);
    }

    private void initHeaders(String titleName, List<String> columns) {
        initHeaderBefore();
        //标题
        doInitTitle(titleName);
        //列表头
        doInitColumn(columns);

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

    private void doInitColumn(List<String> columns) {
        html.append("""
                <tr style="background-color: %s;">
                """.formatted(backGroundColor));
        columns.forEach(column -> html.append("""
                <th style="border: %s %s %s; color: %s;">%s</th>
                """.formatted(
                columnBorderLineWith,
                columnBorderLineStyle,
                columnBorderColor,
                textColor,
                column
        )));
        html.append("""
                    </tr>
                """);
    }

    private void doInitTitle(String titleName) {
        html.append("""
                <tr>
                    <th colspan="%d" style="text-align: %s;">%s</th>
                </tr>
                """.formatted(columnColspanNum, textPosition, titleName));
    }

    private void setColumnStyle(TableStyleConfig.ColumnStyle columnStyle) {
        if (null != columnStyle) {
            String columnBorderColor = columnStyle.getBorderColor();
            this.columnBorderColor = (null != columnBorderColor) ? columnBorderColor : ColorEnum.BLACK.getCode();
            String columnBorderLineWith = columnStyle.getBorderLineWith();
            this.columnBorderLineWith = (null != columnBorderLineWith) ? columnBorderLineWith : LineWidthEnum.WIDTH_1PX.getWidth();
            String columnBorderLineStyle = columnStyle.getBorderLineStyle();
            this.columnBorderLineStyle = (null != columnBorderLineStyle) ? columnBorderLineStyle : LineStyleEnum.SOLID.getStyle();
            String backGroundColor = columnStyle.getBackGroundColor();
            this.backGroundColor = (null != backGroundColor) ? backGroundColor : ColorEnum.BLUE.getCode();
            String textColor = columnStyle.getTextColor();
            this.textColor = (null != textColor) ? textColor : ColorEnum.WHITE.getCode();
        } else {
            this.columnBorderColor = ColorEnum.BLACK.getCode();
            this.columnBorderLineWith = LineWidthEnum.WIDTH_1PX.getWidth();
            this.columnBorderLineStyle = LineStyleEnum.SOLID.getStyle();
            this.backGroundColor = ColorEnum.BLUE.getCode();
            this.textColor = ColorEnum.WHITE.getCode();
        }
    }

    private void setRowStyle(TableStyleConfig.RowStyle rowStyle) {
        if (null != rowStyle) {
            String borderColor = rowStyle.getBorderColor();
            this.rowBorderColor = (null != borderColor) ? borderColor : ColorEnum.BLACK.getCode();
            String borderLineWith = rowStyle.getBorderLineWith();
            this.rowBorderLineWith = (null != borderLineWith) ? borderLineWith : LineWidthEnum.WIDTH_1PX.getWidth();
            String borderLineStyle = rowStyle.getBorderLineStyle();
            this.rowBorderLineStyle = (null != borderLineStyle) ? borderLineStyle : LineStyleEnum.SOLID.getStyle();
            String fontType = rowStyle.getFontType();
            this.fontType = (null != fontType) ? fontType : FontTypeEnum.ARIAL.getType();
            String fontSize = rowStyle.getFontSize();
            this.fontSize = (null != fontSize) ? fontSize : FontSizeEnum.SMALL.getSize();
        } else {
            this.rowBorderColor = ColorEnum.BLACK.getCode();
            this.rowBorderLineWith = LineWidthEnum.WIDTH_1PX.getWidth();
            this.rowBorderLineStyle = LineStyleEnum.SOLID.getStyle();
            this.fontType = FontTypeEnum.ARIAL.getType();
            this.fontSize = FontSizeEnum.SMALL.getSize();
        }

    }

    private void setTitleStyle(TableStyleConfig.TitleStyle titleStyle) {
        if (null != titleStyle) {
            Integer columnColspanNum = titleStyle.getColumnColspanNum();
            this.columnColspanNum = (null != columnColspanNum) ? columnColspanNum : 0;
            PositionEnum textPosition = titleStyle.getTextPosition();
            this.textPosition = (null != textPosition) ? textPosition : PositionEnum.CENTER;
        } else {
            this.columnColspanNum = 0;
            this.textPosition = PositionEnum.CENTER;
        }

    }
}
