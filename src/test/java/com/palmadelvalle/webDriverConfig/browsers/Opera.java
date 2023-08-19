package com.palmadelvalle.webDriverConfig.browsers;

import com.palmadelvalle.webDriverConfig.Browser;
import com.palmadelvalle.webDriverConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class Opera implements Browser {
    private BrowserType browserType = BrowserType.OPERA;
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
        if (System.getProperty("headless") != null) {
            log.warn("Headless mode not supported in Opera");
            log.warn("Ignoring headless property");
        }
        this.driver = WebDriverManager.operadriver().create();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

}