package com.palmadelvalle.pagesObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPO extends BasePO {
    public HeaderPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//header//button[1]")
    public WebElement btnHKZKlang;

    @FindBy(xpath = "//header//button[1]")
    public WebElement btnEnlang;
}
