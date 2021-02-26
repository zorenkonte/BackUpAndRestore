package com.the.bug.one.main;

import static com.the.bug.one.job.BackUpTask.startTask;
import static com.the.bug.one.util.Utility.createDefaultDirectory;
import static com.the.bug.one.util.Utility.loadProperties;

public class Main {

    public static void main(String[] args) {
        createDefaultDirectory(); //create default folder if not exist on startup
        loadProperties(); //load configs
        startTask(); //start cron job
    }
}
