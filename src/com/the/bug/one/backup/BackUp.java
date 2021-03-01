package com.the.bug.one.backup;

import com.the.bug.one.config.PropertyConfig;
import com.the.bug.one.util.Utility;
import org.apache.log4j.Logger;

public class BackUp {
    public static final Logger LOGGER = Logger.getLogger(BackUp.class);

    public static void performBackUp(PropertyConfig config) {
        Runtime runtime = Runtime.getRuntime();
        String filePath = String.format("%s%s.sql", Utility.getCurrentPath(), Utility.getCurrentDate());
        String backUpCommand = String.format("mysqldump -h%s -u%s -p%s --add-drop-database -B %s -P%s -r %s",
                config.getHost(), config.getUser(), config.getPassword(), config.getDatabase(), config.getPort(), filePath);
        /*
         *  mysqldump variable is registered to my system path.
         *  Or you can just replace it with the directory of mysqldump.exe in your machine.
         */
        LOGGER.info(String.format("Saving back-up file to : %s", filePath));
        LOGGER.info(String.format("Back-up command : %s", backUpCommand));

        Process runtimeProcess;
        try {
            LOGGER.info("Executing back-up command");
            runtimeProcess = runtime.exec(backUpCommand);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                LOGGER.info("Back up created successfully");
            } else {
                LOGGER.error("Couldn't Create Back-Up :(");
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
