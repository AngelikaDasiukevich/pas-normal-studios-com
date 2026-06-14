package com.pasnormalstudios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage{
    private String TITLE = "//h1[contains(@class, 'max-w-72')]";
    private String COLOR_BUTTON = "//div[contains(@class, 'flex-wrap')]//button[@aria-label]";
    private String SIZE_BUTTON = "//button[contains(@title, 'Size: ')]";
    private String PRICE_TEXT = "//button[contains(@class, 'bg-primary')]//div[contains(@class, 'gap-x-2')]";
    private String ADD_TO_CART_BUTTON = "//button[contains(@class, 'bg-primary')]";

    public ProductPage() {
        super();
    }

    public String getTitleText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
        log.info("Fetching Title Text: {}", text);
        return text;
    }

    public String getSelectedColor() {
        List<WebElement> buttons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(COLOR_BUTTON))
        );

        return buttons.stream()
                .filter(button -> "true".equals(button.getAttribute("aria-current")))
                .findFirst()
                .map(button -> button.getAttribute("aria-label"))
                .orElseThrow(() -> new RuntimeException("None of the colors is selected"));
    }

    public void clickSizeButton(String size) {
        log.info("Selecting size: '{}'", size);

        List<WebElement> sizeButtons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(SIZE_BUTTON))
        );

        sizeButtons.stream()
                .filter(button -> {
                    String title = button.getAttribute("title");
                    return title.contains("Size: " + size) && !title.contains("Out of stock");
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Size '" + size + "' is not available (not found or Out of Stock)"))
                .click();

        log.info("Size '{}' successfully selected.", size);
    }

    public String getSelectedSize() {
        List<WebElement> sizeButtons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(SIZE_BUTTON))
        );

        return sizeButtons.stream()
                .filter(button -> "true".equals(button.getAttribute("aria-pressed")))
                .findFirst()
                .map(button -> button.getAttribute("title").replace("Size: ", ""))
                .orElseThrow();
    }

    public double getPriceAsDouble() {
        String rawPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRICE_TEXT))).getText();
        String cleanPrice = rawPrice.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanPrice);
    }

    public void clickAddToCartButton() {
        log.info("Clicking 'Add To Cart' button");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_TO_CART_BUTTON))).click();
    }
}
