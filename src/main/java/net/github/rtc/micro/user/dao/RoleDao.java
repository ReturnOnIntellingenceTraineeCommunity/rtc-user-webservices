package net.github.rtc.micro.user.dao;

import net.github.rtc.micro.user.entity.Role;

import java.util.List;

/**
 * Created by ivan on 13.05.14.
 */
public interface RoleDao {

    Role get(Integer id);
    Role save(Role role);
    Role delete(Integer id);
    Role update (Role role);
    boolean exist(Role role);
    List<Role> findAll();

}
