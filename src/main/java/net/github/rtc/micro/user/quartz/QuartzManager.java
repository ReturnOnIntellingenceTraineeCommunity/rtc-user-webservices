package net.github.rtc.micro.user.quartz;


import io.dropwizard.lifecycle.Managed;
import net.github.rtc.micro.user.GuiceJobFactory;
import net.github.rtc.micro.user.job.UserExportJob;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;

import javax.inject.Inject;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by ivan on 13.05.14.
 */
public class QuartzManager implements Managed {

    private Scheduler scheduler;
    private QuartzJobMonitor jobMonitor;

    @Inject
    public QuartzManager(SchedulerFactory sf, final GuiceJobFactory jobFactory) throws SchedulerException {
        jobMonitor = new QuartzJobMonitor();
        scheduler = sf.getScheduler();
        scheduler.setJobFactory(jobFactory);
    }

    @Override
    public void start() throws Exception {
        scheduler.getListenerManager().addJobListener(
                jobMonitor, EverythingMatcher.allJobs());
        JobDetail job = newJob(UserExportJob.class)
                .withIdentity("UserExportJob", "UserGroup")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("UserExportJobTrigger", "UserGroup")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0/30 * * * ?"))
                .build();

        scheduler.scheduleJob(job,trigger);
        scheduler.start();
       }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener(jobMonitor.getName());
        scheduler.shutdown(true);
    }
}
