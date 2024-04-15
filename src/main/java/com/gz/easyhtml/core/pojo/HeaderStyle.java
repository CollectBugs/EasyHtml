package com.gz.easyhtml.core.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description: 表头样式
 * @author: guozhong
 * @create: 2024-04-12 16:30
 */
@Data
@Builder
public class HeaderStyle {
    /**
     * @description 合并列集合
     **/
    private List<List<Integer>> mergerColumnNum;
}
