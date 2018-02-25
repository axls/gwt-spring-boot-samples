package gwt.springboot.sample.sse;

import java.time.Duration;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class Application {

    @GetMapping("events")
    Flux<ServerSentEvent<String>> events() {
        return Flux.interval(Duration.ofMillis(1000))
                .map(i -> ServerSentEvent.builder(new Date().toString()).build());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
