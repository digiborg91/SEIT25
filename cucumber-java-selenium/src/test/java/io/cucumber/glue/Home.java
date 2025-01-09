package io.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;

public class Home extends Context {

  public Home(Manager manager) {
    super(manager);
  }

  @Given("an example Home Page step")
  public void exampleHomePageStep() {
    System.out.println("printing shared stash :" + getTestStash().toString());
  }
}