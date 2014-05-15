package net.github.rtc.micro.user.job;

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
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("jobWasExecuted");

        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is finished...");

        if (!e.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + e.getMessage());
        }
    }
}
