package gwt.springboot.sample.vuegwt;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class VuegwtIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() throws InterruptedException {
        wait.until(textToBe(id("message"), "Hello from Vue GWT!"));
        
        var input = driver.findElement(id("input"));
        input.clear();
        input.sendKeys("test");
        wait.withTimeout(3, TimeUnit.SECONDS).until(textToBe(id("message"), "test"));
    }
}
