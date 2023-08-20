package com.palmadelvalle.pagesObject;

import com.palmadelvalle.customExpectedCondition.CustomExpectedCondition;
import com.palmadelvalle.utils.TranslationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalPO extends BasePO {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public ModalPO(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public static final String modalLocatorXpath = "//div[contains(@class, 'modal')]";
    public static final String modalTitleLocatorXpath = "//h3[contains(text(),'%s')]";

    public static final String subBenefitsSectionLocatorXpath = "//div[contains(text(),'Hide sub-benefit')]//following-sibling::div/div";

    @FindBy(xpath = modalLocatorXpath)
    public WebElement modal;

    public static String getTitleXpathByLocale(String title, String locale) {
        return String.format(modalTitleLocatorXpath, TranslationUtils.getLabelByLang(title, locale));
    }

    public boolean isSectionVisible() {
        //return !ExpectedConditions.invisibilityOfElementLocated(By.xpath(subBenefitsSectionLocatorXpath)).apply(driver);
        return CustomExpectedCondition.isPresentElementLocated(By.xpath(subBenefitsSectionLocatorXpath)).apply(driver);
    }

}
