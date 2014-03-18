package roi.rtc.webservices.user.resources;

/**
 * Created by Angelos on 14.03.14.
 */

//import com.google.common.base.Optional;
//import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/hello-world")
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

    @GET
    @Path("/user")
    public UserClass sayHello() {
        UserClass user = new UserClass();
        user.setCity("ASD");
        user.setEmail("asdfghjk");
        return user;
    }
    @POST
    public UserClass getUsers (UserClass user)
    {
        ArrayList usersList = new ArrayList();

        usersList.add(user);

        return  user;
    }



}



