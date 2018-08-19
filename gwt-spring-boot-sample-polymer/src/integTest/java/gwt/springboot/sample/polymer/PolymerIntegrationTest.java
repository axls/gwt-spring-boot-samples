package gwt.springboot.sample.polymer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Test;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class PolymerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() throws InterruptedException {
        var paperCheckbox = driver.findElement(xpath("//paper-checkbox"));
        assertThat(paperCheckbox, notNullValue());
        assertThat(paperCheckbox.getAttribute("checked"), is("true"));
        
        paperCheckbox.click();
        assertThat(paperCheckbox.getAttribute("checked"), nullValue());
        
        var toggleCheckBoxButton = driver.findElement(xpath("//button[text()='Toggle']"));
        toggleCheckBoxButton.click();
        assertThat(paperCheckbox.getAttribute("checked"), is("true"));
        
        var customElement = driver.findElement(xpath("//custom-element"));
        assertThat(customElement, notNullValue());
        assertThat(customElement.getText(), is("Hello from GWT!"));
    }
}
