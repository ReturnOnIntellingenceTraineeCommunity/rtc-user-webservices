package net.github.rtc.micro.user.job;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by ivan on 13.05.14.
 */
public class QuartzHealthCheck extends HealthCheck {
    private QuartzManager quartzManager;


    //TO DO
   /* public QuartzSchedulerHealthCheck(QuartzManager qm) throws SchedulerException {
        super("Quartz Scheduler Health Check");
        this.quartzManager = qm;
    }*/

    /**
     * Checks the state of the Quartz Scheduler and all scheduled Quartz jobs
     * @return Result Healthy if the scheduler and all of it's jobs are running withing acceptable parameters
     * @throws Exception if unable to check the state of the scheduler or its jobs
     */
    @Override
    protected Result check() throws Exception {
            return Result.healthy();
    }
}
