package net.github.rtc.micro.user.resource;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */


import com.yammer.dropwizard.hibernate.UnitOfWork;
import net.github.rtc.micro.user.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import net.github.rtc.micro.user.dao.UserDao;


@Path("/users-web-service")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    public UserResource() {

    }
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;


    @GET
    @UnitOfWork
    @Path("{id}")
    public User getUser(@PathParam("id") Integer id) {
        return userDao.get(id);
    }

    @GET
    @UnitOfWork
    @Path("login/{email}")
    public User getLogin(@PathParam("email") String email) {
        return userDao.findByLogin(email);
    }
    

    @POST
    @UnitOfWork
    public User addUser(User user) {
        userDao.save(user);
        return user;
    }


    @DELETE
    @UnitOfWork
    @Path("{id}")
    public boolean delete(@PathParam("id") Integer id) {

        userDao.delete(id);
        return true;
    }

    @GET
    @UnitOfWork
    @Path("viewAll")
    public Collection<User> getAll() {
        return userDao.findAll();
    }

}



