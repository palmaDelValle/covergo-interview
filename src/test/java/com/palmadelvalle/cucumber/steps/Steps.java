package com.palmadelvalle.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Steps {

    public WebDriver driver = WebDriverManager.getInstance().getWebDriver();

    @Given("user navigates to {string} page")
    public void userNavigatesToPage(String url) {
        driver.get(url);
    }

    @When("user clicks on link {string}")
    public void userClicksOnLink(String link) {
        driver.findElement(By.partialLinkText(link)).click();
    }

    @Then("user is directed to the {string} page")
    public void userIsDirectedToThePage(String page) {
        Assertions.assertEquals(page, driver.findElement(By.tagName("h1")).getText());
    }
}
