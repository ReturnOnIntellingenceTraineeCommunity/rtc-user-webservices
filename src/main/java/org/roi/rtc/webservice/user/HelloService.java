package org.roi.rtc.webservice.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class HelloService extends Service<ConfigurationClass>{
    public static void main(String[] args) throws Exception {
        new HelloService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ConfigurationClass> bootstrap) {
        bootstrap.setName("hello-world");
    }

    @Override
    public void run(ConfigurationClass configuration,Environment environment) {
        // nothing to do yet
    }
}
