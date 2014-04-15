package net.github.rtc.micro.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

import io.dropwizard.ConfiguredBundle;
//import com.codahale.dropwizard.Service;
//import com.codahale.dropwizard.assets.AssetsBundle;
//import com.codahale.dropwizard.config.Bootstrap;
//import com.codahale.dropwizard.config.Environment;
//import com.codahale.dropwizard.db.DatabaseConfiguration;
//import com.codahale.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javafx.concurrent.Service;
import net.github.rtc.micro.user.entity.RoleType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import net.github.rtc.micro.user.config.MainServiceConfiguration;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.dao.impl.UserDaoImpl;
import net.github.rtc.micro.user.entity.Role;
import net.github.rtc.micro.user.entity.User;
import net.github.rtc.micro.user.resource.UserResource;

import java.util.Arrays;


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
    }

    /**
     * Prepare DB before start. If admin not fount will be add default.
     *
     * @param dao            userDao
     * @param sessionFactory current session factory
     */
    private void prepareAdminUser(final UserDao dao, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        session.beginTransaction();
        if (dao.checkAdmin()) {
            session.getTransaction().commit();
            User admin = new User("Test", "Test", "Test", "test@rtcapp.dp.ua", "testpass");
            admin.setAuthorities(Arrays.asList(new Role(RoleType.ROLE_ADMIN), new Role(RoleType.ROLE_USER)));
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
        final UserDao dao = new UserDaoImpl(sessionFactory);
        prepareAdminUser(dao, sessionFactory);
        environment.addResource(new UserResource(dao));
    }


}
