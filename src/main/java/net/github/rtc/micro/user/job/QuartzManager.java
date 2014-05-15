package net.github.rtc.micro.user.job;


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
    private QuartzJobMonitor jobMonitor;

    public QuartzManager(SchedulerFactory sf) throws SchedulerException {
        scheduler = sf.getScheduler();
        jobMonitor = new QuartzJobMonitor();
    }

    @Override
    public void start() throws Exception {

        scheduler.getListenerManager().addJobListener(
                new QuartzJobMonitor(), EverythingMatcher.allJobs());
        scheduler.start();
       }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener(jobMonitor.getName());
        scheduler.shutdown(true);
    }


    public boolean isHealthy(){
        return true;
        //return jobMonitor.isHealthy();
    }

    public String getState() {
        return "Hello";
       //TO DO
       //return null;
       //return "Scheduler: " + schedulerMonitor.getState() + " Jobs:" + jobMonitor.getState();
    }
}
