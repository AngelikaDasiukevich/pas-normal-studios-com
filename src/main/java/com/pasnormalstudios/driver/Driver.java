package com.pasnormalstudios.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    private static WebDriver driver;

    private Driver() {};

    public static WebDriver getDriver() {
        if (driver == null)
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080"); // Жесткий размер экрана
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}