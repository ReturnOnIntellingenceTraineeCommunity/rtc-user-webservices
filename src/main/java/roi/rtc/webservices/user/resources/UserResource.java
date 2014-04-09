package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */


import com.yammer.dropwizard.hibernate.UnitOfWork;
import roi.rtc.webservices.user.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import roi.rtc.webservices.user.dao.UserDAO;


@Path("/users-web-service")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    public UserResource() {

    }
    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private UserDAO userDAO;


    @GET
    @UnitOfWork
    @Path("{id}")
    public User getUser(@PathParam("id") Integer id) {
        return userDAO.getById(id);
    }

    @GET
    @UnitOfWork
    @Path("login/{email}")
    public User getLogin(@PathParam("email") String email) {
        return userDAO.getByLogin(email);
    }

    @POST
    @UnitOfWork
    public User addUser(User user) {
        userDAO.save(user);
        return user;
    }

    @POST
    @UnitOfWork
    @Path("update")
    public void update(User user)
    {
        userDAO.update(user);

    }

    @DELETE
    @UnitOfWork
    @Path("{id}")
    public boolean delete(@PathParam("id") Integer id) {

        userDAO.delete(id);
        return true;
    }

    @GET
    @UnitOfWork
    @Path("viewAll")
    public Collection<User> getAll() {
        return userDAO.getAll();
    }

}



