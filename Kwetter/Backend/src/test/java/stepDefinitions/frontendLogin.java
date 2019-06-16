package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.HomePage;
import selenium.pages.LoginPage;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class frontendLogin {

    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    public frontendLogin() {
        driver = Hooks.driver;
    }

    @Given("I am on the login page of the application")
    public void i_am_on_the_login_page_of_the_application() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://localhost:4200/login");
        loginPage = new LoginPage(driver);
    }

    @When("I log in with username: {string} and password: {string}")
    public void i_log_in_with_username_and_password(String string, String string2) {
        homePage = loginPage.login(string, string2);
    }

    @Then("I should be on the home page")
    public void i_should_be_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(true, homePage.isDisplayed());
    }

}
