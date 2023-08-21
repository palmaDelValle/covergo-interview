package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class BasePO {
    private WebDriver driver;

    public BasePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String divContainsTextXpath(String link) {
        return String.format("//div[contains(text(),'%s')]",link);
    }

    public By getLinkLocatorByLocale(String key, String locale) {
        return By.xpath(divContainsTextXpath(TranslationUtils.getLabelByLang(key, locale)));
    }

    public By getElementXpathLocatorContainingTextByLocale(String key, String locale) {
        return By.xpath(String.format("//*[contains(text(),'%s')]",TranslationUtils.getLabelByLang(key, locale)));
    }
}
