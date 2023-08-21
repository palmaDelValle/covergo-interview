package com.palmadelvalle.cucumber.hooks;

import com.palmadelvalle.browserConfig.BrowserManager;
import com.palmadelvalle.browserConfig.BrowserType;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Hooks {

    private final WebDriver driver;
    private final BrowserManager browserManager;


    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.driver = browserManager.getDriver();
    }

    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
    }

    @After(order = 0)
    public void tearDown() {
        log.info("Closing open browsers");
        if (browserManager.getBrowserType() != BrowserType.SAFARI) this.driver.close();
        this.driver.quit();
    }
}
