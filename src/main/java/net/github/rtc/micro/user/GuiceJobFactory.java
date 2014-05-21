package net.github.rtc.micro.user;

import com.google.inject.Injector;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import javax.inject.Inject;

/**
 * Created by ivan on 19.05.14.
 */
public class GuiceJobFactory implements JobFactory {
    private final Injector guice;

    @Inject
    public GuiceJobFactory(final Injector guice){
        this.guice = guice;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class jobClass = jobDetail.getJobClass();
        return (Job) guice.getInstance(jobClass);
    }
}
