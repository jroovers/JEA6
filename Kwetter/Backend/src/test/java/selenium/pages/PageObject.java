package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * Common pageobject for shared webelementes in application
 *
 */
abstract public class PageObject {

    public WebDriver driver;
    public WebDriverWait wait;
    public boolean bResult;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        this.bResult = true;
    }
}
