package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class NHSHomePage extends Page {

    By btn_accept_cookies = By.id("accept-cookies");
    By txtbox_keyword = By.id("keyword");
    By txtbox_location = By.id("location");
    By btn_search = By.id("search");
    By select_sort = By.id("sort");
    By results_job_titles = By.xpath("//a[@data-test='search-result-job-title']");
    By employer_town = By.id("employer_town");
    By date_posted = By.xpath("(//*[@id='date_posted'])[2]");
    By btn_goback = By.id("back-link");
    By results_grid = By.xpath("//ul[@class='nhsuk-list search-results']");
    By searchOptionsBtn = By.id("searchOptionsBtn");
    By txtbox_jobReference = By.id("jobReference");
    By txtbox_employer = By.id("employer");
    By select_payrange = By.id("payRange");
    By trac_job_reference = By.xpath("(//*[@id='trac-job-reference'])[2]");
    By employer_name = By.id("employer_name");
    By noresults_found = By.xpath("//h3[contains(@class,'nhsuk-heading-m nhsuk-u-margin-bottom')]");



    /**
     * Launch browser and open home page
     * @param browserName
     */
    public void openHomepage(String browserName) {
        openBrowser(browserName);
        ConfigReader configReader = new ConfigReader();
        String app_url = configReader.init_prop().getProperty("NHS_jobs_app_url");
        driver.get(app_url);
        driver.findElement(btn_accept_cookies).click();


    }

    public void enterJobkeyWord(String keyword) {

        driver.findElement(txtbox_keyword).sendKeys(keyword);
    }

    public void enterLocation(String location) {
        driver.findElement(txtbox_location).sendKeys(location);
    }

    public void clickSearch() {
        driver.findElement(btn_search).click();
        //waitForElementToBeVisible(driver.findElement(results_grid), 20);
    }

    public void verifyResultsGridDisplay()
    {
        waitForElementToBeVisible(driver.findElement(results_grid), 20);
        Assert.assertTrue(driver.findElement(results_grid).isDisplayed());
    }


    public void sortJobsBy(String sortBy) {
        Select sort = new Select(driver.findElement(select_sort));
        sort.selectByVisibleText(sortBy);

    }

    /**
     * Verify searched results jobs titles are relevant
     * @param expected_title
     */

    public void verifyJobTitles(String expected_title) {
        List<WebElement> title_elements = driver.findElements(results_job_titles);
        for (WebElement title : title_elements) {
            String actual_title = title.getText();
            Assert.assertTrue(actual_title.contains(expected_title));

        }


    }

    /**
     * Sometimes actual search location is not displayed in address section of job
     *
     * @param expected_location
     */
    public void veirfyJobLocation(String expected_location) {
        List<WebElement> title_elements = driver.findElements(results_job_titles);
        for (WebElement title : title_elements) {
            waitForElementToBeClickable(title, 20);
            title.click();
            waitForElementToBeVisible(driver.findElement(employer_town), 10);
            String actual_location = driver.findElement(employer_town).getText().trim();
            Assert.assertTrue(actual_location.contains(expected_location));
            driver.navigate().back();

        }

    }

    public void verifyJobsPostedDateOrder() {
        List<WebElement> title_elements = driver.findElements(results_job_titles);
        List<String> dates = new ArrayList<>();

        for (int i = 0; i < title_elements.size(); i++) {
            List<WebElement> ele = driver.findElements(results_job_titles);
            ele.get(i).click();
            scrollIntoView(driver.findElement(date_posted));
            waitForElementToBeVisible(driver.findElement(date_posted), 10);
            String date_posted_txt = driver.findElement(date_posted).getText().trim();
            dates.add(date_posted_txt);
            driver.findElement(btn_goback).click();

        }

        boolean isDescending = verifyDescendingOrder(dates);
        Assert.assertTrue(isDescending);
    }


    public void select_payrange(String pay_range){
        Select pay_range_dd=new Select(driver.findElement(select_payrange));
        pay_range_dd.selectByVisibleText(pay_range);

    }

    public void enterReferenceId(String reference_id){
        driver.findElement(txtbox_jobReference).sendKeys(reference_id);

    }

    public void enterEmployerName(String employer){
        driver.findElement(txtbox_employer).sendKeys(employer);

    }
    public void clickMoreSearchOptionLink() {
        driver.findElement(searchOptionsBtn).click();
    }

    public void openJobFromSearchResult() {
        driver.findElement(results_job_titles).click();
    }


    public String getEmployerName(){

        return driver.findElement(employer_name).getText();
    }

    public String getReferenceIdOfJob(){
        scrollDown();
        return driver.findElement(trac_job_reference).getText();
    }


    public String getNoResultsMsg() {
        return driver.findElement(noresults_found).getText();
    }
}
