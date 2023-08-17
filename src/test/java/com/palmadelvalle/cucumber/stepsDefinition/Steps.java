package com.palmadelvalle.cucumber.stepsDefinition;

import com.palmadelvalle.webDriverConfig.BrowserManager;
import com.palmadelvalle.pagesObject.FormPageObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;


public class Steps {

    private BrowserManager browserManager;
    private final FormPageObject formPageObject;
    private final WebDriver driver;

    public Steps(BrowserManager browserManager) {
        this.driver = browserManager.getDriver();
        this.formPageObject = new FormPageObject(driver);
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String url) {
        driver.get(url);
    }

    @When("user selects age {int}")
    public void userSelectsAgeAge(int age) {
        formPageObject.selectAge(age);
    }

    @And("user selects gender {string}")
    public void userSelectsGenderGender(String gender) {
        formPageObject.selectGender(gender);
    }

    @And("user clicks on {string} button")
    public void userClicksOnButton(String buttonLabel) {
        formPageObject.getButtonByLabel(buttonLabel).click();
    }

    @Then("user is directed to the {string} page")
    public void userIsDirectedToThePage(String url) {
        Assertions.assertTrue(driver.getCurrentUrl().contains(url));
    }
}
