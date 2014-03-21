package roi.rtc.webservices.user.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import roi.rtc.webservices.user.dao.UserDAO;
import roi.rtc.webservices.user.entities.Dao1;

/**
 * Created by Eugene on 19.03.14.
 */
public class UserDAOImpl extends AbstractDAO<Dao1> implements UserDAO {

    public UserDAOImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Dao1 insert(Dao1 dao) {
        currentSession().save(dao);
        return dao;
    }

    @Override
    public Dao1 get(int daoId) {
        return (Dao1) currentSession().get(Dao1.class, daoId);
    }
}
