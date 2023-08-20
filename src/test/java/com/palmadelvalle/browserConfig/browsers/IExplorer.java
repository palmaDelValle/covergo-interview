package com.palmadelvalle.browserConfig.browsers;

import com.palmadelvalle.browserConfig.Browser;
import com.palmadelvalle.browserConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class IExplorer implements Browser {
    private BrowserType browserType = BrowserType.IEXPLORER;
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
            log.warn("Headless mode not supported in Internet explorer");
            log.warn("Ignoring headless property");
        }
        this.driver = WebDriverManager.iedriver().create();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

}