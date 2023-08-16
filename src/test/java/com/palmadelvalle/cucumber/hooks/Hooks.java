package com.palmadelvalle.cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Hooks {

    @After
    public void tearDown() {
        log.info("Closing open browsers");
        WebDriverManager.getInstance().getWebDriver().close();
    }
}
