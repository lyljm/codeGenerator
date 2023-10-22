package com.easyjava.generator.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldInfo {
    private String fieldName;
    private String propertyName;
    private String sqlType;
    private String javaType;
    private String comment;
    private Boolean isAutoIncrement;

}
