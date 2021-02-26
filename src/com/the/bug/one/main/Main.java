package com.the.bug.one.main;

import com.the.bug.one.job.BackUpTask;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static com.the.bug.one.util.Utility.createDefaultDirectory;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    public static void main(String[] args) {
        createDefaultDirectory(); //create default folder if not exist on startup

        try {
            var sf = new StdSchedulerFactory();
            var scheduler = sf.getScheduler();
            var job = newJob(BackUpTask.class)
                    .withIdentity("backup", "groupOne")
                    .build();
            var trigger = newTrigger()
                    .withIdentity("triggerOne", "groupOne")
                    .withSchedule(cronSchedule("* * * ? * *"))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
