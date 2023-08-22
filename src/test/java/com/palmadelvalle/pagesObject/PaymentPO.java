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

    private final WebDriverWait wait;
    private final String BASE_XPATH_SELECT = "//*[contains(text(),'%s')]/ancestor::div[1]//select";
    private final String BASE_XPATH_INPUT = "//*[contains(text(),'%s')]//ancestor::div[1]//input";
    private final String BASE_XPATH_DIV = "//*[contains(text(),'%s')]";
    private final String BASE_XPATH_SWITCH = "//*[contains(text(),'%s')]/preceding-sibling::div";
    private final String BASE_XPATH_BUTTON = "//*[contains(text(),'%s')]/ancestor::div[1]//button";
    private final String BASE_XPATH_ERROR = "//ancestor-or-self::div[contains(@class,'error')]//div[contains(@class,'text-cError')]";

    public PaymentPO(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    /**
     * This method builds a dynamic Xpath and then find the field on the screen.
     * @param label Is the label of the WebElement in the screen, we use the key declare in the label_mappings.json file
     *              to calculate the label value in the current application lang.
     * @param type Is the type of the field which we are going to perform the action.
     *             The value of type should be one of the following: div, switch, button, select, input.
     * @param locale The current application lang.
     * @return WebDriverElement found.
     */
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * This method perform an action to a WebElement. I have defined the action to the component by the page behaviour.
     * Eg: The DIV element receive the action clicks because some "buttons" in the application are div.
     * Eg: The switch component is also a DIV but without text content, so I decided to declare the type switch.
     * @param label Is the label of the WebElement in the screen, we use the key declare in the label_mappings.json file
     *              to calculate the label value in the current application lang.
     * @param type Is the type of the field which we are going to perform the action.
     *             The value of type should be one of the following: div, switch, button, select, input.
     * @param value The value that we want to set on the field. This is only applicable to types: select and input.
     * @param locale The current application lang.
     */
    public void fulfillField(String label, String type, String value, String locale) {
        WebElement element = getFieldByLabelAndType(label, type, locale);
        switch (type.toLowerCase()) {
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

    /**
     * This method get the error message related to the field informed.
     * @param label Is the label of the WebElement in the screen, we use the key declare in the label_mappings.json file
     *              to calculate the label value in the current application lang.
     * @param type  Is the type of the field which we are going to perform the action.
     *              The value of type should be one of the following: div, switch, button, select, input.
     * @param locale The current application lang.
     * @return The WebDriverElement of the message related to the field informed.
     */
    public WebElement getErrorMessageForField(String label, String type, String locale) {
        WebElement element = getFieldByLabelAndType(label, type, locale);
        return element.findElement(By.xpath(BASE_XPATH_ERROR));
    }
}
