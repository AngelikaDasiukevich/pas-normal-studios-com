package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.Product;
import com.pasnormalstudios.data.Products;
import com.pasnormalstudios.pages.CartPage;
import com.pasnormalstudios.pages.ProductPage;
import com.pasnormalstudios.pages.SearchPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("E-Commerce Core")
@Feature("Cart")
@Tag("regression")
public class CartTest extends BaseTest {
    CartPage cartPage;
    ProductPage productPage;
    SearchPage searchPage;
    Product product;

    @BeforeEach
    public void setupSearch() {
        cartPage = new CartPage();
        productPage = new ProductPage();
        searchPage = new SearchPage();
        product = Products.getBaseProduct();
    }

    @Test
    @DisplayName("Verify Cart page title text")
    @Description("Check that the main header text on the cart page is displayed correctly.")
    @Severity(SeverityLevel.TRIVIAL)
    @Tag("ui")
    public void assertTitleText() {
        homePage.clickCartButton();
        Assertions.assertEquals("Cart", cartPage.getTitleText());
    }

    @Test
    @DisplayName("Verify Empty Cart message")
    @Description("Check that the message on the empty cart page is displayed correctly.")
    @Severity(SeverityLevel.TRIVIAL)
    @Tag("ui")
    public void assertEmptyCartText() {
        homePage.clickCartButton();
        Assertions.assertEquals("Your cart is empty!Explore New Arrivals", cartPage.getEmptyCartMessage());
    }

    @Test
    @DisplayName("Verify cart count updates after adding product")
    @Description("Check that the cart title shows 'Cart (1)' after successfully adding a product.")
    @Severity(SeverityLevel.NORMAL)
    @Tag("cart")
    public void assertAddingProductToCart() {
        homePage.clickSearchButton();
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCardSearchResult(product.getName(), product.getColor());
        productPage.clickSizeButton(product.getSize());
        productPage.clickAddToCartButton();
        Assertions.assertEquals("Cart (1)", cartPage.getTitleText());
    }

    @Test
    @DisplayName("Verify product details in cart")
    @Description("Verify that the product added to the cart matches the selected attributes (Name, Color, Size, Price).")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("cart")
    public void assertProductDetailsInCart() {
        homePage.clickSearchButton();
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCardSearchResult(product.getName(), product.getColor());
        productPage.clickSizeButton(product.getSize());
        productPage.clickAddToCartButton();
        Assertions.assertTrue(cartPage.isProductInCart(product.getName(), product.getColor(), product.getSize(), product.getPrice()));
    }
}