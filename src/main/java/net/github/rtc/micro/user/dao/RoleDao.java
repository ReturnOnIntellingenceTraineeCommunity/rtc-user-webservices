package net.github.rtc.micro.user.dao;

import net.github.rtc.micro.user.entity.Role;

import java.util.List;

/**
 * Created by ivan on 13.05.14.
 */
public interface RoleDao {

    /**
     * Get an existing role by id
     *
     * @param id user id
     * @return saved role
     */
    Role get(Integer id);

    /**
     * Save a new role in the DB
     *
     * @param role role for save
     * @return saved role
     */
    Role save(Role role);

    /**
     * Delete role by id
     *
     * @param id role to delete id
     * @return deleted role
     */
    Role delete(Integer id);

    /**
     * Update existing role
     *
     * @param role role to update
     * @return updated role
     */
    Role update (Role role);

    /**
     * Check if current role exist in DB
     *
     * @param role role to check
     * @return true if exist, false - not exist
     */
    boolean exist(Role role);

    /**
     * Find collection of roles in the DB
     *
     * @return collection of roles
     */
    List<Role> findAll();

}
