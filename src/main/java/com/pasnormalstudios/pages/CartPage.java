package com.pasnormalstudios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private static String TITLE = "//h2[contains(@class, 'text-xl')]";
    private static String EMPTY_CART_MESSAGE = "//div[contains(@class, 'flex-col')]//p[contains(@class, 'text-center')]";
    private static String CART_ITEM = "//div[contains(@class, 'transition')]//li";
    private final By CART_ITEM_NAME = By.xpath(".//span[contains(@class, 'pr-8')]");
    private final By CART_ITEM_COLOR = By.xpath(".//div[contains(@class, 'gap-2')]/span[1]");
    private final By CART_ITEM_SIZE = By.xpath(".//div[contains(@class, 'gap-2')]/span[2]");
    private final By CART_ITEM_PRICE = By.xpath(".//div[contains(@class, 'pt-[10px]')]/span");

    public CartPage() {
        super();
    }

    public String getTitleText() {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE))).getText();
        log.info("Fetching Title Text: {}", text);
        return text;
    }

    public String getEmptyCartMessage() {
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EMPTY_CART_MESSAGE))).getText();
        log.info("Empty cart message is: '{}'", value);
        return value;
    }

    public boolean isProductInCart(String name, String color, String size, String price) {
        log.info("Checking if product '{}' ({}, {}, {}) is in cart", name, color, size, price);
        double expectedPrice = Double.parseDouble(price.replaceAll("[^0-9.]", ""));

        List<WebElement> cartItems = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CART_ITEM))
        );

        return cartItems.stream().anyMatch(item -> {
            String actualName = item.findElement(CART_ITEM_NAME).getText();
            String actualColor = item.findElement(CART_ITEM_COLOR).getText();
            String actualSize = item.findElement(CART_ITEM_SIZE).getText();

            String rawPrice = item.findElement(CART_ITEM_PRICE).getText();
            double actualPrice = Double.parseDouble(rawPrice.replaceAll("[^0-9.]", ""));

            log.info("Product is in cart");
            return actualName.equals(name) &&
                    actualColor.equals(color) &&
                    actualSize.equals(size) &&
                    Math.abs(actualPrice - expectedPrice) < 0.001;

        });
    }
}
