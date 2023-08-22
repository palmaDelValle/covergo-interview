package com.palmadelvalle.cucumber.hooks;

import com.palmadelvalle.browserConfig.BrowserManager;
import com.palmadelvalle.browserConfig.BrowserType;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.palmadelvalle.utils.ScreenshotUtil.attachScreenshotToScenario;

@Slf4j
public class Hooks {
    private final WebDriver driver;
    private final BrowserManager browserManager;


    public Hooks(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.driver = browserManager.getDriver();
    }

    /**
     * This Cucumber hook will attach to the failed scenario a screenshot.
     * @param scenario Current Cucumber scenario.
     */
    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Taking screenshot.");
            attachScreenshotToScenario(driver, scenario);
            log.info("Screenshot attached to the current scenario result.");
        }
    }

    /**
     * This Cucumber hook will close the driver used for the scenario when the scenario ends.
     */
    @After(order = 0)
    public void tearDown() {
        log.info("Closing open browsers");
        if (browserManager.getBrowserType() == BrowserType.CHROME) this.driver.close();
        if (browserManager.getBrowserType() == BrowserType.CHROMIUM) this.driver.close();
        if (browserManager.getBrowserType() == BrowserType.EDGE) this.driver.close();
        this.driver.quit();
    }
}
