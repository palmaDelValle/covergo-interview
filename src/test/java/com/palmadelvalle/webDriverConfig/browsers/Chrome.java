package com.palmadelvalle.webDriverConfig.browsers;

import com.palmadelvalle.webDriverConfig.Browser;
import com.palmadelvalle.webDriverConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Chrome implements Browser {
    private BrowserType browserType = BrowserType.CHROME;
    private WebDriver driver;
    @Override
    public BrowserType getBrowser() {
        return browserType;
    }
    @Override
    public WebDriver getDriver() {
        return driver;
    }
    @Override
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void setupBrowser() {
        this.driver = WebDriverManager.chromedriver().create();
    }

}