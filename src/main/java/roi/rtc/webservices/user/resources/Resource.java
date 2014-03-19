package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

//import com.google.common.base.Optional;
//import com.yammer.metrics.annotation.Timed;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Path("/users-web-service")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
//    private final String template;
//    private final String defaultName;
//    private final AtomicLong counter;


    public Resource() {
//        this.template = template;
//        this.defaultName = defaultName;
//        this.counter = new AtomicLong();

    }


    Map<Integer, User> map = new HashMap();
    User user = new User();

    @GET
    public User getUser() {

        return user;
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



