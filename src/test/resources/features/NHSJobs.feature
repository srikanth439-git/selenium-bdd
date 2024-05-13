@RegressionTest
Feature: NHS Jobs Website's search Functionality
  Background:
    Given job seeker is on the NHS Jobs homepage in "firefox" browser
#Current supporting chrome and firefox

  Scenario Outline: Job seeker searches for jobs by keyword and location

    When job seeker enters "<keyword>" in the keyword search bar
    And job seeker type "<location>" as the location
    And job seeker clicks the search button
    Then job seeker should see a list of job postings related to "<keyword>"
    Examples:
      | keyword                   | location   |
      | Pharmacy Technician       | London     |
      | Nurse                     | Manchester |
      | Therapist                 | Birmingham |
      | General Practitioner      | London     |
      | Radiographer              | Hertfordshire |
      | Midwife                   | London Fields, London|

  Scenario Outline: Job seeker searches for jobs by advance filter option
    When job seeker click more search options link
    When job seeker type "<Reference_id>" in job reference text box
    And  job seeker type "<Employer>" in Employer text box
    And Job seeker select "<Pay_Range>" from dropdown
    And job seeker clicks the search button
    Then job seeker opens the job shown in search
    And  Job seeker should able to see "<Employer>" and "<Reference_id>" details
    Examples:
      | Reference_id              | Employer                                        | Pay_Range|
      | 200-5591794-MA-AA-F       | St George's Healthcare NHS Foundation Trust     | £10,000 to £20,000 |

  Scenario: Job seeker try to search keyword which doesnt match any job
    When job seeker enters "myjobsabcdefshg" in the keyword search bar
    And job seeker clicks the search button
    Then job seeker should see "No result found" message



  Scenario: Job seeker sort the jobs by date posted
    When job seeker enters "Pharmacy Technician" in the keyword search bar
    And job seeker type "London" as the location
    And job seeker clicks the search button
    And job seeker sort jobs by Date posted
    Then verify newest jobs on top



