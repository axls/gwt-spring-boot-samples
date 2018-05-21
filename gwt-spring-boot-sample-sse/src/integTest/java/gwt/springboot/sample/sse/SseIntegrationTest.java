package gwt.springboot.sample.sse;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.time.LocalDate;

import org.junit.Test;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class SseIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() {
        wait.until(textToBe(id("status"), "Connected"));
        wait.until(textToBePresentInElementLocated(id("lastEvent"),
                String.valueOf(LocalDate.now().getYear())));
    }
}
