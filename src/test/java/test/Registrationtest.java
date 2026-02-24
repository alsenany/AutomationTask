package test;

import core.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Registrationtest {

    private WebDriver driver;
    private String baseUrl;
    private String emailAddress;
    private String password;
    private String confirmPassword;

    @BeforeEach
    public void setup() {
        Main.startDriver();
        driver = Main.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        baseUrl = System.getProperty("baseUrl", "https://testerbud.com/register");
        emailAddress = System.getProperty("emailAddress", "yahya@gmail.com");
        password = System.getProperty("password", "AliMohamed2026#");
        confirmPassword = System.getProperty("confirmPassword", "AliMohamed2026#");
    }

    @AfterEach
    public void tearDown() {
        Main.quitDriver();
    }

    @Test
    public void testPasswordRules_minLength() {
        RegistrationPage page = new RegistrationPage(driver)
                .open(baseUrl)
                .typePassword("short");

        assertFalse(page.ruleMinLengthPassed(),
                "Min length rule should fail for short password");
    }

    @Test
    public void ruleUppercase_ShouldPass_WhenContainsUppercase() {
        RegistrationPage page = new RegistrationPage(driver)
                .open(baseUrl)
                .typePassword("A");

        assertTrue(page.ruleUppercasePassed());
        assertFalse(page.ruleLowercasePassed());
        assertFalse(page.ruleNumberPassed());
        assertFalse(page.ruleSpecialPassed());
    }

    @Test
    public void ruleLowercase_ShouldPass_WhenContainsLowercase() {
        RegistrationPage page = new RegistrationPage(driver)
                .open(baseUrl)
                .typePassword("a");

        assertTrue(page.ruleLowercasePassed());
        assertFalse(page.ruleUppercasePassed());
        assertFalse(page.ruleNumberPassed());
        assertFalse(page.ruleSpecialPassed());
    }

    @Test
    public void ruleNumber_ShouldPass_WhenContainsDigit() {
        RegistrationPage page = new RegistrationPage(driver)
                .open(baseUrl)
                .typePassword("1");

        assertTrue(page.ruleNumberPassed());
        assertFalse(page.ruleUppercasePassed());
        assertFalse(page.ruleLowercasePassed());
        assertFalse(page.ruleSpecialPassed());
    }

    @Test
    public void testConfirmPasswordMismatch() {
        RegistrationPage registrationPage = new RegistrationPage(driver)
                .open(baseUrl)
                .typeEmail("example@gmail.com")
                .typePassword("Hello2026#")
                .typeConfirmPassword("Helloteam2026#")
                .clickRegister();
        assertTrue( registrationPage.getConfirmPasswordError(), "Error message should indicate passwords do not match");
    }

    @Test
    public void testSuccessfulRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver)
                .open(baseUrl)
                .typeEmail("Ramez@gmail.com")
                .typePassword("Ramez2026#")
                .typeConfirmPassword("Ramez2026#")
                .clickRegister();

        assertTrue(registrationPage.isSuccessBannerVisible(),
                "Success banner should be visible after valid registration");
    }

    @Test
    public void testEmptyEmailValidation() {
        RegistrationPage registrationPage = new RegistrationPage(driver)
                .open(baseUrl)
                .typePassword(password)
                .typeConfirmPassword(confirmPassword)
                .clickRegister() ;


        assertTrue(registrationPage.isEmailErrorVisible(), "Expected inline email error for empty email");
    }


}