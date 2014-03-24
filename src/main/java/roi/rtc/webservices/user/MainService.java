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
import com.yammer.dropwizard.migrations.MigrationsBundle;
import roi.rtc.webservices.user.config.MainServiceConfiguration;
import roi.rtc.webservices.user.dao.UserDAO;
import roi.rtc.webservices.user.dao.impl.UserDAOImpl;
import roi.rtc.webservices.user.entity.User;
import roi.rtc.webservices.user.resources.UserResource;

public class MainService extends Service<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate = new HibernateBundle<MainServiceConfiguration>(User.class) {

        @Override
        public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle((ConfiguredBundle) hibernate);
        bootstrap.addBundle(new MigrationsBundle<MainServiceConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
                return configuration.getDatabase();
            }

        });
    }

    @Override
    public void run(MainServiceConfiguration configuration, Environment environment) {
        final UserDAO dao = new UserDAOImpl(hibernate.getSessionFactory());
        environment.addResource(new UserResource(dao));
    }



}
