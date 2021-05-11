import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author wy
 * @date 2021/4/6 18:42
 */
@ExtendWith(SeleniumJupiter.class)
public class AutomationTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    public AutomationTest(ChromeDriver driver) {
        this.driver = driver;
        js = driver;
        vars = new HashMap<>();
    }

    @Test
    public void test() {
        driver.get("https://blazedemo.com//");
        driver.manage().window().setSize(new Dimension(1330, 838));
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
        }
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
        }
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("ABC");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("ERG");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("BCD");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("SDF");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("cardType")).click();
        driver.findElement(By.id("cardType")).click();
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123123123");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("1");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("h1")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }
}
