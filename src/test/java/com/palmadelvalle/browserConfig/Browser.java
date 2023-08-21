package com.palmadelvalle.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface Browser {
    /**
     * Get the browser type of the browser.
     * @return The browser type of the browser.
     */
    public BrowserType getBrowser();

    /**
     * Get the browser driver.
     * @return WebDriver of the browser instantiated.
     */
    public WebDriver getDriver();
    /**
     * Get the browser WebDriverWait.
     * @return WebDriverWait of the browser instantiated.
     */
    public WebDriverWait getWebdriverWait();

    /**
     * Stored the instantiated browser
     * @param driver WebDriver of the browser instantiated.
     */
    public void setDriver(WebDriver driver);

    /**
     * This method configure the browser.
     */
    public void setupBrowser();

    /**
     * Close the browser.
     */
    public default void teardownBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
