package com.palmadelvalle.browserConfig.browsers;

import com.palmadelvalle.browserConfig.Browser;
import com.palmadelvalle.browserConfig.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Edge implements Browser {
    private BrowserType browserType = BrowserType.EDGE;
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
        EdgeOptions edgeOptions = new EdgeOptions();
        if (System.getProperty("headless") != null) {
            edgeOptions.addArguments("--headless");
        }
        this.driver = WebDriverManager.edgedriver().capabilities(edgeOptions).create();
    }

}