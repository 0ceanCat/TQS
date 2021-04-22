package webtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wy
 * @date 2021/4/22 14:00
 */
public class BuySteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @And("I choose departure city {string}")
    public void iChooseDepartureCityPhiladelphia(String city) {
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '"+ city + "']")).click();
        }
        driver.findElement(By.name("fromPort")).click();
    }

    @And("I choose destination city {string}")
    public void iChooseDestinationCityRome(String city) {
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '" + city + "']")).click();
        }
        driver.findElement(By.name("toPort")).click();
    }


    @And("I click in Find Flights")
    public void iClickInFindFlights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the '{int}'th flight")
    public void iChooseTheThFlight(Integer arg0) {
        driver.findElement(By.cssSelector("tr:nth-child(" + arg0 + ") .btn")).click();
    }

    @And("I fill the form with name {string}, address {string}, state {string}, city {string}, zipCode {string}, creditCardNumber {string}, nameOnCard {string}")
    public void iFillTheFormWithNameABCAddressERGStateBCDCitySDFZipCodeCreditCardNumberNameOnCard(String name, String city, String state, String address,
                                                                                                  String zipCode, String creditCardNumber, String nameOnCard) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(name);
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(address);
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
        driver.findElement(By.id("cardType")).click();
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber);
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);
    }

    @And("I click in Purchase Flight")
    public void iClickPurchaseFlight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should have a page with title {string}")
    public void iShouldHaveAPageWithTitleBlazeDemoConfirmation(String title) {
        assertEquals(driver.getTitle(), title);
        driver.quit();
    }
}
