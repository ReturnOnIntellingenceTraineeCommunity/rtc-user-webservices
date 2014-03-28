package roi.rtc.webservices.user.dao;


import roi.rtc.webservices.user.entity.User;
import java.util.List;

/**
 *
 * @author dfa
 */
public interface UserDAO {

    User getById (Integer id);

    User getByLogin(String email);

    void save (User user);

    void delete (Integer id);

    List<User> getAll();

}
