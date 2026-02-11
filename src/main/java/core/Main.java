package core;

import org.openqa.selenium.WebDriver;

public class Main {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void startDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        DriverManager driverManager;
        switch (browser) {
            case "chrome" ->  driverManager = new ChromeDriverManager();
            case "edge" ->  driverManager = new EdgeDriverManager();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        DRIVER.set(driverManager.createDriver());
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

}

