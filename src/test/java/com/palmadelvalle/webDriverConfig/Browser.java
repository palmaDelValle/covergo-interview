package com.palmadelvalle.webDriverConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface Browser {
    public BrowserType getBrowser();
    public WebDriver getDriver();
    public WebDriverWait getWebdriverWait();
    public void setDriver(WebDriver driver);

    public void setWait(WebDriver driver);

    public void setupBrowser();
    public default void teardownBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
