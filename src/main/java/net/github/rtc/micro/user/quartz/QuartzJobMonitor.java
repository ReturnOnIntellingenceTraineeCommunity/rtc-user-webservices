package net.github.rtc.micro.user.quartz;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ivan on 13.05.14.
 */
public class QuartzJobMonitor implements JobListener {
    public static final String LISTENER_NAME = "dummyJobListenerName";
    private static final Logger LOG =  LoggerFactory.getLogger(JobListener.class.getName());

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        LOG.info("jobToBeExecuted","Job : " + jobName + " is going to start...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        LOG.info("jobExecutionVetoed!!!");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        LOG.info("jobWasExecuted" ,"Job : " + jobName + " is finished...");
    }
}
