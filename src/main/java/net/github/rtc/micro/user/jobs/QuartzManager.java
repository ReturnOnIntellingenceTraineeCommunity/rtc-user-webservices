package net.github.rtc.micro.user.jobs;


import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * Created by ivan on 13.05.14.
 */
public class QuartzManager implements Managed {

    private Scheduler scheduler;
    public QuartzSchedulerMonitor schedulerMonitor;
    private QuartzJobMonitor jobMonitor;

    public QuartzManager(SchedulerFactory sf) throws SchedulerException {
        scheduler = sf.getScheduler();
        schedulerMonitor = new QuartzSchedulerMonitor(); // Implements SchedulerListener
        scheduler.getListenerManager().addSchedulerListener(schedulerMonitor);
        jobMonitor = new QuartzJobMonitor(); // Implements JobListener
    }

    @Override
    public void start() throws Exception {
        scheduler.start();
        scheduler.getListenerManager().addJobListener(jobMonitor, EverythingMatcher.allJobs());
    }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener(jobMonitor.getName());
        scheduler.shutdown(true);
    }


    public boolean isHealthy(){
        //TO DO
        return false;
        //return schedulerMonitor.isHealthy() && jobMonitor.isHealthy();
    }

    public String getState() {
        //TO DO
        return null;
       // return "Scheduler: " + schedulerMonitor.getState() + " Jobs:" + jobMonitor.getState();
    }
}
