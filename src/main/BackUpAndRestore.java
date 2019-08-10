package main;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BackUpAndRestore {

    //TODO-RENZO: I will separate the utility methods in a separate class.

    private static final String FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSSS";
    private static final Path PATH = FileSystems.getDefault().getPath(".").toAbsolutePath();
    private static final String CURRENT_PATH = PATH.toString().replace(".", "");

    public static void main(@NotNull String[] args) {
        switch (args[0]) {
            case "backup":
                performBackUp(args[1], args[2], args[3], args[4]);
                break;
            case "restore":
                performRestore(args[1], args[2], args[3]);
                break;
            default:
                JOptionPane.showMessageDialog(null, "I Can't Execute Your Command :(", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private static void performBackUp(String host, String user, String password, String database) {
        Runtime runtime = Runtime.getRuntime();
        String backUpCommand = "mysqldump -h" + host + " -u" + user + " -p" + password + " --add-drop-database -B " + database + " -r " + CURRENT_PATH + getCurrentDate() + ".sql";
        /*
         *  mysldump variable is registered to my system path.
         *  Or you can just replace it with the directory of mysqldump.exe in your machine.
         */
        Process runtimeProcess;
        try {
            runtimeProcess = runtime.exec(backUpCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Back-Up Created Successfully :D", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't Create Back-Up :(", "Error", JOptionPane.ERROR_MESSAGE);
            }   //TODO-RENZO: I will change the notification message next time. JOption sucks xD
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void performRestore(String host, String user, String password) {
        File file = getLatestBackUp();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "It looks like you don't have back-up file yet in this directory. :(", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Runtime runtime = Runtime.getRuntime();
        /*
         *  mysql is automatically registered to your environment variable when you install mysql server.
         *  So you dont need to add it manually to your environment variable.
         *  Or you can just replace it with the directory of mysql.exe in your machine.
         */
        String[] restoreCommand = new String[]{"mysql", "--host=" + host, "--user=" + user, "--password=" + password, "-e", "source " + file.getAbsolutePath()};
        Process runtimeProcess;
        try {
            runtimeProcess = runtime.exec(restoreCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Database Restored Successfully :D", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't Restore Database :(", "Error", JOptionPane.ERROR_MESSAGE);
            }  //TODO-RENZO: I will change the notification message next time. JOption sucks xD
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static File getLatestBackUp() {
        File directory = new File(BackUpAndRestore.CURRENT_PATH);
        Optional<File> file = Arrays.stream(Objects.requireNonNull(directory.listFiles(File::isFile)))
                            .filter(files -> (files.toString().endsWith(".sql")))
                            .max(Comparator.comparingLong(File::lastModified));
        return file.orElse(null);
    }

    @NotNull
    private static String getCurrentDate() {
        return toString(new Date());
    }

    @NotNull
    private static String toString(Date date) {
        if (date == null) return "DATE_ERROR"; //fix if date is null just return DATE_ERROR
        DateFormat dateFormat = new SimpleDateFormat(BackUpAndRestore.FILE_NAME_FORMAT);
        return dateFormat.format(date);
    }
}
