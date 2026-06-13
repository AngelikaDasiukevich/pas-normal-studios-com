package com.pasnormalstudios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {
    private final String TITLE = "//h2[contains(@class, 'text-xl')]";
    private final String SEARCH_INPUT = "//input[@type='search']";
    private final String PRODUCT_CARDS_SEARCH_RESULT = "//div[@role='option']//div[@class='flex flex-col']";

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

    public void clickProductCartSearchResult(String name, String color) {
        log.info("Searching for product: '{}' with color: '{}'", name, color);

        List<WebElement> productResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(PRODUCT_CARDS_SEARCH_RESULT))
        );

        WebElement resultToClick = productResults.stream()
                .filter(result -> isRequiredProduct(result, name, color))
                .map(result -> result.findElement(By.xpath("./span[1]")))
                .findFirst()
                .orElseThrow();

        log.info("Product found. Clicking on title.");
        resultToClick.click();
    }

    private boolean isRequiredProduct(WebElement result, String name, String color) {
        String actualName = result.findElement(By.xpath("./span[1]")).getText();
        String actualColor = result.findElement(By.xpath("./span[2]")).getText();
        return actualName.equals(name) && actualColor.equals(color);
    }
}
