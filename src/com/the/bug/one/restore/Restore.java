package com.the.bug.one.restore;

import com.the.bug.one.util.Utility;

import javax.swing.*;

public class Restore {
    public static void performRestore(String host, String user, String password) {
        var file = Utility.getLatestBackUp();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "It looks like you don't have back-up file yet in this directory. :(", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        var runtime = Runtime.getRuntime();
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
}
