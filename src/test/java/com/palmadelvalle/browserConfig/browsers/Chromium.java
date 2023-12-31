package com.palmadelvalle.browserConfig.browsers;

import com.palmadelvalle.browserConfig.Browser;
import com.palmadelvalle.browserConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Chromium implements Browser {
    private BrowserType browserType = BrowserType.CHROMIUM;
    private WebDriver driver;

    private WebDriverWait wait;
    @Override
    public BrowserType getBrowser() {
        return browserType;
    }
    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public WebDriverWait getWebdriverWait() {
        return wait;
    }
    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public void setupBrowser() {
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless") != null) {
            options.addArguments("--headless");
        }
        this.driver = WebDriverManager.chromiumdriver().capabilities(options).create();
    }
}