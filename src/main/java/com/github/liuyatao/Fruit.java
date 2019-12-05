package com.github.liuyatao;

import lombok.Builder;
import lombok.Data;

/**
 * Fruit
 */

 @Builder
 @Data
public class Fruit {
    
    private String name;
    private Double price;
  
}