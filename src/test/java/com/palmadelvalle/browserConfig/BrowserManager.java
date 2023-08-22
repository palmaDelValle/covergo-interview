package com.palmadelvalle.browserConfig;

import com.palmadelvalle.browserConfig.browsers.*;
import com.palmadelvalle.utils.Constants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
@Slf4j
public class BrowserManager {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BrowserType browserType;

    /**
     * This method controls which browser is instantiated.
     */
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
        browserType = browser.getBrowser();
        driver = browser.getDriver();
        // Define the WebDriverWait with a default timeout.
        // The value of the timeout in seconds is informed in Constants class.
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(Constants.TIMEOUT_IN_SECONDS));
        // Maximize the browser window.
        driver.manage().window().maximize();
    }
}
