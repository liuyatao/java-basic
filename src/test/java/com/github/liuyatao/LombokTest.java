package com.github.liuyatao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

/**
 * LombokTest
 */

 @Log4j2
public class LombokTest {

    @Test
    @DisplayName("测试Lombok Builder")
    public void testBuilder() {
       Fruit apple = Fruit.builder().name("Apple").price(10.0d).build();
       log.info(apple.toString());
       assertTrue("Apple".equals(apple.getName())&&10.0d==apple.getPrice());
    }
}