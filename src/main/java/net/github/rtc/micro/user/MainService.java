package net.github.rtc.micro.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */



import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.github.rtc.micro.user.config.MainServiceConfiguration;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.dao.impl.UserDaoImpl;
import net.github.rtc.micro.user.entity.Role;
import net.github.rtc.micro.user.entity.RoleType;
import net.github.rtc.micro.user.entity.User;
import net.github.rtc.micro.user.resource.UserResource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import io.dropwizard.assets.AssetsBundle;
import org.hibernate.context.internal.ManagedSessionContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.util.Arrays;


public class MainService extends Application<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate = new HibernateBundle<MainServiceConfiguration>(Role.class,
            User.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle((ConfiguredBundle) hibernate);
        bootstrap.addBundle(new MigrationsBundle<MainServiceConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
        });
    }

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
    public void run(MainServiceConfiguration configuration, Environment environment) throws Exception {

        final SessionFactory sessionFactory = hibernate.getSessionFactory();
        final PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        final UserDao dao = new UserDaoImpl(sessionFactory, passwordEncoder);
        prepareAdminUser(dao, sessionFactory);
        environment.jersey().register(new UserResource(dao));
    }
}
