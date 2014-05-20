package net.github.rtc.micro.user.job;


import com.google.inject.Inject;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.entity.User;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by ivan on 15.05.14.
 */
public class UserExportJob implements Job {
    private static final Logger LOG =  LoggerFactory.getLogger(UserExportJob.class.getName());

    @Inject
    private UserDao userDao;

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        List<User> userList = userDao.findAll();
        //Export logic here
        System.out.println(userList);
    }
}
