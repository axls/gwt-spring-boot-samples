package gwt.springboot.sample.websockets;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import gwt.springboot.sample.test.BaseIntegrationTest;

public class WebsocketsReactiveIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test() {
        WebElement messageText = driver.findElement(id("messageText"));
        WebElement sendButton = driver.findElement(id("sendButton"));
        WebElement messages = driver.findElement(id("messages"));

        messageText.sendKeys("test");
        sendButton.click();
        wait.until(numberOfElementsToBe(xpath("//ul/li"), 1));
        WebElement li = messages.findElements(tagName("li")).get(0);
        assertThat(li.getText(), is("test"));

        messageText.sendKeys("test2");
        sendButton.click();
        wait.until(numberOfElementsToBe(xpath("//ul/li"), 2));

        List<WebElement> lis = messages.findElements(tagName("li"));
        assertThat(lis.get(0).getText(), is("test"));
        assertThat(lis.get(1).getText(), is("test2"));
    }

}
