package com.palmadelvalle.pagesObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InfoPO extends BasePO {
    public InfoPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@locale,'en')]")
    public List<WebElement> cardsList;

    public String cardListLocatorXpath = "//div[contains(@locale,'en')]";
}
