package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPO extends BasePO {

    private WebDriverWait wait;
    public HeaderPO(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    public static final String BTN_HK_ZK_LOCATOR_XPATH = "//header//button[1]";
    public static final String BTN_EN_LOCATOR_XPATH = "//header//button[2]";

    @FindBy(xpath = BTN_HK_ZK_LOCATOR_XPATH)
    public WebElement btnHKZKlang;

    @FindBy(xpath = BTN_EN_LOCATOR_XPATH)
    public WebElement btnEnlang;

    /**
     * This method perform the action to change the application lang.
     * @param lang Lang that we want to set the application.
     */
    public void changeLangTo(String lang) {
        if(lang.equalsIgnoreCase(Constants.ZH_HK)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BTN_HK_ZK_LOCATOR_XPATH))).click();
            //btnHKZKlang.click();
        } else if (lang.equalsIgnoreCase(Constants.EN)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BTN_EN_LOCATOR_XPATH))).click();
            //btnEnlang.click();
        }
    }
}
