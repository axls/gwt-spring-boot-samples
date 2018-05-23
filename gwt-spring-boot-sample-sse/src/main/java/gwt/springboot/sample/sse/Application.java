package gwt.springboot.sample.sse;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@SpringBootApplication
@EnableScheduling
@RestController
public class Application {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("events")
    SseEmitter events() {
        var emitter = new SseEmitter();
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitters.add(emitter);
        return emitter;
    }

    @Scheduled(fixedRate = 1000)
    void onEvent() {
        var now = new Date();
        var toRemove = new HashSet<>();
        for (var emitter : emitters) {
            try {
                emitter.send(now);
            } catch (IOException e) {
                //TODO: log.trace
                // Probably we have a disconnected client
                toRemove.add(emitter);
            }
        }
        if (!toRemove.isEmpty()) {
            emitters.removeAll(toRemove);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
