package com.the.bug.one.main;

import org.apache.log4j.PropertyConfigurator;

import static com.the.bug.one.job.BackUpTask.startTask;
import static com.the.bug.one.util.Utility.createDefaultDirectory;
import static com.the.bug.one.util.Utility.loadProperties;

public class Main {

    public static void main(String[] args) {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("resources/log4j.properties")); //register log4j config props
        createDefaultDirectory(); //create default folder if not exist on startup
        loadProperties(); //load configs
        startTask(); //start cron job
    }
}
