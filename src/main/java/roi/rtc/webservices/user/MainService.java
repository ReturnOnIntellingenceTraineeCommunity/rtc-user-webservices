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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import roi.rtc.webservices.user.config.MainServiceConfiguration;
import roi.rtc.webservices.user.dao.UserDAO;
import roi.rtc.webservices.user.dao.impl.UserDAOImpl;
import roi.rtc.webservices.user.entity.Role;
import roi.rtc.webservices.user.entity.Roles;
import roi.rtc.webservices.user.entity.User;
import roi.rtc.webservices.user.resources.UserResource;

import java.util.Arrays;

//import com.yammer.dropwizard.migrations.MigrationsBundle;

public class MainService extends Service<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate = new HibernateBundle<MainServiceConfiguration>(Role.class,
            User.class) {

        @Override
        public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle((ConfiguredBundle) hibernate);
       /* bootstrap.addBundle(new MigrationsBundle<MainServiceConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
                return configuration.getDatabase();
            }

        });*/
    }

    /**
     * Prepare DB before start. If admin not fount will be add default.
     *
     * @param dao userDao
     * @param sessionFactory current session factory
     */
    private void prepareAdminUser(final UserDAO dao, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        session.beginTransaction();
        if (dao.checkAdmin()) {
            session.getTransaction().commit();
            User admin = new User("Test Test", "test@rtcapp.dp.ua", "testpass");
            admin.setAuthorities(Arrays.asList(new Role(Roles.ROLE_ADMIN), new Role(Roles.ROLE_USER)));
            System.out.println(admin);
            session.beginTransaction();
            try {
                dao.save(admin);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
            }
            session.close();
        }
    }


    @Override
    public void run(MainServiceConfiguration configuration, Environment environment) {
        final SessionFactory sessionFactory = hibernate.getSessionFactory();
        final UserDAO dao = new UserDAOImpl(sessionFactory);
        prepareAdminUser(dao, sessionFactory);
        environment.addResource(new UserResource(dao));
    }


}
