package com.gz.easyhtml.core.pojo;

import com.gz.easyhtml.core.HtmlTable;
import lombok.Builder;
import lombok.Data;


/**
 * 表格样式配置
 * @author guozhong
 */
@Data
@Builder
public class TableStyleConfig {

    /**
     * @description 表头样式
     **/
    public HeaderStyle headerStyle;

}
