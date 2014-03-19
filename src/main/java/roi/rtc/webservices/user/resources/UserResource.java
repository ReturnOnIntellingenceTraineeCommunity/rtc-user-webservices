package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

//import com.google.common.base.Optional;
//import com.yammer.metrics.annotation.Timed;


import roi.rtc.webservices.user.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Path("/users-web-service")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
//    private final String template;
//    private final String defaultName;
//    private final AtomicLong counter;


    public UserResource() {
//        this.template = template;
//        this.defaultName = defaultName;
//        this.counter = new AtomicLong();

    }


    Map<Integer, User> map = new HashMap();
    User user = new User();

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Integer id) {

        //map.get(id);
        return map.get(id);
    }

    @POST
    public User addUser(User user) {
        map.put(user.getId(), user);

        return user;
    }


    @DELETE
    @Path("{id}")
    public User delete(@PathParam("id") Integer id) {

        map.remove(id);

        return user;
    }

    @GET
    @Path("viewAll")
    public Collection<User> getAll() {
        return map.values();
    }
}



