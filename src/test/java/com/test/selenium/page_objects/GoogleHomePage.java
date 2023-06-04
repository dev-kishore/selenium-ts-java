package com.test.selenium.page_objects;

import com.test.selenium.util.Query;
import com.test.selenium.DriverBase;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.test.selenium.util.AssignDriver.initQueryObjects;

public class GoogleHomePage {

    private final Query searchBar = new Query().defaultLocator(By.name("q"));
    private final Query googleSearch = new Query().defaultLocator(By.name("btnK"));
    private final WebDriverWait wait;

    public GoogleHomePage() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
        wait = new WebDriverWait(DriverBase.getDriver(), Duration.ofSeconds(15), Duration.ofMillis(100));
    }

    public GoogleHomePage enterSearchTerm(String searchTerm) {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBar.by()));
        searchBar.findWebElement().clear();
        searchBar.findWebElement().sendKeys(searchTerm);

        return this;
    }

    public GoogleSearchPage submitSearch() throws Exception {
        googleSearch.findWebElement().submit();

        return new GoogleSearchPage();
    }

}
