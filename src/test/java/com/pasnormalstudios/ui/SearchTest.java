package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.Product;
import com.pasnormalstudios.data.Products;
import com.pasnormalstudios.pages.ProductPage;
import com.pasnormalstudios.pages.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {
    SearchPage searchPage;
    ProductPage productPage;
    Product product;

    @BeforeEach
    public void setupSearch() {
        searchPage = new SearchPage();
        homePage.clickSearchButton();
        productPage = new ProductPage();
        product = Products.getBaseProduct();
    }

    @Test
    @DisplayName("Verify Search page title text")
    public void assertTitleText() {
        Assertions.assertEquals("Search", searchPage.getTitleText());
    }

    @Test
    @DisplayName("Verify input value in search input field")
    public void assertSearchInputValue() {
        searchPage.setSearchInput(product.getName());
        Assertions.assertEquals("Men's Essential Insulated Gilet", searchPage.getSearchInputText());
    }

    @Test
    @DisplayName("Verify input value in search input field")
    public void clickResult() {
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCartSearchResult(product.getName(), product.getColor());
        Assertions.assertEquals("Men's Essential Insulated Gilet", productPage.getTitleText());
        Assertions.assertTrue(productPage.isColorSelected(product.getColor()));
    }
}
