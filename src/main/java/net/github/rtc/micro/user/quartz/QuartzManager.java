package net.github.rtc.micro.user.quartz;


import io.dropwizard.lifecycle.Managed;
import net.github.rtc.micro.user.GuiceJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

import javax.inject.Inject;

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
        scheduler.start();
       }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener(jobMonitor.getName());
        scheduler.shutdown(true);
    }
}
