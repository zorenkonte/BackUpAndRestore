package com.the.bug.one.job;

import com.the.bug.one.config.PropertyConfig;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class BackUpTask {
    private static final PropertyConfig CONFIG = PropertyConfig.getInstance();

    public static void startTask() {
        try {
            var sf = new StdSchedulerFactory();
            var scheduler = sf.getScheduler();
            var job = newJob(BackUpJob.class)
                    .withIdentity("backup", "groupOne")
                    .build();
            var trigger = newTrigger()
                    .withIdentity("triggerOne", "groupOne")
                    .withSchedule(cronSchedule(CONFIG.getCronExpression()))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
