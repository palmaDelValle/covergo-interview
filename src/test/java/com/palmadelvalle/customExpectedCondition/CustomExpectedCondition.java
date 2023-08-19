package com.palmadelvalle.customExpectedCondition;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

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


}
