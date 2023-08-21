package com.palmadelvalle.pagesObject;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class CardPO extends BasePO {

    private final WebDriver driver;

    public CardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    
    private final String SHARED_XPATH = "//div[contains(@class,'items-center')]";

    public By cardTitle(){
        return By.xpath(String.format("%s[1][1]//h4", SHARED_XPATH));
    }

    public By cardKey(){
        return By.xpath(String.format("%s[1][1]//p", SHARED_XPATH));
    }

    public By cardPaymentSection(){
        return By.xpath(String.format("%s[2][1]", SHARED_XPATH));
    }

    public By cardAmount(){
        return By.xpath(String.format("%s[2][1]//span[1]",SHARED_XPATH));
    }

    public By cardPriceSwitcher(){
        return By.xpath(String.format("%s[2][1]//div[@tabindex=0]", SHARED_XPATH));
    }

    public By cardBuyNowBtn(){
        return By.xpath(String.format("%s[2][1]//button", SHARED_XPATH));
    }

    public By cardBenefitsSection(){
        return By.xpath("//div[contains(@class,'py-2')][1]");
    }

    public By cardSeeAllBenefitsLink(String locale){
        //return By.xpath(divContainsTextXpath(TranslationUtils.getLabelByLang("see_all_benefits", locale)));
        return getLinkLocatorByLocale("see_all_benefits", locale);
    }

    public By cardSeeAllDocumentsLink(String locale){
        //return By.xpath(divContainsTextXpath(TranslationUtils.getLabelByLang("see_all_documents_and_notes", locale)));
        return getLinkLocatorByLocale("see_all_documents_and_notes", locale);
    }

    public By cardShowImportantsNotesLink(String locale) {
        return getLinkLocatorByLocale("show_important_notes", locale);
    }

    public By getCardFieldLocator(String field, String locale) {
        switch (field) {
            case "Title":
                return (cardTitle());
            case "Key":
                return (cardKey());
            case "Amount":
                return (cardAmount());
            case "Price switch":
                return (cardPriceSwitcher());
            case "Buy button":
                return (cardBuyNowBtn());
            case "Annual limit":
                return (cardBenefitsSection());
            case "Benefits link":
                return (cardSeeAllBenefitsLink(locale));
            case "Documents link":
                return (cardSeeAllDocumentsLink(locale));
            case "Important notes":
                return (cardShowImportantsNotesLink(locale));
            case "See sub-benefit":
                return getLinkLocatorByLocale("see_sub_bebefits", locale);
            case "Hide sub-benefit":
                return getLinkLocatorByLocale("hide_sub_bebefits", locale);
            default:
                log.error("Field {} not found.", field);
                throw new NullPointerException(String.format("Field %s not found", field));
        }
    }
}
