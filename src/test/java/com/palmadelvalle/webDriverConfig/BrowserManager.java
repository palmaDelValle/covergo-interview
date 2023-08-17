package com.palmadelvalle.webDriverConfig;

import com.palmadelvalle.webDriverConfig.browsers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Getter
@Slf4j
public class BrowserManager {
    private final WebDriver driver;

    /*
    public BrowserManager() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
        driver.manage().window().maximize();
    }
*/
    //TODO: Refactor all browsers
    public BrowserManager() {
        switch (BrowserType.getBrowser()) {
            case CHROMIUM:
                Chromium chromium = new Chromium();
                chromium.setupBrowser();
                driver = chromium.getDriver();
                break;
            case EDGE:
                driver = new Edge().getDriver();
                break;
            case FIREFOX:
                driver = new Firefox().getDriver();
                break;
            case IEXPLORER:
                driver = new IExplorer().getDriver();
                break;
            case OPERA:
                driver = new Opera().getDriver();
                break;
            case SAFARI:
                Safari safari = new Safari();
                safari.setupBrowser();
                driver = safari.getDriver();
                break;
            default:
                Chrome chrome = new Chrome();
                chrome.setupBrowser();
                driver = chrome.getDriver();
        }
    }
}
