package com.palmadelvalle.browserConfig.browsers;

import com.palmadelvalle.browserConfig.Browser;
import com.palmadelvalle.browserConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Firefox implements Browser {
    private BrowserType browserType = BrowserType.FIREFOX;
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
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (System.getProperty("headless") != null) {
            firefoxOptions.addArguments("--headless");
        }
        this.driver = WebDriverManager.firefoxdriver().create();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

}