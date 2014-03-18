package roi.rtc.webservices.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import roi.rtc.webservices.user.core.ConfigurationClass;
import roi.rtc.webservices.user.resources.Resource;
/*import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;*/

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

/*    private static final SessionFactory sessionFactory
            = configureSessionFactory();
    private static ServiceRegistry serviceRegistry;


    private static SessionFactory configureSessionFactory()
            throws HibernateException {

        File file = new File("src/main/java/roi.rtc.webservices.user/config/hibernate.cfg.xml");
        Configuration configuration = new Configuration().configure(file);
        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }*/

}
