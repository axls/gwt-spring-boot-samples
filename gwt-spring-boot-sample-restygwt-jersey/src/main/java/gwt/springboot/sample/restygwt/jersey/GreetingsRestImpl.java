package gwt.springboot.sample.restygwt.jersey;

import javax.ws.rs.Path;

import gwt.springboot.sample.restygwt.jersey.api.Greeting;
import gwt.springboot.sample.restygwt.jersey.api.GreetingsRest;

@Path("/greetings")
class GreetingsRestImpl implements GreetingsRest {

    @Override
    public Greeting getGreeting(String name) {
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello, " + name + "!");
        return greeting;
    }
}
