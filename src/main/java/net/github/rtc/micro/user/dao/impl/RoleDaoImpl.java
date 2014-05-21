package net.github.rtc.micro.user.dao.impl;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import net.github.rtc.micro.user.dao.RoleDao;
import net.github.rtc.micro.user.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by ivan on 13.05.14.
 */
public class RoleDaoImpl  implements RoleDao {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Override
    @Transactional
    public Role get(Integer id) {
        return entityManagerProvider.get().find(Role.class, id);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        entityManagerProvider.get().persist(role);
        return role;
    }

    @Override
    @Transactional
    public Role delete(Integer id) {
        Role role = (Role) ((Session)entityManagerProvider.get().getDelegate()).get(Role.class, id);
        if (role != null) {
            ((Session)entityManagerProvider.get().getDelegate()).delete(role);
        }
        return role;
    }

    @Override
    @Transactional
    public Role update(Role role) {
        ((Session)entityManagerProvider.get().getDelegate()).update(role);
        return role;
    }

    @Override
    @Transactional
    public boolean exist(Role role) {
        return get(role.getId()) != null;
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return ((Session)entityManagerProvider.get().getDelegate()).createCriteria(Role.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
