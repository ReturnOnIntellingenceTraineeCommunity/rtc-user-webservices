package roi.rtc.webservices.user;

/**
 * Created by Angelos on 14.03.14.
 */
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import roi.rtc.webservices.user.core.ConfigurationClass;
import roi.rtc.webservices.user.resources.Resource;

public class MainService extends Service<ConfigurationClass> {
    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ConfigurationClass> bootstrap) {
        bootstrap.setName("hello-world");
    }

    @Override
    public void run(ConfigurationClass configuration,Environment environment) {
//        final String template = configuration.getTemplate();
//        final String defaultName = configuration.getDefaultName();
        environment.addResource(new Resource());
    }
}
