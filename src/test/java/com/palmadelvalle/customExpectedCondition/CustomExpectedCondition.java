package com.palmadelvalle.customExpectedCondition;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

@Slf4j
public class CustomExpectedCondition {

    public static ExpectedCondition<Boolean> waitForPageReady() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String jsReturn = ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .toString();
                log.info("JS return {}", jsReturn);
                return jsReturn.equals("complete");
            }
        };
    }

    public static ExpectedCondition<Boolean> isPresentElementLocated(final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.findElement(locator).isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }

            public String toString() {
                return "presence of element located by " + locator;
            }
        };
    }

}
