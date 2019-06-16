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
public class HomePage extends PageObject {

    WebElement profileLink;
    WebElement createKweetTextBox;
    WebElement createKweetButton;

    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(this.driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createKweetButton")));
        this.profileLink = this.driver.findElement(By.id("profileLink"));
        this.createKweetTextBox = this.driver.findElement(By.id("createKweetTextBox"));
        this.createKweetButton = this.driver.findElement(By.id("createKweetButton"));
    }

    public void enterNewKweetText(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createKweetTextBox")));
        this.createKweetTextBox.clear();
        this.createKweetTextBox.sendKeys(text);
    }

    public HomePage submitNewKweet() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createKweetButton")));
        this.createKweetButton.click();
        this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'selenium test')]")));
        return this;
    }

    public boolean isDisplayed() {
        return createKweetButton.isDisplayed();
    }

    public String getNewKweetContent(String text) {
        this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]")));
        WebElement eNewKweetContent = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        return eNewKweetContent.getText();
    }

}
