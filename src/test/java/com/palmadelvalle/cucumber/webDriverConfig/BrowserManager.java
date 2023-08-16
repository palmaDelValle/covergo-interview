package com.palmadelvalle.cucumber.webDriverConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@Getter
public class BrowserManager {
    private final WebDriver driver;
    public BrowserManager() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
