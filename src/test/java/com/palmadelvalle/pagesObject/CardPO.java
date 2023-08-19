package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.Constants;
import com.palmadelvalle.utils.TranslationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class CardPO extends BasePO {

    private final WebDriver driver;

    public CardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    
    private final String SHARED_XPATH = "//div[contains(@class,'items-center')]";

    private String divContainsTextXpath(String link) {
        return String.format("//div[contains(text(),'%s')]",link);
    }

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

    public By cardSeeAllBenefitsLink(){
        String link = null;
        String lang = Constants.EN;
        if (driver.getCurrentUrl().contains(Constants.ZH_HK)) {
            log.info("Language defined is: {}", Constants.ZH_HK);
            lang = Constants.ZH_HK;
        }
        link = TranslationUtils.getLabelByLang("see_all_benefits", lang);
        return By.xpath(divContainsTextXpath(link));
    }

    public By cardSeeAllDocumentsLink(){
        return By.xpath(divContainsTextXpath("See All Documents and Notes"));
    }

    public By cardShowImportantsNotesLink() {
        return By.xpath(divContainsTextXpath("Show Important Notes"));
    }

    public By getCardFieldLocator(String field) {
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
                return (cardSeeAllBenefitsLink());
            case "Documents link":
                return (cardSeeAllDocumentsLink());
            default:
                log.error("Field {} not found.", field);
                throw new NullPointerException(String.format("Field %s not found", field));
        }
    }
}
