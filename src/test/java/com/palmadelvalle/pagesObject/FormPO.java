package com.palmadelvalle.pagesObject;

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


    /*public FormPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }*/

    @FindBy(xpath = "//option[@value='1']//ancestor-or-self::select")
    public WebElement ageSelect;

    @FindBy(xpath = "//option[@value='male']//ancestor-or-self::select")
    public WebElement genderSelector;

    @FindBy(xpath = "//button[contains(text(), 'Show Results')]")
    public WebElement showResultsButton;

    public void selectAge(Integer age) {
        new Select(ageSelect).selectByVisibleText(age.toString());
    }

    public void selectGender(String gender) {
        new Select(genderSelector).selectByVisibleText(gender);
    }

    public WebElement getButtonByLabel(String label) {
        String xpath = String.format("//button[contains(text(), '%s')]", label);
        return driver.findElement(By.xpath(xpath));
    }
}
