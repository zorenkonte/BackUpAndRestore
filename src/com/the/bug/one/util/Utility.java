package com.the.bug.one.util;

import com.the.bug.one.config.PropertyConfig;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utility {
    public static final Logger LOGGER = Logger.getLogger(Utility.class);
    private static final String FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSSS";
    private static final Path PATH = FileSystems.getDefault().getPath(".").toAbsolutePath();
    private static final String CURRENT_PATH = PATH.toString().replace(".", "");
    private static final String DEFAULT_SEPARATOR = File.separator;
    private static final String FOLDER_NAME = "backup-files";
    private static final PropertyConfig CONFIG = PropertyConfig.getInstance();

    public static File getLatestBackUp() {
        var directory = new File(CURRENT_PATH);
        Optional<File> file = Arrays.stream(Objects.requireNonNull(directory.listFiles(File::isFile)))
                .filter(files -> (files.toString().endsWith(".sql")))
                .max(Comparator.comparingLong(File::lastModified));
        return file.orElse(null);
    }

    public static String getCurrentPath() {
        return String.format("%s%s%s%s", CURRENT_PATH, DEFAULT_SEPARATOR, FOLDER_NAME, DEFAULT_SEPARATOR);
    }

    @NotNull
    public static String getCurrentDate() {
        return toString(new Date());
    }

    @NotNull
    private static String toString(Date date) {
        if (date == null) return "DATE_ERROR"; //fix if date is null just return DATE_ERROR
        var dateFormat = new SimpleDateFormat(FILE_NAME_FORMAT);
        return dateFormat.format(date);
    }

    public static void createDefaultDirectory() {
        var file = new File(getCurrentPath());
        if (file.mkdirs()) {
            LOGGER.info("Folder has been created.");
        } else {
            LOGGER.warn("Folder already exists.");
        }
    }

    public static void loadProperties() {
        try (InputStream input = Utility.class.getClassLoader().getResourceAsStream("resources/app.properties")) {
            var prop = new Properties();
            if (input == null) {
                LOGGER.warn("Sorry, unable to find app.properties");
            } else {
                prop.load(input);
                CONFIG.setHost(prop.getProperty("host"));
                CONFIG.setPort(prop.getProperty("port"));
                CONFIG.setUser(prop.getProperty("user"));
                CONFIG.setPassword(prop.getProperty("password"));
                CONFIG.setDatabase(prop.getProperty("database"));
                CONFIG.setCronExpression(prop.getProperty("cron-expression"));
                LOGGER.info("Properties loaded");
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
