package com.palmadelvalle.customExpectedCondition;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

@Slf4j
public class CustomExpectedCondition {

    /**
     * Custom Expected condition to return a boolean that identify if an element is displayed in the screen by his locator.
     * @param locator Locator of the element that we want to know if it is displayed.
     * @return Boolean indicating that the element is displayed in the screen (true) or not (false).
     */
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
