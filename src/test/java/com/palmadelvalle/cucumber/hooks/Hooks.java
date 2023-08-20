package com.palmadelvalle.cucumber.hooks;

import com.palmadelvalle.browserConfig.BrowserManager;
import com.palmadelvalle.browserConfig.BrowserType;
import io.cucumber.java.After;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Hooks {

    private final WebDriver driver;
    private final BrowserManager browserManager;


    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.driver = browserManager.getDriver();
    }

    @After
    public void tearDown() {
        log.info("Closing open browsers");
        if (browserManager.getBrowserType() != BrowserType.SAFARI) this.driver.close();
        this.driver.quit();
    }
}
