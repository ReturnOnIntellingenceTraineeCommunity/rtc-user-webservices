package roi.rtc.webservices.user.resources;

/**
 * Created by Angelos on 14.03.14.
 */

import roi.rtc.webservices.user.core.SayHello;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)

public class Resource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public Resource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();

    }
    @GET
    @Timed
    public SayHello sayHello(@QueryParam("name") Optional<String> name) {
        return new SayHello(counter.incrementAndGet(),
                String.format(template, name.or(defaultName)));
    }
}



