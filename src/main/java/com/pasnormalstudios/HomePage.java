package com.pasnormalstudios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final String URL = "https://pasnormalstudios.com/";
    private final String ALLOW_COOKIES_BUTTON = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']";
    private final String ACCOUNT_LINK = "//span[contains(text(), 'Account')]";

    public HomePage() {
        super();
    }

    public void open() {
        driver.get(URL);
    }

    public void clickAllowCookiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ALLOW_COOKIES_BUTTON))).click();
    }

    public void clickAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ACCOUNT_LINK))).click();
    }


}
