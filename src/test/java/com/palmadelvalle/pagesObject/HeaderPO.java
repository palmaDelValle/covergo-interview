package com.palmadelvalle.pagesObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPO extends BasePO {
    public HeaderPO(WebDriver driver) {
        super(driver);
    }

    public static final String BTN_HK_ZK_LOCATOR_XPATH = "//header//button[1]";
    public static final String BTN_EN_LOCATOR_XPATH = "//header//button[2]";

    @FindBy(xpath = BTN_HK_ZK_LOCATOR_XPATH)
    public WebElement btnHKZKlang;

    @FindBy(xpath = BTN_EN_LOCATOR_XPATH)
    public WebElement btnEnlang;


}
