package roi.rtc.webservices.user.resources;

/**
 * Created by Chernichenko Bogdan on 14.03.14.
 */

//import com.google.common.base.Optional;
//import com.yammer.metrics.annotation.Timed;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;



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

   ArrayList userArray = new ArrayList();
   @GET
   public UserClass getUser() {
       UserClass user = new UserClass();
       return user;
   }

    @POST
    public UserClass addUser (UserClass user)
    {
        userArray.add(user);
        return  user;
    }



}



