package test;

import core.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class test {

    private WebDriver driver;
    private String baseUrl;
    private String username;
    private String password;
    private Object Thread;

    @BeforeEach
    public void setup() {
        Main.startDriver();
        driver = Main.getDriver();

        baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com/login");
        username = System.getProperty("username", "tomsmithg");
        password = System.getProperty("password", "SuperSecretPassword!");
    }

    @AfterEach
    public void tearDown() {

        new WebDriverWait(Main.getDriver(), Duration.ofSeconds(300))
                .until(driver -> true);

        Main.quitDriver();

    }

    @Test
    public void validLogin_shouldSucceed() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        assertTrue(login.isLoggedIn(), "Expected successful login.");
    }

    @Test
    public void invalidLogin_shouldShowError() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("wrong")
                .enterPassword("wrong")
                .submit();

        assertTrue(login.isErrorVisible(), "Expected error to be visible.");
    }
}