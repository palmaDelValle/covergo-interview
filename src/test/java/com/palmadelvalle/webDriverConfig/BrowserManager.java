package com.palmadelvalle.webDriverConfig;

import com.palmadelvalle.webDriverConfig.browsers.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
@Slf4j
public class BrowserManager {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final BrowserType browserType;

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
        wait = browser.getWebdriverWait();
    }
}
