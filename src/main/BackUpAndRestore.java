package main;

import javax.swing.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BackUpAndRestore {

    private static final String FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSSS";
    private static final Path PATH = FileSystems.getDefault().getPath(".").toAbsolutePath();
    private static final String CURRENT_PATH = PATH.toString().replace(".", "");

    public static void main(String[] args) {
        switch (args[0]) {
            case "backup":
                performBackUp();
                break;
            case "restore":
                performRestore();
                break;
            default:
                JOptionPane.showMessageDialog(null, "I Can't Execute Your Command :(", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private static void performBackUp() {
        Runtime runtime = Runtime.getRuntime();
        String backUpCommand = "mysqldump -h192.168.254.130 -utgrenzo -p@sdf1234 --add-drop-database -B inventory -r " + CURRENT_PATH + getCurrentDate() + ".sql";
        Process runtimeProcess;
        try {
            runtimeProcess=runtime.exec(backUpCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Back-Up Created Successfully :D", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't Create Back-Up :(", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void performRestore() {
        File file = lastFileModified(CURRENT_PATH);
        Runtime runtime = Runtime.getRuntime();
        String[] restoreCommand = new String[]{"mysql", "--host=" + "192.168.254.130", "--user=" + "tgrenzo", "--password=" + "@sdf1234", "-e", "source " + file.getAbsolutePath()};
        Process runtimeProcess;
        try {
            runtimeProcess=runtime.exec(restoreCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Database Restored Successfully :D", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Couldn't Restore Database :(", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static File lastFileModified(String directory) {
        File fl = new File(directory);
        File[] files = fl.listFiles(File::isFile);
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : Objects.requireNonNull(files)) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }

    private static String getCurrentDate() {
        return toString(new Date());
    }

    private static String toString(Date date) {
        if (date == null) return "DATE_ERROR"; //fix if date is null just return DATE_ERROR
        DateFormat dateFormat = new SimpleDateFormat(BackUpAndRestore.FILE_NAME_FORMAT);
        return dateFormat.format(date);
    }
}
