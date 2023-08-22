package com.palmadelvalle.cucumber.stepsDefinition;

import com.palmadelvalle.pagesObject.*;
import com.palmadelvalle.utils.Constants;
import com.palmadelvalle.browserConfig.BrowserManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
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
import static com.palmadelvalle.utils.ScreenshotUtil.attachScreenshotToScenario;

@Slf4j
public class Steps {

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
        this.infoPO = new InfoPO(driver, wait);
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
        // Locator of the element
        By locator = basePO.getElementXpathLocatorContainingTextByLocale(buttonLabel, locale);
        // Wait until element is visible. Then click.
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    @Then("user is directed to the {string} page")
    public void userIsDirectedToThePage(String url) {
        wait.until(ExpectedConditions.urlContains(url));
        Assertions.assertTrue(driver.getCurrentUrl().contains(url));
    }

    @Then("user see cards element")
    public void userWillSeeCardsElement() {
        List<WebElement> cardsList = infoPO.getCardsList(locale);
        Assertions.assertEquals(8, cardsList.size());
    }
    

    @And("the card will contain the product information")
    public void theCardWillContainTheProductInformation(DataTable fields) {
        // We are using the first card shown in the plan page.
        // This could be changed to inform the card to be used.
        WebElement firstCard = infoPO.getCardsList(locale).get(0);
        // Wait for the card to be clickable. This behaviour is to about NoSuchElementException.
        wait.until(ExpectedConditions.elementToBeClickable(firstCard));
        // Iterate for every row informed in the fields datatable.
        // Then the test wait until element is in the present and perform the assertion.
        for (String field : fields.asList(String.class)) {
            log.info("Field to check: {}", field);
            By locator = cardPO.getCardFieldLocator(field, locale);
            Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed());
        }
    }
    @And("user changes lang")
    public void userChangesLang() {
        // Note: locale variable indicates to the scenario the current language of the site.
        // We assume that the default language of the site is English and when the user changes the language
        // we change the variable assignment to the current language.
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
        // We are using the first card shown in the plan page.
        // This could be changed to inform the card to be used.
        WebElement firstCard = infoPO.getCardsList(locale).get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstCard));
        log.info("Link to be clicked: {}", link);
        By locator = basePO.getLinkLocatorByLocale(link, locale);
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed());
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
        // Evaluates that the modal is not present.
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
        Assertions.assertTrue(wait.until(ExpectedConditions.urlContains(lang)));
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
        By locator = basePO.getElementXpathLocatorContainingTextByLocale(title, locale);
        Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed());
    }

    @When("user fulfill mandatory fields")
    public void userFulfillMandatoryFields(DataTable dataTable) {
        for (Map<String, String> columns : dataTable.asMaps(String.class, String.class)) {
            log.info("Field: {}, Type: {}, Value: {}", columns.get("field"), columns.get("type"), columns.get("value"));
            // I have to wait until the element is visible in the screen
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
