package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BasePO {
    private final String XPATH_CONTAINS_TEXT = "//%s[contains(text(),'%s')]";
    private final String ANY = "*";
    private final String DIV = "div";

    public BasePO(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Build a dynamic Xpath to find a WebElement with tagname DIV and the indicated text.
     * @param key Text that has the WebElement that we are looking for.
     * @return Xpath to find the element.
     */
    private By buildXpath(String key, String locale, String tagName) {
        return By.xpath(String.format(XPATH_CONTAINS_TEXT, tagName, (TranslationUtils.getLabelByLang(key, locale))));
    }

    /**
     * Build a dynamic Xpath to find a WebElement with tagname DIV and the indicated text
     * @param key The key of the label informed in the label_mappings.json file.
     * @param locale The current application lang.
     * @return Xpath locator that we can use to find the element.
     */
    public By getLinkLocatorByLocale(String key, String locale) {
        return buildXpath(key, locale, DIV);
    }

    /**
     * Build a dynamic Xpath to find a WebElement with any tagname and the indicated text
     * @param key The key of the label informed in the label_mappings.json file.
     * @param locale The current application lang
     * @return Xpath locator that we can use to find the element.
     */
    public By getElementXpathLocatorContainingTextByLocale(String key, String locale) {
        return buildXpath(key, locale, ANY);
    }
}
