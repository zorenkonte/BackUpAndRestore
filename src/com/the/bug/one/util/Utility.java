package com.the.bug.one.util;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utility {
    private static final String FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSSS";
    private static final Path PATH = FileSystems.getDefault().getPath(".").toAbsolutePath();
    private static final String CURRENT_PATH = PATH.toString().replace(".", "");

    public static File getLatestBackUp() {
        var directory = new File(CURRENT_PATH);
        Optional<File> file = Arrays.stream(Objects.requireNonNull(directory.listFiles(File::isFile)))
                .filter(files -> (files.toString().endsWith(".sql")))
                .max(Comparator.comparingLong(File::lastModified));
        return file.orElse(null);
    }

    public static String getCurrentPath() {
        return CURRENT_PATH;
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
}
