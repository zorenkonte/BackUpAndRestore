package com.the.bug.one.backup;

import com.the.bug.one.logger.FileLogger;
import com.the.bug.one.util.Utility;

import javax.swing.*;
import java.time.Instant;

public class BackUp {
    public static void performBackUp(String host, String user, String password, String database) {
        Runtime runtime = Runtime.getRuntime();
        String backUpCommand = "mysqldump -h" + host + " -u" + user + " -p" + password + " --add-drop-database -B " + database + " -r " + Utility.getDefaultDir() + Utility.getCurrentDate() + ".sql";
        /*
         *  mysldump variable is registered to my system path.
         *  Or you can just replace it with the directory of mysqldump.exe in your machine.
         */
        System.out.println(backUpCommand);
        Process runtimeProcess;
        try {
            runtimeProcess = runtime.exec(backUpCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.format("%s : %s", "Back up created successfully", Instant.now());
            } else {
                System.out.format("%s : %s", "Couldn't Create Back-Up :(", Instant.now());
                FileLogger.logError(runtimeProcess);
            }   //TODO-RENZO: I will change the notification message next time. JOption sucks xD
        } catch (Exception ex) {
            FileLogger.logError(ex);
        }
    }
}
