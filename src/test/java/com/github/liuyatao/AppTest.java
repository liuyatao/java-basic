package com.github.liuyatao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lombok.extern.log4j.Log4j2;

/**
 * Unit test for simple App.
 */
@Log4j2
public class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    @DisplayName("Hello World Test")
    public void testApp() {
        log.debug("{},{}","hello","world");
        assertTrue(true);
    }
}
