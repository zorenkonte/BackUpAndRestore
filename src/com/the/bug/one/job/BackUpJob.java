package com.the.bug.one.job;

import com.the.bug.one.backup.BackUp;
import com.the.bug.one.config.PropertyConfig;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpJob implements Job {

    private static final PropertyConfig CONFIG = PropertyConfig.getInstance();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackUp.performBackUp(CONFIG); //do backup
    }
}
