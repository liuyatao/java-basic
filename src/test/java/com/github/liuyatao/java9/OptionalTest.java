package com.github.liuyatao.java9;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * OptionalTest
 */
public class OptionalTest {

    @Test
    @DisplayName("当Optional中有值得时候返回它本来得值")
    public void givenOptional_whenPresent_thenShouldTakeAValueFromIt() {

        // given
        String expected = "properValue";
        String defaultString = "default";
        Optional<String> value = Optional.of(expected);
        Optional<String> defaultValue = Optional.of(defaultString);

        // when
        Optional<String> result = value.or(() -> defaultValue);

        // then
        assertTrue(result.get().equals(expected));
    }

    @Test
    @DisplayName("当Optional是空的时候返回or的结果")
    public void givenOptional_whenEmpty_thenShouldTakeAValueFromOr() {

        // given
        String defaultString = "default";
        Optional<String> value = Optional.empty();
        Optional<String> defaultValue = Optional.of(defaultString);

        // when
        Optional<String> result = value.or(() -> defaultValue);

        // then
        assertTrue(result.get().equals(defaultString));
    }

    @DisplayName("当Optional中有值的时候做相应的回调")
    @Test
    public void givenOptional_whenPresent_thenShouldExecuteProperCallback() {

        // given
        Optional<String> value = Optional.of("properValue");
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        // when
        value.ifPresentOrElse(v -> successCounter.incrementAndGet(), onEmptyOptionalCounter::incrementAndGet);

        // then
        assertTrue(successCounter.get() == 1);
        assertTrue(onEmptyOptionalCounter.get() == 0);
    }

    @Test
    public void givenOptional_whenNotPresent_thenShouldExecuteProperCallback() {
        // given
        Optional<String> value = Optional.empty();
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        // when
        value.ifPresentOrElse(v -> successCounter.incrementAndGet(), onEmptyOptionalCounter::incrementAndGet);

        // then
        assertThat("fff", successCounter.get(), equalTo(0));
        assertThat(onEmptyOptionalCounter.get(), equalTo(1));
    }

    @Test
    public void givenOptionalOfSome_whenToStream_thenShouldTreatItAsOneElementStream() {
        // given
        Optional<String> value = Optional.of("a");

        // when
        List<String> collect = value.stream().map(String::toUpperCase).collect(Collectors.toList());

        // then
        assertThat(collect, hasItem("A"));
    }

    @Test
    public void givenOptionalOfNone_whenToStream_thenShouldTreatItAsZeroElementStream() {
        // given
        Optional<String> value = Optional.empty();

        // when
        List<String> collect = value.stream().map(String::toUpperCase).collect(Collectors.toList());

        // then
        assertThat(collect,is(empty()));
    }

}