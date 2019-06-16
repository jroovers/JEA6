package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class LoginPage extends PageObject {

    private WebElement username;
    private WebElement password;
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(this.driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginSubmit")));
        this.username = this.driver.findElement(By.id("loginUsername"));
        this.password = this.driver.findElement(By.id("loginPassword"));
        this.submitButton = this.driver.findElement(By.id("loginSubmit"));

    }

    public void enterCredentials(String username, String password) {
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void submit() {
        submitButton.click();
    }

    public LoginPage loginExpectingWrongCredentials(String username, String password) {
        this.enterCredentials(username, password);
        this.submit();
        return new LoginPage(this.driver);
    }

    public HomePage login(String username, String password) {
        this.enterCredentials(username, password);
        this.submit();
        return new HomePage(this.driver);
    }

    public boolean isDisplayed() {
        return submitButton.isDisplayed();
    }
}
