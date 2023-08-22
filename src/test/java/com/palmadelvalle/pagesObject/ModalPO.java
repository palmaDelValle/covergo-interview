package com.palmadelvalle.pagesObject;

import com.palmadelvalle.customExpectedCondition.CustomExpectedCondition;
import com.palmadelvalle.utils.TranslationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalPO extends BasePO {

    private final WebDriver driver;
    public ModalPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static final String modalLocatorXpath = "//div[contains(@class, 'modal')]";
    public static final String modalTitleLocatorXpath = "//h3[contains(text(),'%s')]";
    public static final String subBenefitsSectionLocatorXpath = "//div[contains(text(),'Hide sub-benefit')]//following-sibling::div/div";

    @FindBy(xpath = modalLocatorXpath)
    public WebElement modal;

    /**
     * This method build a string with the Xpath to get the title of the modal.
     * @param title Key of the title in label_mappings.json file.
     * @param locale Current lang of the application.
     * @return String with the dynamic Xpath to find the element.
     */
    public static String getTitleXpathByLocale(String title, String locale) {
        return String.format(modalTitleLocatorXpath, TranslationUtils.getLabelByLang(title, locale));
    }

    /**
     * This method evaluates if the section sub-benefit is present in the screen.
     * @return boolean indicating if the section is present in the screen (true) or not (false).
     */
    public boolean isSubBenefitSectionVisible() {
        By locator = By.xpath(subBenefitsSectionLocatorXpath);
        return CustomExpectedCondition.isPresentElementLocated(locator).apply(driver);
    }

}
