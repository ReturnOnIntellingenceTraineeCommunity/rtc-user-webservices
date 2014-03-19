package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

//import com.google.common.base.Optional;
//import com.yammer.metrics.annotation.Timed;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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


    Map map = new HashMap();
    UserClass user = new UserClass();

   @GET
   public UserClass getUser() {

       return user;
   }

    @POST
    public UserClass addUser (UserClass user)
    {
        map.put(user.getId(),user);

        return  user;
    }


@GET
@Path("{id}")
    public UserClass delete (@PathParam("id") Integer id )
    {

        map.remove(user.getId());

        return user;
    }

   /*@GET
    public Collection<Map> getAll ()
    {
        return map.;
    }*/
}



