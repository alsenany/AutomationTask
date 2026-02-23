package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By registerBtn = By.cssSelector("button[type='submit']"); // //*[@id="root"]/div[2]/div/div/div/div/form/button

    private final By emailError = By.xpath("//div[contains(@class,'alert-danger') and contains(.,'Email is required')]");
    private final By confirmPwdError = By.xpath("//div[contains(@class,'alert-danger') and contains(.,'Passwords do not match')]");

    private final By ruleMinLength = By.xpath("//*[contains(text(),'At least 8')]");
    private final By ruleUppercase = By.xpath("//*[contains(text(),'uppercase')]");
    private final By ruleLowercase = By.xpath("//*[contains(text(),'lowercase')]");
    private final By ruleNumber = By.xpath("//*[contains(text(),'number')]");
    private final By ruleSpecial = By.xpath("//*[contains(text(),'special')]");

    private final By successBanner = By.xpath("//*[contains(text(),'Registration successful')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage open(String url) {
        goTo(url);
        waitUntilVisible(registerBtn);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public RegistrationPage typePassword(String pwd) {
        type(passwordInput, pwd);
        return this;
    }

    public RegistrationPage typeConfirmPassword(String pwd) {
        type(confirmPasswordInput, pwd);
        return this;
    }

    public RegistrationPage clickRegister() {
        click(registerBtn);
        return this;
    }

    public boolean isSuccessBannerVisible() {
        return isDisplayed(successBanner);
    }

    public boolean isEmailErrorVisible() {
        return isDisplayed(emailError);
    }

    public String getConfirmPasswordError() {
        try {
            return waitUntilVisible(confirmPwdError).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public boolean ruleMinLengthPassed() {
        return isRulePassed(ruleMinLength);
    }
    public boolean ruleUppercasePassed() {
        return isRulePassed(ruleUppercase);
    }
    public boolean ruleLowercasePassed() {
        return isRulePassed(ruleLowercase);
    }
    public boolean ruleNumberPassed() {
        return isRulePassed(ruleNumber);
    }
    public boolean ruleSpecialPassed() {
        return isRulePassed(ruleSpecial);
    }

    private boolean isRulePassed(By locator) {
        try {
            WebElement el = waitUntilVisible(locator);

            String cls = el.getAttribute("class");

            if (cls != null && cls.matches(".*(valid|pass|success).*"))
                return true;

            String color = el.getCssValue("color");
            return color != null && (color.contains("0, 128, 0") || color.contains("33, 186, 69"));
        }
        catch (TimeoutException e) {
            return false;
        }
    }

    public boolean forceShowEmailValidationMessage() {
        WebElement email = driver.findElement(emailInput);
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].reportValidity();", email);
    }

    public boolean isEmailValidByConstraintValidationApi() {
        WebElement email = driver.findElement(emailInput);
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();", email);
    }

}