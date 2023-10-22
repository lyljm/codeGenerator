package com.easyjava.generator.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StrutilsTest {
    
    @Test
    public  void testUpperAndLower(){
        String s = StrUtils.firstLetter2Upper(null);
        log.info(s);
        String s1 = StrUtils.firstLetter2Upper("");
        log.info(s1);
        String s2 = StrUtils.firstLetter2Upper("test");
        log.info(s2);
        String s3 = StrUtils.firstLetter2Upper("Test");
        log.info(s3);
    }
}
