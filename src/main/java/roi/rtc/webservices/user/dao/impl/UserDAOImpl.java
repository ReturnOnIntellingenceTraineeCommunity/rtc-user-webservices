package roi.rtc.webservices.user.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import roi.rtc.webservices.user.dao.UserDAO;
import roi.rtc.webservices.user.entity.User;

import java.util.List;

/**
 * Created by Eugene on 19.03.14.
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    public UserDAOImpl(SessionFactory factory) {
        super(factory);
    }


    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
