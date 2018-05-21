package gwt.springboot.sample.restygwt.jersey;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import org.junit.Test;
import org.openqa.selenium.Alert;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class RestyGwtJerseyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() throws InterruptedException {
        driver.findElement(xpath("//button[text() = 'Click me']")).click();
        Alert alert = wait.until(alertIsPresent());
        assertThat(alert.getText(), is("Hello, World!"));
    }
}
