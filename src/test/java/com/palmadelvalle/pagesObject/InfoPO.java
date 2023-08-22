package com.palmadelvalle.pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InfoPO extends BasePO {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public InfoPO(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public static final String cardListLocatorXpath = "//div[contains(@locale,'%s')]";

    /**
     * Get the list of cards in the screen.
     * @param locale Language of the page. We need this value because we use the locale attribute
     *               to build the xpath that match with the list of cards.
     * @return List of WebElements that are the cards present in the page.
     */
    public List<WebElement> getCardsList(String locale) {
        By locator = By.xpath(String.format(cardListLocatorXpath, locale));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}
