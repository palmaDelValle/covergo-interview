package com.palmadelvalle.pagesObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CardPO extends BasePO {
    public CardPO(WebDriver driver) { super(driver); }

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][1])[1]//h4")
    List<WebElement> title;

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][1])[1]//p")
    WebElement key;

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][2])[1]")
    WebElement paymentSection;
    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][2][1]//span)[1]")
    WebElement amount;

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][2][1]//div[@tabindex=0])")
    WebElement paymentSwitcher;

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'items-center')][2][1]//button)[1]]")
    WebElement buyNowBtn;

    @FindBy (xpath = "(//div[contains(@locale,'en')][1]//div[contains(@class,'py-2')])[1]")
    WebElement benefitsSection;

    @FindBy (xpath = "(//div[contains(text(),'See All Benefits')])[1]")
    WebElement seeAllbenefitsLink;

    @FindBy (xpath = "(//div[contains(text(),'See All Documents and Notes')])[1]")
    WebElement seeAllDocumentsLink;

    @FindBy (xpath = "(//div[contains(text(),'Show Important Notes')])[1]")
    WebElement showImportantNotes;


}
