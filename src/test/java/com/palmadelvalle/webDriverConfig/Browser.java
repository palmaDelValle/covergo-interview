package com.palmadelvalle.webDriverConfig;

import org.openqa.selenium.WebDriver;

public interface Browser {
    public BrowserType getBrowser();
    public WebDriver getDriver();
    public void setDriver(WebDriver driver);

    public void setupBrowser();
    public default void teardownBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
