package com.pasnormalstudios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    private final String TITLE = "//h2[contains(@class, 'text-xl')]";
    private final String SEARCH_INPUT = "//input[@type='search']";
    private final String FOUND_PRODUCT_CARD = "//div[@role='option']//div[@class='flex flex-col']";
    private final String FOUND_PRODUCT_CARD_NAME = "./span[1]";
    private final String FOUND_PRODUCT_CARD_COLOR = "./span[2]";

    public SearchPage(){
        super();
    }

    public String getTitleText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
        log.info("Fetching Title Text: {}", text);
        return text;
    }

    public void setSearchInput(String value) {
        log.info("Typing search value: '{}'", value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT))).sendKeys(value);
    }

    public String getSearchInputText() {
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT))).getAttribute("value");
        log.info("Search value is: '{}'", value);
        return value;
    }

    public void clickProductCardSearchResult(String name, String color) {
        log.info("Searching for product: '{}' with color: '{}'", name, color);

        List<WebElement> productResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(FOUND_PRODUCT_CARD))
        );

        WebElement resultToClick = productResults.stream()
                .filter(result -> isRequiredProduct(result, name, color))
                .map(result -> result.findElement(By.xpath(FOUND_PRODUCT_CARD_NAME)))
                .findFirst()
                .orElseThrow();

        log.info("Product found. Clicking on title.");
        resultToClick.click();
    }

    private boolean isRequiredProduct(WebElement result, String name, String color) {
        String actualName = result.findElement(By.xpath(FOUND_PRODUCT_CARD_NAME)).getText();
        String actualColor = result.findElement(By.xpath(FOUND_PRODUCT_CARD_COLOR)).getText();
        return actualName.equals(name) && actualColor.equals(color);
    }
}
