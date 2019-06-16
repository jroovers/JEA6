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
import selenium.pages.ProfilePage;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class frontendProfile {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;

    public frontendProfile() {
        this.driver = Hooks.driver;
    }

    @Given("on my profile")
    public void on_my_profile() {
        driver.get("http://localhost:4200/jeroen");
        profilePage = new ProfilePage(driver);
    }

    @When("choose to change my profile")
    public void choose_to_change_my_profile() {
        // Write code here that turns the phrase above into concrete actions
        profilePage.clickChangeProfileButton();
    }

    @When("enter some details")
    public void enter_some_details() {
        // Write code here that turns the phrase above into concrete actions
        profilePage.enterTextForName("Selenium Name");
        profilePage.enterTextForBiography("Automatische biography");
        profilePage.enterTextForLocation("Test locatie");
        profilePage.enterTextForWebsite("www.google.nl");
    }

    @When("confirm my changes")
    public void confirm_my_changes() {
        // Write code here that turns the phrase above into concrete actions
        profilePage.clickProfileEditConfirmButton();
    }

    @Then("I expect these values to be visible on my profile")
    public void i_expect_these_values_to_be_visible_on_my_profile() {
        // Write code here that turns the phrase above into concrete actions
        String output = profilePage.getProfileName("Selenium");
        Assert.assertEquals("Selenium Name", output);
    }

}
