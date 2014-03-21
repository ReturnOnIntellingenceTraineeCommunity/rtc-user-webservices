package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */


import roi.rtc.webservices.user.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
    @Path("{id}")
    public User getUser(@PathParam("id") Integer id) {


        return userDAO.getById(id);
    }

    @POST
    public User addUser(User user) {

        userDAO.save(user);
        return user;
    }


    @DELETE
    @Path("{id}")
    public boolean delete(@PathParam("id") Integer id) {

        userDAO.delete(id);
        return true;
    }

    @GET
    @Path("viewAll")
    public Collection<User> getAll() {

        return userDAO.getAll();
    }
}



