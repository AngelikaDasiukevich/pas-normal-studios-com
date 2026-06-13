package com.pasnormalstudios.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private static final Logger log = LogManager.getLogger();
    private final String URL = "https://pasnormalstudios.com/";
    private final String ALLOW_COOKIES_BUTTON = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";
    private final String ACCOUNT_LINK = "//span[contains(text(), 'Account')]";
    private final String SEARCH_BUTTON = "//span[contains(text(), 'Search')]";

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

    public void clickAccountLink() {
        log.info("Navigating to the account page via the home page.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_LINK))).click();
    }

    public void clickSearchButton() {
        log.info("Navigating to the search page via the home page.");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON))).click();
    }
}
