package net.github.rtc.micro.user.dao;


import net.github.rtc.micro.user.entity.User;

import java.util.List;

/**
 * @author dfa
 */
public interface UserDao {

    /**
     * Get an existing user by id
     *
     * @param id user id
     * @return user object
     */
     User get(Integer id);

    /**
     * Find  user by email
     *
     * @param email user email
     * @return user object
     */
     User findByEmail(String email);

    /**
     * Find user by code
     *
     * @param code user code
     * @return user object
     */
     User findByCode(String code);

    /**
     * Save a new user in the DB
     *
     * @param user user for save
     * @return saved user
     */
     User save(User user);

    /**
     * Delete user by user code
     *
     * @param code user code
     */
     void delete(String code);

    /**
     * Find collection of users in the DB
     *
     * @return collection of users
     */
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

    /**
     * Check if admin exist in database
     *
     * @return true if exist, false - not exist
     */
     boolean isAdmin();
}
