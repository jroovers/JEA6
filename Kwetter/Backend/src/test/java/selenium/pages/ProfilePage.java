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
public class ProfilePage extends PageObject {

    WebElement profileFollowingCount;
    WebElement profileFollowerCount;
    WebElement profileTweetCount;

    WebElement changeProfileButton;
    WebElement profileEditName;
    WebElement profileEditBio;
    WebElement profileEditLocation;
    WebElement profileEditWebsite;
    WebElement profileEditConfirm;

    WebElement profileFollowButton;
    WebElement profileUnFollowButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(this.driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileFollowingCount")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileFollowerCount")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileTweetCount")));
    }

    public void clickChangeProfileButton() {
        // wait for page to load
        wait.until(ExpectedConditions.elementToBeClickable(By.id("changeProfileButton")));
        this.changeProfileButton = this.driver.findElement(By.id("changeProfileButton"));
        changeProfileButton.click();
        // wait for dialog
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditConfirm")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditName")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditBio")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditLocation")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditWebsite")));
        this.profileEditName = this.driver.findElement(By.id("profileEditName"));
        this.profileEditBio = this.driver.findElement(By.id("profileEditBio"));
        this.profileEditLocation = this.driver.findElement(By.id("profileEditLocation"));
        this.profileEditWebsite = this.driver.findElement(By.id("profileEditWebsite"));
        this.profileEditConfirm = this.driver.findElement(By.id("profileEditConfirm"));
    }

    public void enterTextForName(String text) {
        this.profileEditName.clear();
        this.profileEditName.sendKeys(text);
    }

    public void enterTextForBiography(String text) {
        this.profileEditBio.clear();
        this.profileEditBio.sendKeys(text);
    }

    public void enterTextForLocation(String text) {
        this.profileEditLocation.clear();
        this.profileEditLocation.sendKeys(text);
    }

    public void enterTextForWebsite(String text) {
        this.profileEditWebsite.clear();
        this.profileEditWebsite.sendKeys(text);
    }

    public void clickProfileEditConfirmButton() {
        if (this.profileEditConfirm != null) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("profileEditConfirm")));
            this.profileEditConfirm.click();
        }
    }

    public void clickFollowButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileFollowButton")));
        this.profileFollowButton = this.driver.findElement(By.id("profileFollowButton"));
        this.profileFollowButton.click();
    }

    public void clickUnfollowButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("profileUnFollowButton")));
        this.profileUnFollowButton = this.driver.findElement(By.id("profileUnFollowButton"));
        this.profileUnFollowButton.click();
    }

    public String getProfileName(String text) {
        this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + text + "')]")));
        WebElement profileName = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        return profileName.getText();
    }

    public String getFollowerName(String text) {
        this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + text + "')]")));
        WebElement followerName = driver.findElement(By.xpath("//div[contains(text(), '" + text + "')]"));
        return followerName.getText();
    }

}
