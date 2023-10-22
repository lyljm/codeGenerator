package com.easyjava.generator.utils;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesTest {
    Logger logger = LoggerFactory.getLogger(PropertiesTest.class);

    @Test
    public void testReadProperties() {
        String string = PropertiesUtils.getString("db.datasource.driver-class-name");
        logger.info("driver-class-name: {}", string);
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


}
