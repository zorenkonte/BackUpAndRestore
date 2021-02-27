package com.the.bug.one.job;

import com.the.bug.one.backup.BackUp;
import com.the.bug.one.config.PropertyConfig;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpJob implements Job {

    public static final Logger LOGGER = Logger.getLogger(BackUpJob.class);
    private static final PropertyConfig CONFIG = PropertyConfig.getInstance();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("Executing back-up job");
        BackUp.performBackUp(CONFIG); //do backup
    }
}
