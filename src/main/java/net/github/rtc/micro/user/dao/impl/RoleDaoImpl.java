package net.github.rtc.micro.user.dao.impl;

import io.dropwizard.hibernate.AbstractDAO;
import net.github.rtc.micro.user.dao.RoleDao;
import net.github.rtc.micro.user.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by ivan on 13.05.14.
 */
public class RoleDaoImpl extends AbstractDAO<Role> implements RoleDao {

    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Role get(Integer id) {
        return get(id);
    }

    @Override
    public Role save(Role role) {
        return persist(role);
    }

    @Override
    public Role delete(Integer id) {
        Role role = (Role) currentSession().get(Role.class, id);
        if (role != null) {
            currentSession().delete(role);
        }
        return role;
    }

    @Override
    public Role update(Role role) {
        currentSession().update(role);
        return role;
    }

    @Override
    public boolean exist(Role role) {
        return get(role.getId()) != null;
    }

    @Override
    public List<Role> findAll() {
        return currentSession().createCriteria(Role.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
