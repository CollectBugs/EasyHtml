package com.gz.easyhtml.core;

import com.gz.easyhtml.core.pojo.TableStyleConfig;

public class HtmlTableFactory {
    public static HtmlTable build(TableStyleConfig config) {
        if(null == config){
            config=TableStyleConfig.builder().build();
        }
        return new HtmlTable(config);
    }

}
