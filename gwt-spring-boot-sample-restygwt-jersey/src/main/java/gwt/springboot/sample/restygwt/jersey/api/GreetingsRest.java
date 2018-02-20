package gwt.springboot.sample.restygwt.jersey.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.DirectRestService;

@Path("/greetings")
public interface GreetingsRest extends DirectRestService {

    @GET
    Greeting getGreeting(@QueryParam("name") String name);
}
