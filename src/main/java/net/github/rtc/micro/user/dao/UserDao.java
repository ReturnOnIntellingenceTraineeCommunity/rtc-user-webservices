package net.github.rtc.micro.user.dao;


import net.github.rtc.micro.user.entity.User;

import java.util.List;

/**
 * @author dfa
 */
public interface UserDao {

    User get(Integer id);

    User findByLogin(String email);

    User findByCode(String code);

    void save(User user);

    void delete(String code);

    List<User> findAll();

    /**
     * Check user code exist
     *
     * @param code user code
     * @return true if exist, false - not exist
     */
    boolean exist(String code);

    /**
     * SaveOrUpdate with merge associated entities
     *
     * @param user user object
     * @return user object with updated keys
     */
    User merge(User user);

    boolean isAdmin();

}
