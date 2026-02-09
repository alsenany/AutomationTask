package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // ===== Locators (update these) =====
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//*[@id='login']/button/i");
    private final By errorBanner   = By.xpath("//*[@id='flash']");
    private final By successMarker = By.xpath("//*[@id='content']") ;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage open(String baseUrl) {
        goTo(baseUrl); // If needed: goTo(baseUrl + "/login");
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public LoginPage submit() {
        click(loginButton);
        return this;
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorBanner);
    }

    public boolean isLoggedIn() {
        // Prefer a robust marker: a specific element visible on the landing page after login.

        return isDisplayed(successMarker);
    }
}
