import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SampleTest {
    private WebDriver browser;

    @BeforeEach
    public void init() {
        // System.setProperty("webdriver.chrome.driver", "C:\\Webriver\\bin\\chromedriver.exe");
        browser = new ChromeDriver();
    }

    @AfterEach
    public void close() {
        browser.close();
    }

    @Test
    public void site_header_is_on_home_page() {
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue(href.isDisplayed());
    }
}