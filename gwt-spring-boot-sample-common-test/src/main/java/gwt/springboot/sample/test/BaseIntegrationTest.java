package gwt.springboot.sample.test;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integTest")
@RunWith(SpringRunner.class)
public class BaseIntegrationTest {

    private static final int DEFAULT_WAIT_TIMEOUT_SEC = 10;

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @LocalServerPort
    private int port = 8080;

    @BeforeClass
    public static void beforeClass() {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments("headless");
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIMEOUT_SEC, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIMEOUT_SEC);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    @Before
    public void before() {
        driver.get("http://localhost:" + port);
    }
}
