package com.palmadelvalle.cucumber.hooks;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BrowserHook {
    private void setupBrowser(String browserName) {
      log.debug("Setting up browsers");
      log.debug("Browser name {}", browserName);
      WebDriverManager.chromedriver().create();
    }

    @Before(value="chrome")
    public void setUp(){
        setupBrowser("chrome");
    }
}
