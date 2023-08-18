package com.palmadelvalle.webDriverConfig;

import com.palmadelvalle.webDriverConfig.browsers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Getter
@Slf4j
public class BrowserManager {
    private final WebDriver driver;
    private final WebDriverWait wait;

    /*
    public BrowserManager() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, SECONDS));
        driver.manage().window().maximize();
    }
*/
    //TODO: Refactor all browsers
    public BrowserManager() {
        Browser browser = null;
        switch (BrowserType.getBrowser()) {
            case CHROMIUM:
                browser = new Chromium();
                break;
            case EDGE:
                browser = new Edge();
                break;
            case FIREFOX:
                browser = new Firefox();
                break;
            case IEXPLORER:
                browser = new IExplorer();
                break;
            case OPERA:
                browser = new Opera();
                break;
            case SAFARI:
                browser = new Safari();
                break;
            default:
                browser = new Chrome();
        }
        browser.setupBrowser();
        driver = browser.getDriver();
        wait = browser.getWebdriverWait();
    }
}
