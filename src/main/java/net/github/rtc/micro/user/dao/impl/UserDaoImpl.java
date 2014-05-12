package net.github.rtc.micro.user.dao.impl;

import io.dropwizard.hibernate.AbstractDAO;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.entity.RoleType;
import net.github.rtc.micro.user.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Eugene on 19.03.14.
 */
public class UserDaoImpl extends AbstractDAO<User> implements UserDao {

    public UserDaoImpl(SessionFactory factory) {
        super(factory);
    }


    @Override
    public User getById(Integer id) {
        return get(id);
    }

    @Override
    public User findByLogin(String email) {
        return (User) currentSession().
                createCriteria(User.class).
                add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void delete(Integer id) {
        User user = get(id);
        currentSession().delete(user);
    }

    @Override
    public List<User> findAll() {
        return currentSession().createCriteria(User.class).list();
    }

    @Override
    public boolean isAdmin() {
        return ((Long) currentSession().createCriteria(User.class).setFetchMode("authorities", FetchMode.SELECT)
                .createAlias("authorities", "authorities").add(Restrictions.disjunction()
                        .add(Restrictions.eq("authorities.name", RoleType.ROLE_ADMIN)))
                .setProjection(Projections.rowCount()).uniqueResult()) == 0;
    }
}
