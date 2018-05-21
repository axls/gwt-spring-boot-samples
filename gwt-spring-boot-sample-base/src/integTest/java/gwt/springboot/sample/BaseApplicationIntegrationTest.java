package gwt.springboot.sample;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Test;
import org.openqa.selenium.Alert;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class BaseApplicationIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() throws InterruptedException {
        driver.findElement(xpath("//button[text() = 'Click me']")).click();
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText(), is("Hello, World!"));
    }
}
