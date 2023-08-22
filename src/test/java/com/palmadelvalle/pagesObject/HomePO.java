package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePO extends BasePO {
    private final String genderLocationXpath = "//option[text()='%s']//ancestor-or-self::select";
    private final WebDriver driver;

    public HomePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//option[@value='1']//ancestor-or-self::select")
    public WebElement ageSelect;

    /**
     * Set the value informed in the Age selector
     * @param age Value of the age that we want to select.
     */
    public void selectAge(Integer age) {
        new Select(ageSelect).selectByVisibleText(age.toString());
    }

    /**
     * Set the value of the gender informed in the gender select.
     * @param gender Key of the gender in the file label_mappings.json.
     * @param locale Current lang of the application.
     */
    public void selectGender(String gender, String locale) {
        WebElement genderWebElement = driver.findElement(By.xpath(String.format(genderLocationXpath, TranslationUtils.getLabelByLang(gender, locale))));
        new Select(genderWebElement).selectByVisibleText(TranslationUtils.getLabelByLang(gender, locale));
    }

}
