package com.github.liuyatao;

import lombok.extern.log4j.Log4j2;

/**
 * Hello world!
 */

 @Log4j2
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        log.debug("Hello world");
    }
}
