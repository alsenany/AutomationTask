package test;

import core.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;


import static org.junit.jupiter.api.Assertions.*;

public class test {

    private WebDriver driver;
    private String baseUrl;
    private String username;
    private String password;


    @BeforeEach
    public void setup() {
        Main.startDriver();
        driver = Main.getDriver();

        baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com/login");
        username = System.getProperty("username", "tomsmith");
        password = System.getProperty("password", "SuperSecretPassword!");
    }

    @AfterEach
    public void tearDown() {
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
        // assertTrue means the test will pass if the condition returns true
    }

    @Test public void invalidLogin_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("wrongUser")
                .enterPassword("wrongPass")
                .submit();
        assertFalse(login.isLoggedIn(), "Expected login to fail.");
    }


    @Test
    public void correctUsername_wrongPassword_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername(username)        // valid
                .enterPassword("wrongPass")     // invalid
                .submit();

        assertFalse(login.isLoggedIn(), "Expected user NOT to be logged in with wrong password.");
    }

    @Test
    public void wrongUsername_validPassword_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("wrongUsername")        // valid
                .enterPassword(password)     // invalid
                .submit();

        assertFalse(login.isLoggedIn(), "Expected user NOT to be logged in with wrong username.");
    }
        @Test
    public void emptyUsername_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("")
                .enterPassword("fakePassword")
                .submit();

        assertFalse(login.isLoggedIn(), "Expected error for empty username.");
    }
    @Test
    public void emptyPassword_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("fakeUser")
                .enterPassword("")
                .submit();

        assertFalse(login.isLoggedIn(), "Expected error for empty password.");
    }

    @Test
    public void emptyCredentials_shouldFail() {
        LoginPage login = new LoginPage(driver)
                .open(baseUrl)
                .enterUsername("")
                .enterPassword("")
                .submit();

        assertFalse(login.isLoggedIn(), "Expected error when both fields are empty.");
        // isLoggedIn method should return false and our test case will pass.

    }


}