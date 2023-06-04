package com.test.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.test.selenium.DriverBase;
import com.test.selenium.page_objects.GoogleHomePage;
import com.test.selenium.page_objects.GoogleSearchPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GoogleSearchIT extends DriverBase {


    @Test
    public void googleSearch() throws Exception {
        // Create a new WebDriver instance
        WebDriver driver = getDriver();

        //navigate to the google home page
        driver.get("http://www.google.com");
        // Alternative - driver.navigate().to("http://www.google.com");

        //Instantiate an instance of our GoogleHomePage page object
        GoogleHomePage googleHomePage = new GoogleHomePage();
        GoogleSearchPage googleSearchPage = googleHomePage.enterSearchTerm("Rose").submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // We wait for up to 15 seconds for the page to load, an exception is thrown if it doesn't
        googleSearchPage.waitForPageTitleToStartWith("Rose");
        assertThat(googleSearchPage.getPageTitle()).isEqualTo("Rose - Google Search");
    }
}
