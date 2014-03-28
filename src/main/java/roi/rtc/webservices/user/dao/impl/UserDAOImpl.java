package roi.rtc.webservices.user.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import roi.rtc.webservices.user.dao.UserDAO;
import roi.rtc.webservices.user.entity.User;

import java.util.List;
//import roi.rtc.webservices.user.resources.*;

/**
 * Created by Eugene on 19.03.14.
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    public UserDAOImpl(SessionFactory factory) {
        super(factory);
    }


    @Override
    public User getById(Integer id) {
        return get(id);
    }

    @Override
    public User getByLogin(String email)
    {
        return (User) currentSession().
                createCriteria(User.class).
                add(Restrictions.eq("email",email)).uniqueResult();
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
    public List<User> getAll() {
        return currentSession().createCriteria(User.class).list();
    }
}
