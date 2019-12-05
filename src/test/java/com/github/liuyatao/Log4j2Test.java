package com.github.liuyatao;

import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

/**
 * Log4j2Test
 */

 @Log4j2
public class Log4j2Test {


    @Test
    public void log4j2ThreadTest() {
        Thread a = new Thread(()->{
            log.info("Running...");
        });
        a.setName("The thread Name");
        a.start();
        
    }
}