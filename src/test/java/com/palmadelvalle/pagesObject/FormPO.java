package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FormPO extends BasePO {

    private final WebDriver driver;
    public FormPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//option[@value='1']//ancestor-or-self::select")
    public WebElement ageSelect;

    String genderLocationXpath = "//option[text()='%s']//ancestor-or-self::select";
    //public WebElement genderSelector;

    @FindBy(xpath = "//button[contains(text(), 'Show Results')]")
    public WebElement showResultsButton;

    public void selectAge(Integer age) {
        new Select(ageSelect).selectByVisibleText(age.toString());
    }

    public void selectGender(String gender, String locale) {
        WebElement genderWebElement = driver.findElement(By.xpath(String.format(genderLocationXpath, TranslationUtils.getLabelByLang(gender, locale))));
        new Select(genderWebElement).selectByVisibleText(TranslationUtils.getLabelByLang(gender, locale));
    }

}
