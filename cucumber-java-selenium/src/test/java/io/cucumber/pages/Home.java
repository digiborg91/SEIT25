package io.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Home extends Page {

  public Home(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "#content ul li a")
  private List<WebElement> exampleLinks;

  @FindBy(css = "h1")
  private WebElement title;

  public WebElement getTitle() {
    return title;
  }

  public List<String> getExampleLinksText() {
    return exampleLinks.stream().map(WebElement::getText).toList();
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }

  @FindBy(css = "div.example p")
  private WebElement successMessage;

  public String getSuccessMessage() {
    return successMessage.getText();
  }
}
