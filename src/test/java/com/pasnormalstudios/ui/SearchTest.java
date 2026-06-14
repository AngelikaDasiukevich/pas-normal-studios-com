package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.Product;
import com.pasnormalstudios.data.Products;
import com.pasnormalstudios.pages.ProductPage;
import com.pasnormalstudios.pages.SearchPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("E-Commerce Core")
@Feature("Search and Navigation")
@Tag("regression")
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
    @Description("Check that the main header text on the search page is displayed correctly.")
    @Severity(SeverityLevel.TRIVIAL)
    @Tag("ui")
    public void assertTitleText() {
        Assertions.assertEquals("Search", searchPage.getTitleText());
    }

    @Test
    @DisplayName("Verify input value in search input field")
    @Description("Verify that the text entered into the search bar matches the expected product name.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("ui")
    public void assertSearchInputValue() {
        searchPage.setSearchInput(product.getName());
        Assertions.assertEquals("Men's Essential Insulated Gilet", searchPage.getSearchInputText());
    }

    @Test
    @DisplayName("Verify navigation to Product Page from search suggestions")
    @Description("Type a product name, click on the matching search result, and verify redirection to the correct Product Page with the right color pre-selected.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    public void clickResult() {
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCartSearchResult(product.getName(), product.getColor());
        Assertions.assertEquals("Men's Essential Insulated Gilet", productPage.getTitleText());
        Assertions.assertTrue(productPage.isColorSelected(product.getColor()));
    }
}