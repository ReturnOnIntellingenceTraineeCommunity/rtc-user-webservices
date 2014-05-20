package net.github.rtc.micro.user.dao.impl;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.entity.RoleType;
import net.github.rtc.micro.user.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Eugene on 19.03.14.
 */
public class UserDaoImpl implements UserDao {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Override
    public User getById(Integer id) {
        return entityManagerProvider.get().find(User.class, id);
    }

    @Override
    public User findByLogin(String email) {
        return (User) ((Session)entityManagerProvider.get().getDelegate()).
                createCriteria(User.class).
                add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public User findByCode(String code) {
        return (User) ((Session)entityManagerProvider.get().getDelegate()).createCriteria(User.class)
                .add(Restrictions.eq("code", code)).uniqueResult();
    }

    @Override
    @Transactional
    public User save(User user) {
        entityManagerProvider.get().persist(user);
        return  user;
    }

    @Override
    public void delete(String code) {
        User user = findByCode(code);
        if (user != null) {
            entityManagerProvider.get().remove(user);
        }
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return entityManagerProvider.get().createQuery("select u from User u").getResultList();
    }

    @Override
    public boolean exist(String code) {
        return ((Long) entityManagerProvider.get().createQuery("select count(*) from User u where u.code = :code")
                .setParameter("code", code).getSingleResult()) != 0;
    }

    @Override
    public boolean isAdmin() {
        return ((Long)((Session)entityManagerProvider.get().getDelegate()).createCriteria(User.class).setFetchMode("authorities", FetchMode.SELECT)
                .createAlias("authorities", "authorities").add(Restrictions.disjunction()
                        .add(Restrictions.eq("authorities.name", RoleType.ROLE_ADMIN)))
                .setProjection(Projections.rowCount()).uniqueResult()) == 0;
    }


    @Override
    public User merge(User user) {
        entityManagerProvider.get().merge(user);
        return user;
    }
}
