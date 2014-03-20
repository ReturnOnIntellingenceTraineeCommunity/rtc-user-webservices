package roi.rtc.webservices.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import roi.rtc.webservices.user.config.MainServiceConfiguration;
import roi.rtc.webservices.user.entities.Dao1;
import roi.rtc.webservices.user.resources.Resource;

public class MainService extends Service<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate = new HibernateBundle<MainServiceConfiguration>(Dao1.class) {

        @Override
        public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.setName("hello-world");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle((ConfiguredBundle) hibernate);
    }

    @Override
    public void run(MainServiceConfiguration configuration, Environment environment) {
//        final String template = configuration.getTemplate();
//        final String defaultName = configuration.getDefaultName();
        environment.addResource(new Resource());
    }

}
