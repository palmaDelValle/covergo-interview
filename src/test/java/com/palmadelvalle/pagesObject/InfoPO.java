package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InfoPO extends BasePO {
    public InfoPO(WebDriver driver) {
        super(driver);
    }

    public static final String cardListLocatorENXpath = "//div[contains(@locale,'en')]";
    public static final String cardListLocatorHKZKXpath = "//div[contains(@locale,'zh-HK')]";

    @FindBy(xpath = cardListLocatorENXpath)
    public List<WebElement> cardsListEn;

    @FindBy(xpath = cardListLocatorHKZKXpath)
    public List<WebElement> cardsListZHHK;




    public List<WebElement> getCardsList(String locale) {
        if(locale.equalsIgnoreCase(Constants.ZH_HK)) {
            return cardsListZHHK;
        } else {
            return cardsListEn;
        }
    }
}
