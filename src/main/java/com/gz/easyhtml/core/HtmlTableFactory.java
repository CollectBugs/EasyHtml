package com.gz.easyhtml.core;

import com.gz.easyhtml.core.pojo.TableStyleConfig;

/**
 * @author guozhong
 */
public class HtmlTableFactory {
    public static HtmlTable build(TableStyleConfig config) {
        return new HtmlTable(config);
    }

}
