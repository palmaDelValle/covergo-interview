package com.palmadelvalle.cucumber.stepsDefinition;

import com.palmadelvalle.cucumber.webDriverConfig.BrowserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Steps {

    private final BrowserManager browserManager;
    private final WebDriver driver;
    public Steps(BrowserManager browserManager) {
        this.browserManager = browserManager;
        this.driver = browserManager.getDriver();
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String url) {
        driver.get(url);
    }
    @When("user clicks on link {string}")
    public void user_clicks_on_link(String link) {
        String xpath = String.format("//div[contains(@class,'grid')]//a[contains(@href,'%s')]", link);
        driver.findElement(By.xpath(xpath)).click();
    }
    @Then("user is directed to the {string} page")
    public void user_is_directed_to_the_page(String page) {
        Assertions.assertEquals(page, driver.findElement(By.tagName("h1")).getText());
    }
}
