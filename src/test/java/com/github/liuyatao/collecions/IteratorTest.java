package com.github.liuyatao.collecions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

/**
 * IteratorTest
 */

 @Log4j2
public class IteratorTest {


    @Test
    public void getTteratorTest() {
        Map<String,String> map = new HashMap<>();
        map.put("key", "value");

        List<String> list = Arrays.asList("1","2","3");
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            log.info(iterator.next());
        }
        
    }


    
}