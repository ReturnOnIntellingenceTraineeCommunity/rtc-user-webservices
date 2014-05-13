package net.github.rtc.micro.user;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */


import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.assets.AssetsBundle;
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
import org.hibernate.context.internal.ManagedSessionContext;

import java.util.Arrays;


public class UserService extends Application<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new UserService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration>
            hibernate = new HibernateBundle<MainServiceConfiguration>(Role.class,User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };


    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {

        //Quartz config///
        //SchedulerFactory sf = new StdSchedulerFactory(config.getSchedulerFactoryProperties());
        //QuartzManager qm = new QuartzManager(sf); // A Dropwizard Managed Object
        //env.manage(qm); // Assign the management of the object to the Service
        //env.addHealthCheck(new QuartzHealthCheck(qm)); /

        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle((ConfiguredBundle) hibernate);
        bootstrap.addBundle(new MigrationsBundle<MainServiceConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }});
    }

    private void prepareAdminUser(final UserDao dao, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        session.beginTransaction();
        if (dao.isAdmin()) {
            session.getTransaction().commit();
            User admin = new User("Test", "Test", "Test", "test@rtcapp.dp.ua", "de1082b5e436b41daa4906ceeca7f4223870ed68c2251978d5e7ad7fb1c2e55fcaa68fba3ef5b0be");
            admin.setAuthorities(Arrays.asList(new Role(RoleType.ROLE_ADMIN)));
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
        final UserDao dao = new UserDaoImpl(sessionFactory);
        prepareAdminUser(dao, sessionFactory);
        environment.jersey().register(new UserResource(dao));
    }
}
