package com.palmadelvalle.pagesObject;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class CardPO extends BasePO {

    public CardPO(WebDriver driver) {
        super(driver);
    }
    
    private final String SHARED_XPATH = "//div[contains(@class,'items-center')]";

    /**
     * Get the card element title.
     * @return Xpath locator for the element
     */
    private By cardTitle(){
        return By.xpath(String.format("%s[1][1]//h4", SHARED_XPATH));
    }

    /**
     * Get the card element key.
     * @return Xpath locator for the element
     */
    private By cardKey(){
        return By.xpath(String.format("%s[1][1]//p", SHARED_XPATH));
    }

    /**
     * Get the card element amount.
     * @return Xpath locator for the element
     */
    private By cardAmount(){
        return By.xpath(String.format("%s[2][1]//span[1]",SHARED_XPATH));
    }

    /**
     * Get the card element Price switcher.
     * @return Xpath locator for the element
     */
    private By cardPriceSwitcher(){
        return By.xpath(String.format("%s[2][1]//div[@tabindex=0]", SHARED_XPATH));
    }

    /**
     * Get the card element Buy now button.
     * @return Xpath locator for the element
     */
    private By cardBuyNowBtn(){
        return By.xpath(String.format("%s[2][1]//button", SHARED_XPATH));
    }

    /**
     * Get the card element Benefits section.
     * @return Xpath locator for the element
     */
    private By cardBenefitsSection(){
        return By.xpath("//div[contains(@class,'py-2')][1]");
    }

    /**
     * Get the card element Benefits section link see all benefits.
     * @return Xpath locator for the element
     */
    public By cardSeeAllBenefitsLink(String locale){
        return getLinkLocatorByLocale("see_all_benefits", locale);
    }

    /**
     * Get the card element see all documents link.
     * @return Xpath locator for the element
     */
    public By cardSeeAllDocumentsLink(String locale){
        return getLinkLocatorByLocale("see_all_documents_and_notes", locale);
    }

    /**
     * Get the card element show important notes link.
     * @return Xpath locator for the element
     */
    public By cardShowImportantNotesLink(String locale) {
        return getLinkLocatorByLocale("show_important_notes", locale);
    }

    /**
     * This method builds the locator to find the element that we are looking for.
     * @param field Field to look for.
     * @param locale Current language of the application.
     * @return Xpath locator to the element.
     */
    public By getCardFieldLocator(String field, String locale) {
        switch (field.toLowerCase()) {
            case "title":
                return (cardTitle());
            case "key":
                return (cardKey());
            case "amount":
                return (cardAmount());
            case "price switch":
                return (cardPriceSwitcher());
            case "buy button":
                return (cardBuyNowBtn());
            case "annual limit":
                return (cardBenefitsSection());
            case "benefits link":
                return (cardSeeAllBenefitsLink(locale));
            case "documents link":
                return (cardSeeAllDocumentsLink(locale));
            case "important notes":
                return (cardShowImportantNotesLink(locale));
            case "see sub-benefit":
                return getLinkLocatorByLocale("see_sub_bebefits", locale);
            case "hide sub-benefit":
                return getLinkLocatorByLocale("hide_sub_bebefits", locale);
            default:
                log.error("Field {} not found.", field);
                throw new NullPointerException(String.format("Field %s not found", field));
        }
    }
}
