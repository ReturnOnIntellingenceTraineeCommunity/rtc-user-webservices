package net.github.rtc.micro.user.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ivan on 15.05.14.
 */
public class UserExportJob implements Job {
    private static final Logger LOG =  LoggerFactory.getLogger(UserExportJob.class.getName());


    public void execute(JobExecutionContext context)
            throws JobExecutionException {


    }
}
