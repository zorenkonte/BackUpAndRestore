package com.the.bug.one.main;

import com.the.bug.one.job.BackUpTask;
import com.the.bug.one.job.PropertyConfig;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static com.the.bug.one.util.Utility.createDefaultDirectory;
import static com.the.bug.one.util.Utility.loadProperties;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    private static final PropertyConfig config = PropertyConfig.getInstance();

    public static void main(String[] args) {
        createDefaultDirectory(); //create default folder if not exist on startup
        loadProperties(); //load configs

        try {
            var sf = new StdSchedulerFactory();
            var scheduler = sf.getScheduler();
            var job = newJob(BackUpTask.class)
                    .withIdentity("backup", "groupOne")
                    .build();
            var trigger = newTrigger()
                    .withIdentity("triggerOne", "groupOne")
                    .withSchedule(cronSchedule(config.getCronExpression()))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
