package io.cucumber.glue;

import io.cucumber.core.Manager;
import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Home extends Context {

  public Home(Manager manager) {
    super(manager);
  }

  @Given("an example Home Page step")
  public void exampleHomePageStep() {
    System.out.println("printing shared stash :" + getTestStash().toString());
  }

  @Then("the homepage should display the following links:")
  public void homepage_should_display_links(List<String> expectedLinks) {
    Home homePage = new Home(manager);
    List<String> actualLinks = homePage.getExampleLinksText();
    assertEquals(expectedLinks, actualLinks);
  }

  public List<String> getExampleLinksText() {
    return getDriver().findElements(By.tagName("a")) // Get all anchor <a> elements
            .stream()
            .map(WebElement::getText) // Extract text
            .collect(Collectors.toList()); // Convert to List<String>
  }

  @When("the {string} example is opened")
  public void theBasicAuthExampleIsOpened(String example) {
    WebElement exampleLink = getDriver().findElement(By.linkText(example));
    exampleLink.click();
  }

  @And("valid credentials are supplied {string} and password {string}")
  public void validCredentialsAreSuppliedAdminAndPasswordAdmin(String username, String password) {
    // Handling Basic Auth using URL format (username:password@)
    String authUrl = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
    getDriver().get(authUrl);
  }

  @Then("I should see the message {string}")
  public void iShouldSeeTheMessage(String expectedMessage) {
    WebElement successMessage = getDriver().findElement(By.tagName("p"));
    String actualMessage = successMessage.getText();
    assertEquals(expectedMessage, actualMessage);
    getDriver().quit();
  }

}