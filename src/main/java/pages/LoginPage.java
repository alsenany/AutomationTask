package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//*[@id='login']/button/i");
    private final By successMarker = By.xpath("//div[contains(text(),'You logged into a secure area!')]") ;

   public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(String baseUrl) {
        goTo(baseUrl);
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

    public boolean isLoggedIn() {
       return isDisplayed(successMarker) ;
    }




}
