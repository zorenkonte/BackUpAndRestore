package com.the.bug.one.job;

import com.the.bug.one.config.PropertyConfig;
import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class BackUpTask {
    public static final Logger LOGGER = Logger.getLogger(BackUpTask.class);
    private static final PropertyConfig CONFIG = PropertyConfig.getInstance();

    public static void startTask() {
        try {
            var sf = new StdSchedulerFactory();
            var scheduler = sf.getScheduler();
            var jobKey = JobKey.jobKey("backup", "groupOne");
            var jobDetail = newJob(BackUpJob.class)
                    .withIdentity(jobKey)
                    .build();
            var trigger = newTrigger()
                    .withIdentity("triggerOne", "groupOne")
                    .withSchedule(cronSchedule(CONFIG.getCronExpression()))
                    .build();

            LOGGER.info("Cron job started");
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            LOGGER.info("Executing first job");
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
