/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import selenium.pages.HomePage;
import selenium.pages.LoginPage;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class frontendCreateKweet {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    public frontendCreateKweet() {
        this.driver = Hooks.driver;
    }

    @Given("I am logged in to the application")
    public void i_am_logged_in_to_the_application() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://localhost:4200/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("jeroen", "password");
    }

    @When("I type some text for a kweet: {string}")
    public void i_type_some_text_for_a_kweet(String string) {
        // Write code here that turns the phrase above into concrete actions
        homePage.enterNewKweetText(string);
    }

    @When("I confirm my kweet")
    public void i_confirm_my_kweet() {
        // Write code here that turns the phrase above into concrete actions
        homePage.submitNewKweet();
    }

    @Then("my kweet should be added with text {string}")
    public void my_kweet_should_be_added_with_text(String string) {
        // Write code here that turns the phrase above into concrete actions
        String result = homePage.getNewKweetContent(string);
        Assert.assertEquals(string.trim(), result.trim());
    }

}
