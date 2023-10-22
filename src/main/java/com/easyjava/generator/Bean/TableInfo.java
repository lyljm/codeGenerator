package com.easyjava.generator.Bean;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class TableInfo {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 类名称
     */
    private String beanName;
    /**
     * 参数名称（query）
     */
    private String beanParamName;
    /**
     * 注释
     */
    private String comment;

    /**
     * 字段信息
     */
    private List<FieldInfo> fieldList;
    /**
     * 唯一索引集合
     * 索引名称： 索引字段List
     */
    private Map<String,List<FieldInfo>> keyIndexMap=new LinkedHashMap<>();
    /**
     * 是否有Date类型
     */
    private Boolean haveDate=false;
    /**
     * 是否有LocalDateTime类型
     */
    private Boolean haveDateTime=false;
    /**
     * 是否有BidDecimal类型
     */
    private Boolean haveBigDecimal=false;

}
