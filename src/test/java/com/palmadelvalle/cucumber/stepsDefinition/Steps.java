package com.palmadelvalle.cucumber.stepsDefinition;

import com.palmadelvalle.pagesObject.*;
import com.palmadelvalle.utils.Constants;
import com.palmadelvalle.browserConfig.BrowserManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

import static com.palmadelvalle.pagesObject.HeaderPO.BTN_EN_LOCATOR_XPATH;
import static com.palmadelvalle.pagesObject.HeaderPO.BTN_HK_ZK_LOCATOR_XPATH;
import static com.palmadelvalle.pagesObject.ModalPO.*;

@Slf4j
public class Steps {

    private BrowserManager browserManager;
    private final HomePO homePO;
    private final InfoPO infoPO;
    private final HeaderPO headerPO;
    private final CardPO cardPO;
    private final BasePO basePO;
    private final ModalPO modalPO;
    private final PaymentPO paymentPO;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private String locale = Constants.EN;

    public Steps(BrowserManager browserManager) {
        this.driver = browserManager.getDriver();
        this.wait = browserManager.getWait();
        this.homePO = new HomePO(driver);
        this.infoPO = new InfoPO(driver);
        this.cardPO = new CardPO(driver);
        this.headerPO = new HeaderPO(driver, wait);
        this.basePO = new BasePO(driver);
        this.modalPO = new ModalPO(driver);
        this.paymentPO = new PaymentPO(driver, wait);
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String url) {
        driver.get(url);
    }

    @When("user selects age {int}")
    public void userSelectsAgeAge(int age) {
        homePO.selectAge(age);
    }

    @And("user selects gender {string}")
    public void userSelectsGenderGender(String gender) {
        homePO.selectGender(gender, locale);
    }

    @And("user clicks on {string} button")
    public void userClicksOnButton(String buttonLabel) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(basePO.getElementXpathLocatorContainingTextByLocale(buttonLabel, locale))).click();
    }

    @Then("user is directed to the {string} page")
    public void userIsDirectedToThePage(String url) {
        wait.until(ExpectedConditions.urlContains(url));
        Assertions.assertTrue(driver.getCurrentUrl().contains(url));
    }

    @Then("user see cards element")
    public void userWillSeeCardsElement() {
        List<WebElement> cardsList = infoPO.getCardsList(locale);
        wait.until(ExpectedConditions.visibilityOfAllElements(cardsList));
        Assertions.assertEquals(8, cardsList.size());
    }
    

    @And("the card will contain the product information")
    public void theCardWillContainTheProductInformation(DataTable fields) {
        WebElement firstCard = infoPO.getCardsList(locale).get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstCard));

        for (String field : fields.asList(String.class)) {
            log.info("Field to check: {}", field);
            wait.until(ExpectedConditions.presenceOfElementLocated(cardPO.getCardFieldLocator(field, locale)));
            //wait.until(ExpectedConditions.presenceOfElementLocated(cardPO.getLinkLocatorByLocale(field, locale)));

            Assertions.assertTrue(firstCard.findElement(cardPO.getCardFieldLocator(field, locale)).isDisplayed());
            //Assertions.assertTrue(firstCard.findElement(cardPO.getLinkLocatorByLocale(field, locale)).isDisplayed());
        }
    }
    @And("user changes lang")
    public void userChangesLang() {
        // Assume that when the url not contain lang, the page is in English.
        if(driver.getCurrentUrl().contains(Constants.ZH_HK)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BTN_EN_LOCATOR_XPATH)));
            headerPO.btnEnlang.click();
            wait.until(ExpectedConditions.urlContains(Constants.EN));
            Assertions.assertTrue(driver.getCurrentUrl().contains(Constants.EN));
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BTN_HK_ZK_LOCATOR_XPATH)));
            headerPO.btnHKZKlang.click();
            wait.until(ExpectedConditions.urlContains(Constants.ZH_HK));
            Assertions.assertTrue(driver.getCurrentUrl().contains(Constants.ZH_HK));
            locale = Constants.ZH_HK;
        }
    }

    @When("user clicks on {string} link")
    public void userClicksOnLink(String link) {
        WebElement firstCard = infoPO.getCardsList(locale).get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstCard));
        log.info("Field to check: {}", link);
        //wait.until(ExpectedConditions.presenceOfElementLocated(cardPO.getCardFieldLocator(link, locale)));
        //Assertions.assertTrue(firstCard.findElement(cardPO.getCardFieldLocator(link, locale)).isDisplayed());
        //firstCard.findElement(cardPO.getCardFieldLocator(link, locale)).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(basePO.getLinkLocatorByLocale(link, locale)));
        Assertions.assertTrue(firstCard.findElement(basePO.getLinkLocatorByLocale(link, locale)).isDisplayed());
        firstCard.findElement(basePO.getLinkLocatorByLocale(link, locale)).click();
    }

    @Then("a modal with title {string} should be present")
    public void aModalWithTitleShouldBePresent(String title) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(modalLocatorXpath)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(modalLocatorXpath + getTitleXpathByLocale(title, locale))));
        Assertions.assertTrue(modalPO.modal.findElement(By.xpath(getTitleXpathByLocale(title, locale))).isDisplayed());
    }

    @And("the modal will be not present")
    public void theModalWillBeNotPresent() {
        ExpectedCondition<Boolean> result = ExpectedConditions.invisibilityOfElementLocated(By.xpath(modalLocatorXpath));
        Assertions.assertTrue(result.apply(driver));
    }

    @When("user changes to {string} lang")
    public void userChangesLangToLang(String lang) {
        headerPO.changeLangTo(lang);
    }

    @Then("the page should be in {string} language")
    public void thePageShouldBeInLang(String lang) {
        log.info("Current url: {} -> Lang: {}", driver.getCurrentUrl(), lang);
        wait.until(ExpectedConditions.urlContains(lang));
        Assertions.assertTrue(driver.getCurrentUrl().contains(lang));
    }

    @Then("the section {string} should {string}")
    public void theSectionLinkShouldCondition(String section, String condition) {
        if (condition.equalsIgnoreCase("be visible")) {
            Assertions.assertTrue(modalPO.isSubBenefitSectionVisible());
        } else if (condition.equalsIgnoreCase("not be visible")) {
            Assertions.assertFalse(modalPO.isSubBenefitSectionVisible());
        }
    }

    @Then("a form with title {string} should be visible")
    public void aFormWithTitleShouldBeVisible(String title) {
        Assertions.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(basePO.getElementXpathLocatorContainingTextByLocale(title, locale))).isDisplayed());
    }

    @When("user fulfill mandatory fields")
    public void userFulfillMandatoryFields(DataTable dataTable) {
        for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
            log.info("Field: {}, Type: {}, Value: {}", columns.get("field"), columns.get("type"), columns.get("value"));
            // I have to wait until the element is visible in the scree
            // then I have to fulfill the field with the value
            paymentPO.fulfillField(columns.get("field"), columns.get("type"), columns.get("value"), locale);
        }
    }

    @Then("a success modal should be visible")
    public void aSuccessModalShouldBeVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(modalLocatorXpath)));
        Assertions.assertTrue(driver.findElement(By.xpath(modalLocatorXpath)).isDisplayed());
    }

    @Then("field should has field is required message")
    public void fieldShouldHasFieldIsRequiredMessage(DataTable dataTable) {
        for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
            log.info("Field: {}, Type: {}", columns.get("field"), columns.get("type"));
            WebElement errorMessage = paymentPO.getErrorMessageForField(columns.get("field"), columns.get("type"), locale);
            Assertions.assertTrue(errorMessage.isDisplayed());
        }
    }
}
