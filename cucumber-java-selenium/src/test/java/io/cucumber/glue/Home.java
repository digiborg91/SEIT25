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
import io.cucumber.datatable.DataTable;

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

  @Then("the Example {int} table should display the following results")
  public void theExampleTableShouldDisplayTheFollowingResults(int tableNumber, DataTable expectedTable) {

    String tableId = "table" + tableNumber;
    WebElement table = getDriver().findElement(By.id(tableId));

    // Get all rows inside the tbody (skip header row)
    List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
    List<List<String>> actualTableData = new ArrayList<>();

    for (WebElement row : rows) {
      // Get all cell data except the last column (Action)
      List<WebElement> cells = row.findElements(By.cssSelector("td"));
      List<String> rowData = cells.stream()
              .map(WebElement::getText)
              .collect(Collectors.toList());

      // Exclude the "Action" column data, i.e., the last column
      rowData.remove(rowData.size() - 1);  // Remove the last element, which is "Action"

      actualTableData.add(rowData);
    }

    // Convert expected Cucumber DataTable to List<List<String>> excluding headers
    List<List<String>> expectedData = new ArrayList<>(expectedTable.asLists(String.class));

    // Remove header row from expected data (if present)
    expectedData.remove(0);

    // Assert that actual data matches expected data
    assertEquals(expectedData, actualTableData, "Table data does not match!");
  }
}