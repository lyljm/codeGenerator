package com.easyjava.generator.Bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestConstants {
    @Test
    public void testPath() {
        log.info("PATH_MAIN " + Constants.PATH_MAIN);
        log.info("PACKAGE_BASE " + Constants.PACKAGE_BASE);
        log.info("PACKAGE_PO " + Constants.PACKAGE_PO);
        log.info("PACKAGE_QUERY " + Constants.PACKAGE_QUERY);
        log.info("PATH_MAIN " + Constants.PATH_MAIN);
        log.info("PATH_PO " + Constants.PATH_PO);
        log.info("PATH_QUERY " + Constants.PATH_QUERY);
    }
}
