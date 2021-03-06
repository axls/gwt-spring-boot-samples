package gwt.springboot.sample.websockets;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

@SpringBootApplication
@Controller
public class Application {

    @Bean
    UnicastProcessor<String> publisher() {
        return UnicastProcessor.create();
    }

    @Bean
    Flux<String> messages(UnicastProcessor<String> publisher) {
        return publisher.replay(10).autoConnect();
    }

    @Bean
    HandlerMapping webSocketMapping(UnicastProcessor<String> publisher, Flux<String> messages) {
        var handlers = new HashMap<String, WebSocketHandler>();
        handlers.put("/messages", new WebSocketHandler() {
          @Override
          public Mono<Void> handle(WebSocketSession session) {
              //get message from the session and send them to the publisher, so, the other clients will be notified  
              session.receive().map(WebSocketMessage::getPayloadAsText).subscribe(publisher::onNext);
              return session.send(messages.map(session::textMessage));
          }
        });

        var mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(handlers);
        mapping.setOrder(-1);
        return mapping;
    }

    @Bean
    WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    // Spring Boot WebFlux auto-configuration doesn't provide welcome page setup.
    // So, we have to setup this one manually.
    @GetMapping("/")
    String index() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
