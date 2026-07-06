package com.pasnormalstudios.pages;

import com.pasnormalstudios.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger log = LogManager.getLogger(this.getClass());
    public static final String BASE_URL = "https://pasnormalstudios.com";

    public BasePage() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickElementWithJSExecutor(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void waitAndClickElementWithJSExecutor(String xpath) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
        );
        clickElementWithJSExecutor(element);
    }
}
