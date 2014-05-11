package net.github.rtc.micro.user.dao;


import net.github.rtc.micro.user.entity.User;

import java.util.List;

/**
 * @author dfa
 */
public interface UserDao {

    User get(Integer id);

    User findByLogin(String email);

    void save(User user);

    void delete(Integer id);

    List<User> findAll();

    boolean isAdmin();

}
