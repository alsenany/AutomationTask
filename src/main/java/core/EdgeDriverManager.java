package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EdgeDriverManager implements DriverManager {

    @Override
    public WebDriver createDriver()
    {
        WebDriverManager.edgedriver().setup();
        return new ChromeDriver() ;
    }
}
