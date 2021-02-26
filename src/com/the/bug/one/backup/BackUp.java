package com.the.bug.one.backup;

import com.the.bug.one.job.BackUpConfig;
import com.the.bug.one.logger.FileLogger;
import com.the.bug.one.util.Utility;

import java.time.Instant;

public class BackUp {
    public static void performBackUp(BackUpConfig config) {
        Runtime runtime = Runtime.getRuntime();
        String filePath = String.format("%s%s.sql", Utility.getDefaultDir(), Utility.getCurrentDate());
        String backUpCommand = String.format("mysqldump -h%s -u%s -p%s --add-drop-database -B %s -P%s -r %s",
                config.getHost(), config.getUser(), config.getPassword(), config.getDatabase(), config.getPort(), filePath);
        /*
         *  mysldump variable is registered to my system path.
         *  Or you can just replace it with the directory of mysqldump.exe in your machine.
         */
        Process runtimeProcess;
        try {
            runtimeProcess = runtime.exec(backUpCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.format("%s : %s \n", "Back up created successfully", Instant.now());
            } else {
                System.out.format("%s : %s \n", "Couldn't Create Back-Up :(", Instant.now());
                FileLogger.logError(runtimeProcess);
            }   //TODO-RENZO: I will change the notification message next time. JOption sucks xD
        } catch (Exception ex) {
            FileLogger.logError(ex);
        }
    }
}
