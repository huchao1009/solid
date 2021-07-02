package com.huchao.solid.dip.wrong;

import java.time.LocalDateTime;

/**
 * @author double
 * @Date 2021/7/1 3:45 下午
 */
public class LoggerTest{

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.info("this is info text");
        logger.warn("this is warn text");
        logger.debug("this is debug text");
        logger.error("this is error text");
    }
}

class Logger {

    public void info(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "info", text));
    }

    public void warn(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "warn", text));
    }

    public void debug(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "debug", text));
    }

    public void error(String text) {
        System.out.println(String.format("%s %s %s", LocalDateTime.now(), "error", text));
    }
}
