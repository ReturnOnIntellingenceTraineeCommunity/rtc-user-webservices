package net.github.rtc.micro.user;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.github.rtc.micro.user.config.MainServiceConfiguration;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.dao.impl.UserDaoImpl;
import net.github.rtc.micro.user.quartz.QuartzHealthCheck;
import net.github.rtc.micro.user.quartz.QuartzManager;
import net.github.rtc.micro.user.resource.UserResource;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Properties;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */
public class UserService extends Application<MainServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new UserService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(new MigrationsBundle<MainServiceConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }});
    }


    @Override
    public void run(MainServiceConfiguration configuration, Environment environment) throws Exception {

        final SchedulerFactory sf = new StdSchedulerFactory();

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(SchedulerFactory.class).toInstance(sf);
                bind(UserDao.class).to(UserDaoImpl.class);
            }
        }, createJpaPersistModule(configuration.getDatabase()));

        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.servlets().addFilter("JPAFilter", injector.getInstance(PersistFilter.class)).
                addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        QuartzManager qm = injector.getInstance(QuartzManager.class);
        environment.lifecycle().manage(qm);

        QuartzHealthCheck healthCheck = new QuartzHealthCheck();
        environment.healthChecks().register("quartz", healthCheck);
    }

   /* private void prepareAdminUser(final UserDao dao, final SessionFactory sessionFactory) {
        final Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        session.beginTransaction();
        if (dao.isAdmin()) {
            session.getTransaction().commit();
            User admin = new User("Test", "Test", "Test", "1111","test@rtcapp.dp.ua",
                    new Date(), "DNK", "DNU", "FPM", "PZ", "none", "", null,
                    true, true, true, true);
            admin.setAuthorities(Arrays.asList(new Role(RoleType.ROLE_ADMIN)));
            admin.setCode(UUID.randomUUID().toString());
            session.beginTransaction();
            try {
                dao.save(admin);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
            }
            session.close();
        }
    }    */

    private JpaPersistModule createJpaPersistModule(DataSourceFactory conf) {
        Properties props = new Properties();
        props.put("javax.persistence.jdbc.url", conf.getUrl());
        props.put("javax.persistence.jdbc.user", conf.getUser());
        props.put("javax.persistence.jdbc.password", conf.getPassword());
        props.put("javax.persistence.jdbc.driver", conf.getDriverClass());
        JpaPersistModule jpaModule = new JpaPersistModule("Default");
        jpaModule.properties(props);
        return jpaModule;
    }

}
