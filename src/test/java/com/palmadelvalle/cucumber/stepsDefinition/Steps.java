package com.palmadelvalle.cucumber.stepsDefinition;

import com.palmadelvalle.pagesObject.CardPO;
import com.palmadelvalle.pagesObject.InfoPO;
import com.palmadelvalle.webDriverConfig.BrowserManager;
import com.palmadelvalle.pagesObject.FormPO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
public class Steps {

    private BrowserManager browserManager;
    private final FormPO formPO;
    private final InfoPO infoPO;

    private final CardPO cardPO;
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Steps(BrowserManager browserManager) {
        this.driver = browserManager.getDriver();
        this.wait = browserManager.getWait();
        this.formPO = new FormPO(driver);
        this.infoPO = new InfoPO(driver);
        this.cardPO = new CardPO(driver);
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String url) {
        driver.get(url);
    }

    @When("user selects age {int}")
    public void userSelectsAgeAge(int age) {
        formPO.selectAge(age);
    }

    @And("user selects gender {string}")
    public void userSelectsGenderGender(String gender) {
        formPO.selectGender(gender);
    }

    @And("user clicks on {string} button")
    public void userClicksOnButton(String buttonLabel) {
        formPO.getButtonByLabel(buttonLabel).click();
    }

    @Then("user is directed to the {string} page")
    public void userIsDirectedToThePage(String url) {
        wait.until(ExpectedConditions.urlContains(url));
        Assertions.assertTrue(driver.getCurrentUrl().contains(url));
    }

    @Then("user will see cards element")
    public void userWillSeeCardsElement() {
        wait.until(ExpectedConditions.visibilityOfAllElements(infoPO.cardsList));
        Assertions.assertEquals(8, infoPO.cardsList.size());
    }

    @And("the card will contain the product information")
    public void theCardWillContainTheProductInformation(DataTable fields) {
        List<String> rows = fields.asList(String.class);
        for (String col : rows) {
            log.info("Col: {}", col);
        }
        WebElement firstCard = infoPO.cardsList.get(0);

    }
}
