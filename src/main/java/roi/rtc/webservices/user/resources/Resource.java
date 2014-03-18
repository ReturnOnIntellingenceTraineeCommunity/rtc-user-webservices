package roi.rtc.webservices.user.resources;

/**
 * Created by Angelos on 14.03.14.
 */

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
    @Timed
    public UserClass sayHello(@QueryParam("name") Optional<String> name) {
        UserClass qwe = new UserClass();
        qwe.setCity("ASD");
        qwe.setEmail("asdfghjk");
        return qwe;


    }
}



