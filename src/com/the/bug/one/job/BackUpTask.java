package com.the.bug.one.job;

import com.the.bug.one.backup.BackUp;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BackUp.performBackUp("localhost", "root", "@d4rkrooT3646", "tigergraphics_accounting"); //do backup
    }
}
