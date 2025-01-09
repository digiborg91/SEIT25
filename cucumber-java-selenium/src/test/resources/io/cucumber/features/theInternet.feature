Feature: The Internet
  This feature covers (some) Example pages on 'the-internet.herokuapp.com'

  @TEST_TI_0001
  Scenario: Homepage has a list of links to Expected examples
    Given the page under test is 'https://the-internet.herokuapp.com'
    And an example Home Page step
    And add other steps

#  @TEST_TI_0001
#  Scenario: Basic Auth allows validated access
#    Given the page under test is 'https://the-internet.herokuapp.com'
#    When the 'Basic Auth' example is opened
#    And valid credentials are supplied
#    Then Congratulations should be displayed
#
#  @TEST_TI_0001
#  Scenario: Sortable Data Tables - Example 1 displays the expected 4 results
#    Given the page under test is 'Sortable Data Tables'
#    And add other steps