package com.github.liuyatao.java8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.IsIterableContaining;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

/**
 * FunctionalInterfaceTest
 */

@Log4j2
public class FunctionalInterfaceTest {

    @Test
    @DisplayName("函数式接口Compose")
    public void functionInterfaceComposeTest() {

        Function<Integer, String> inToString = s -> s.toString();

        assertThat(inToString.apply(5), equalTo("5"));

        Function<String, String> quote = s -> "'" + s + "'";

        Function<Integer, String> quoteIntToString = quote.compose(inToString);

        assertThat(quoteIntToString.apply(5), equalTo("'5'"));
    }

    @Test
    public void primitiveFunctionsTest() {
        IntFunction<Integer> intFunction = s -> s + 1;
        assertThat(intFunction.apply(5), equalTo(6));

        ToIntFunction<String> toIntFunction = s -> Integer.parseInt(s);
        assertThat(toIntFunction.applyAsInt("5"), equalTo(5));

        DoubleToIntFunction doubleToIntFunction = d -> (int) d;
        assertThat(doubleToIntFunction.applyAsInt(12.3d), equalTo(12));
    }

    @Test
    @DisplayName("二元函数")
    public void twoArityFunctionsTest() {
        Map<String, Integer> salaries = new TreeMap<>();
        salaries.put("张三", 40000);
        salaries.put("李四", 30000);
        salaries.put("王五", 50000);

        salaries.replaceAll((name, oldValue) -> name.equals("李四") ? oldValue + 10000 : oldValue);
        assertThat(salaries, IsMapContaining.hasEntry("李四", 40000));


        Map<String, Integer> salaries1 = new TreeMap<>();
        salaries1.put("张三", 4000);
        salaries1.put("李四", 3000);
        salaries1.put("王五", 5000);
        salaries1.put("赵六", 5000);

        salaries.forEach((name,value)->{
            salaries1.merge(name, value, (oldValue,newValue)->{return oldValue;});
        });
        
        assertThat(salaries1, IsMapContaining.hasEntry("李四", 3000));


    }


    @Test
    @DisplayName("")
    public void consumersTest(){
        List<String> names = Arrays.asList("张三","李四","王五");

        names.forEach(name->{    // Consumer
            System.out.println("你好,"+name+"!");
        });

        Map<String, Integer> ages = new HashMap<>();
        ages.put("张三", 25);
        ages.put("李四", 24);
        ages.put("王五", 30);

        ages.forEach((name,age)->{  //BiConsumer
            log.info("{}的年龄是:{}",name,age);
        });
    }


    @Test
    @DisplayName("Predicates测试")
    public void predicatesTest(){
        List<String> names = Arrays.asList("张三","李四","王五","张六");

        List<String> namesWithZhang = names.stream()
            .filter(name -> name.startsWith("张"))
            .collect(Collectors.toList());

        assertThat(namesWithZhang, IsIterableContaining.hasItems("张三","张六"));

        IntPredicate evenNumbers = i -> i % 2 == 0;

        assertThat(evenNumbers.test(100),is(true));

    }


    @Test
    @DisplayName("Operators测试")
    public void operatorsTest() {

        List<String> names = Arrays.asList("zhang san","li si","wang wu");
        names.replaceAll(name ->name.toUpperCase()); //UnaryOperator;
        assertThat(names, IsIterableContaining.hasItems("ZHANG SAN","LI SI","WANG WU"));

        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);
        int sum = values.stream().reduce(0, (i1,i2)->i1+i2);
        assertThat(sum,is(37));    
    }
}