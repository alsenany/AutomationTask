package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;


    //EXPLICIT WAIT (WebDriverWait)
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement el = waitUntilVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitUntilVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void goTo(String url) {
        driver.get(url);
    }

}
