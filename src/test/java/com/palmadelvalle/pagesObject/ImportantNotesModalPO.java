package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportantNotesModalPO extends BasePO {

    private final WebDriver driver;
    public ImportantNotesModalPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static final String modalLocatorXpath = "//div[contains(@class, 'modal')]";
    public static final String importantNotesTitleLocatorXpath = "//h3[contains(text(),'%s')]";

    @FindBy(xpath = modalLocatorXpath)
    public WebElement modal;

    public static String getTitleXpathByLocale(String title, String locale) {
        return String.format(importantNotesTitleLocatorXpath, TranslationUtils.getLabelByLang(title, locale));
    }

}
