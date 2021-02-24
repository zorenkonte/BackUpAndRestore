package com.the.bug.one.logger;

import com.the.bug.one.main.Main;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileLogger {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void logError(Process proc) {
        var stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        var stringBuilder = new StringBuilder();
        stdError.lines().forEach(str -> stringBuilder.append(str).append("\n"));
        logError(stringBuilder);
    }

    public static void logError(Object o) {
        LOGGER.trace(o);
    }
}
