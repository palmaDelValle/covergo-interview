package com.palmadelvalle.pagesObject;

import com.palmadelvalle.utils.TranslationUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class PaymentPO extends BasePO {

    private final WebDriver driver;
    private final WebDriverWait wait;
    //private final String BASE_XPATH = "//*[contains(text(),'%s')]//parent::div";
    private final String BASE_XPATH_SELECT = "//*[contains(text(),'%s')]/ancestor::div[1]//select";
    private final String BASE_XPATH_INPUT = "//*[contains(text(),'%s')]//ancestor::div[1]//input";
    private final String BASE_XPATH_DIV = "//*[contains(text(),'%s')]";
    private final String BASE_XPATH_SWITCH = "//*[contains(text(),'%s')]/preceding-sibling::div";
    private final String BASE_XPATH_BUTTON = "//*[contains(text(),'%s')]/ancestor::div[1]//button";
    private final String BASE_XPATH_ERROR = "//ancestor-or-self::div[contains(@class,'error')]//div[contains(@class,'text-cError')]";

    public PaymentPO(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    private WebElement getFieldByLabelAndType(String label, String type, String locale) {
        String xpath = "";
        switch (type) {
            case "div":
                xpath = BASE_XPATH_DIV;
                break;
            case "switch":
                xpath = BASE_XPATH_SWITCH;
                break;
            case "button":
                xpath = BASE_XPATH_BUTTON;
                break;
            case "select":
                xpath = BASE_XPATH_SELECT;
                break;
            case "input":
                xpath = BASE_XPATH_INPUT;
                break;
            default:
                log.warn("Not found Xpath defined for element type {}.", type);
                break;
        }
        xpath = String.format(xpath, TranslationUtils.getLabelByLang(label, locale));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    public void fulfillField(String label, String type, String value, String locale) {
        WebElement element = getFieldByLabelAndType(label, type, locale);
        switch (type) {
            case "div":
            case "switch":
            case "button":
                element.click();
                break;
            case "select":
                new Select(element).selectByValue(value);
                break;
            case "input":
                element.sendKeys(value);
                break;
            default:
                log.warn("Element type {} not found", type);
        }
    }

    public WebElement getErrorMessageForField(String label, String type, String locale) {
        WebElement element = getFieldByLabelAndType(label, type, locale);
        return element.findElement(By.xpath(BASE_XPATH_ERROR));
        /*switch (type) {
            case "div":
            case "switch":
            case "button":
                return element.findElement(By.xpath(BASE_XPATH_ERROR));
                break;
            case "select":
                return Select(element).selectByValue(value);
                break;
            case "input":
                element.sendKeys(value);
                break;
            default:
                log.warn("Element type {} not found", type);
        }*/
    }
}
