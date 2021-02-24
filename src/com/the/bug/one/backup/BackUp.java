package com.the.bug.one.backup;

import com.the.bug.one.logger.FileLogger;
import com.the.bug.one.util.Utility;

import javax.swing.*;

public class BackUp {
    public static void performBackUp(String host, String user, String password, String database) {
        Runtime runtime = Runtime.getRuntime();
        String backUpCommand = "mysqldump -h" + host + " -u" + user + " -p" + password + " --add-drop-database -B " + database + " -r " + Utility.getCurrentPath() + Utility.getCurrentDate() + ".sql";
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
                FileLogger.logError(runtimeProcess);
            }   //TODO-RENZO: I will change the notification message next time. JOption sucks xD
        } catch (Exception ex) {
            FileLogger.logError(ex);
        }
    }
}
