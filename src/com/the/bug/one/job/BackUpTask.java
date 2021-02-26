package com.the.bug.one.job;

import com.the.bug.one.backup.BackUp;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpTask implements Job {

    private final PropertyConfig config = PropertyConfig.getInstance();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackUp.performBackUp(config); //do backup
    }
}
