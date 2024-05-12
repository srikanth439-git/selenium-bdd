package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.*;

public class NHSJobsSearchSteps {
    NHSHomePage homePage;

    @Given("job seeker is on the NHS Jobs homepage in {string} browser")
    public void the_user_is_on_the_nhs_jobs_homepage_in_browser(String browserName) {
        homePage = new NHSHomePage();
        homePage.openHomepage(browserName);
    }

    @When("job seeker enters {string} in the keyword search bar")
    public void job_seeker_enters_keyword_in_search_bar(String keyword) {
        homePage.enterJobkeyWord(keyword);

    }

    @When("job seeker selects {string} as the location")
    public void job_seeker_selects_location(String location) {
        homePage.enterLocation(location);
    }

    @When("job seeker clicks the search button")
    public void job_seeker_clicks_search_button() {
        homePage.clickSearch();
    }

    @When("job seeker sort jobs by Date posted")
    public void job_seeker_sort_jobs_by_date() {
        homePage.sortJobsBy("Date Posted (newest)");
    }

    @When("verify newest jobs on top")
    public void verify_newest_jobs_on_top() {
        homePage.verifyJobsPostedDateOrder();
    }


    @Then("job seeker should see a list of job postings related to {string}")
    public void job_seeker_should_see_job_postings_related_to_keyword_in_location(String keyword) {
        homePage.verifyJobTitles(keyword);


    }


    @After
    public void closeBrowser() {
        System.out.println("Closing browser");

        Page.getDriver().quit();

    }

}