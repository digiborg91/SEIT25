package io.cucumber.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

  protected ChromeDriver driver;

  public Page(ChromeDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    waitForPageLoad();
  }

  public void waitForPageLoad() {
    try {
      Thread.sleep(Duration.ofSeconds(5));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}