package com.palmadelvalle.cucumber.hooks;

import com.palmadelvalle.webDriverConfig.BrowserManager;
import com.palmadelvalle.webDriverConfig.InvalidBrowserException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

@Slf4j
public class Hooks {

    private final WebDriver driver;
    private BrowserManager browserManager;


    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.driver = browserManager.getDriver();
    }



    @After
    public void tearDown() {
        log.info("Closing open browsers");
        this.driver.close();
        this.driver.quit();
    }
}
