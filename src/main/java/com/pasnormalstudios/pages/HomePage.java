package com.pasnormalstudios.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private static final Logger log = LogManager.getLogger();
    private final String URL = "https://pasnormalstudios.com/";
    private final String ALLOW_COOKIES_BUTTON = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";
    private final String ACCOUNT_LINK = "//div[contains(@class, 'nav-items')]//a[contains(@href, '/account')]";
    private final String SEARCH_BUTTON = "//div[contains(@class, 'nav-items')]//button[contains(@class, 'search-trigger')]";

    public HomePage() {
        super();
    }

    public void open() {
        driver.get(URL);
    }

    public void clickAllowCookiesButton() {
        log.info("Clicking 'Allow Cookies' button");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ALLOW_COOKIES_BUTTON))).click();
    }

//    public void clickAccountLink() {
//        log.info("Navigating to the account page via the home page.");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_LINK))).click();
//    }

    public void clickAccountLink() {
        log.info("Clicking on Account link via JavaScript to bypass Jenkins overlay");

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(ACCOUNT_LINK))
        );

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

//    public void clickSearchButton() {
//        log.info("Navigating to the search page via the home page.");
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON))).click();
//    }

    public void clickSearchButton() {
        log.info("Clicking on Account link via JavaScript to bypass Jenkins overlay");

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_BUTTON))
        );

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
