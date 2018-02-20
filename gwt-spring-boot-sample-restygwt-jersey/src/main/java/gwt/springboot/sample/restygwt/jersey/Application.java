package gwt.springboot.sample.restygwt.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    ResourceConfig jerseyConfig() {
        return new ResourceConfig().register(GreetingsRestImpl.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
