package com.pasnormalstudios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage{
    private String TITLE = "//h1[contains(@class, 'max-w-72')]";
    private String ACTIVE_COLOR_BUTTON = "//div[@class='flex flex-wrap gap-2 p-1']//button[@aria-label]";

    public ProductPage() {
        super();
    }

    public String getTitleText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
        log.info("Fetching Title Text: {}", text);
        return text;
    }

    public boolean isColorSelected(String color) {
        log.info("Verifying if color '{}' is selected ", color);

        List<WebElement> buttons = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ACTIVE_COLOR_BUTTON))
        );

        boolean result = buttons.stream()
                .filter(button -> color.equals(button.getAttribute("aria-label")))
                .findFirst()
                .map(button -> "true".equals(button.getAttribute("aria-current")))
                .orElseThrow();

        log.info("Color is selected.");
        return result;
    }
}
