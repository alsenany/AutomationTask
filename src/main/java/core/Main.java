package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Main {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void startDriver() {
        String browser = System.getProperty("browser", "edge").toLowerCase();

        switch (browser) {
            case "chrome" -> DRIVER.set(createChrome());
            case "edge" -> DRIVER.set(createEdge());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Call Main.startDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver createChrome() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver() ;
    }

    private static WebDriver createEdge() {
        WebDriverManager.edgedriver().setup();
        return new org.openqa.selenium.edge.EdgeDriver(); // No EdgeOptions
    }

}

