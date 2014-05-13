package net.github.rtc.micro.user.resource;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */


import io.dropwizard.hibernate.UnitOfWork;
import net.github.rtc.micro.user.dao.UserDao;
import net.github.rtc.micro.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOG =  LoggerFactory.getLogger(UserResource.class.getName());

    public UserResource() {
    }

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;

    @GET
    @UnitOfWork
    @Path("{code}")
    public User findByCode(@PathParam("code") String code) {
        User user = userDao.findByCode(code);
        if (user == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            //TO DO
            // LOG.error("Exception: ", ex);
            throw ex;
        }
        return user;
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
        LOG.info("User INFO: {}", user.toString());
        String code;
        //do {
            code = UUID.randomUUID().toString();
        //} while (userDao.exist(code));
        user.setCode(code);
        try {
            return userDao.merge(user);
        }catch (Exception ex){
            LOG.error(ex.getMessage());
        }
        return null;
    }

    @DELETE
    @UnitOfWork
    @Path("{code}")
    public boolean delete(@PathParam("code") String code) {
        userDao.delete(code);
        return true;
    }

    @GET
    @UnitOfWork
    @Path("viewAll")
    public Collection<User> getAll() {
        return userDao.findAll();
    }

}



