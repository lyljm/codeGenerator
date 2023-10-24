package com.easyjava.generator.utils;


import com.easyjava.generator.Bean.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class PropertiesTest {

    @Test
    public void testReadProperties() {
        String string = PropertiesUtils.getString("db.datasource.driver-class-name");
        log.info("driver-class-name: {}", string);
    }

    @Test
    public void testBoolRead() {
        boolean aaaa = PropertiesUtils.getBoolean("aaaa");
        Assert.assertTrue (!aaaa);
        boolean expression=PropertiesUtils.getBoolean("bean.date.deserialize.expression");
        Assert.assertTrue (!expression);
        boolean isOpen=PropertiesUtils.getBoolean("bean.date.deserialize.open");
        Assert.assertTrue(isOpen);
    }

    @Test
    public void testResource(){
        String pathResources = Constants.PATH_RESOURCES;
        String pathXml = Constants.PATH_XML;
        log.info(pathResources);
        log.info(pathXml);
    }


}
