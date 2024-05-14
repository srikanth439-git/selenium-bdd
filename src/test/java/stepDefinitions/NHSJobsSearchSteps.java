package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
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

    @When("job seeker type {string} as the location")
    public void job_seeker_selects_location(String location) {
        homePage.enterLocation(location);
    }

    @When("job seeker type {string} in job reference text box")
    public void job_seeker_type_job_reference_id(String reference_id) {
        homePage.enterReferenceId(reference_id);
    }

    @When("job seeker type {string} in Employer text box")
    public void job_seeker_type_employer_name(String employer_name) {
        homePage.enterEmployerName(employer_name);
    }

    @And("Job seeker select {string} from dropdown")
    public void job_seeker_select_from_dropdown(String payRange) {
        homePage.select_payrange(payRange);
    }
    @Then("job seeker opens the job shown in search")
    public void job_seeker_opens_the_job_shown_in_search() {
        homePage.verifyResultsGridDisplay();
        homePage.openJobFromSearchResult();
    }

    @And("Job seeker should able to see {string} and {string} details")
    public void job_seeker_should_able_to_see_details(String employer, String referenceId) {
        Assert.assertEquals(homePage.getEmployerName(),employer);
        Assert.assertEquals(homePage.getReferenceIdOfJob(),referenceId);
    }


    @When("job seeker clicks the search button")
    public void job_seeker_clicks_search_button() {
        homePage.clickSearch();
    }

    @When("job seeker click more search options link")
    public void job_seeker_clicks_more_search_options_link() {
        homePage.clickMoreSearchOptionLink();
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
    public void job_seeker_should_see_job_postings_related_to_keyword(String keyword) {
        homePage.verifyResultsGridDisplay();
        homePage.verifyJobTitles(keyword);
    }

    @Then("job seeker should see {string} message")
    public void job_seeker_should_see_message(String expectedMessage) {
        Assert.assertEquals(homePage.getNoResultsMsg(),expectedMessage);
    }

    @When("job seeker should see search results")
    public void job_seeker_should_see_search_results() {
        homePage.verifyResultsGridDisplay();
    }

    @After
    public void closeBrowser() {
        Page.getDriver().quit();

    }


}