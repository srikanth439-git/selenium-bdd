@RegressionTest
Feature: NHS Jobs Website Functionality
  Background:
    Given job seeker is on the NHS Jobs homepage in "firefox" browser
#Current supporting chrome and firefox

  Scenario Outline: Job seeker searches for jobs by keyword and location

    When job seeker enters "<keyword>" in the keyword search bar
    And job seeker selects "<location>" as the location
    And job seeker clicks the search button
    Then job seeker should see a list of job postings related to "<keyword>"
    Examples:
      | keyword                   | location   |
      | Pharmacy Technician       | London     |
      | Nurse                     | Manchester |
      | Therapist                 | Birmingham |

  Scenario: Job seeker sort the jobs by date posted
    When job seeker enters "Pharmacy Technician" in the keyword search bar
    And job seeker selects "London" as the location
    And job seeker clicks the search button
    And job seeker sort jobs by Date posted
    Then verify newest jobs on top
