package com.huchao.solid.dip.right;

import java.time.LocalDateTime;

/**
 * @author double
 * @Date 2021/7/1 3:52 下午
 */

public class LoggerTest {
    public static void main(String[] args) {
//        ILogger logger = new ConsoleLogger();
//        ILogger logger = new FileLogger();
        ILogger logger = new KafkaLogger();
        logger.info("this is info text");
        logger.warn("this is warn text");
        logger.debug("this is debug text");
        logger.error("this is error text");
    }
}

interface ILogger {
    void info(String text);

    void debug(String text);

    void warn(String text);

    void error(String text);
}

class ConsoleLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[console]", LocalDateTime.now(), "error", text));
    }
}

class FileLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[file]", LocalDateTime.now(), "error", text));
    }
}

class KafkaLogger implements ILogger {

    @Override
    public void info(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "info", text));
    }

    @Override
    public void debug(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "debug", text));
    }

    @Override
    public void warn(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "warn", text));
    }

    @Override
    public void error(String text) {
        System.out.println(String.format("%s %s %s %s", "[kafka]", LocalDateTime.now(), "error", text));
    }
}
