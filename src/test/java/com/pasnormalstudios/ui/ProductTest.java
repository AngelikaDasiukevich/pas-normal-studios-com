package com.pasnormalstudios.ui;

import com.pasnormalstudios.BaseTest;
import com.pasnormalstudios.data.Product;
import com.pasnormalstudios.data.Products;
import com.pasnormalstudios.pages.ProductPage;
import com.pasnormalstudios.pages.SearchPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("E-Commerce Core")
@Feature("Cart")
@Tag("regression")
public class ProductTest extends BaseTest {
    SearchPage searchPage;
    ProductPage productPage;
    Product product;

    @BeforeEach
    public void setupSearch() {
        searchPage = new SearchPage();
        productPage = new ProductPage();
        product = Products.getBaseProduct();
    }

    @Test
    @DisplayName("Verify Product Size selection")
    @Description("Check that the size on the product page is selected correctly.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    public void assertProductSizeSelection() {
        homePage.clickSearchButton();
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCardSearchResult(product.getName(), product.getColor());
        productPage.clickSizeButton(product.getSize());
        Assertions.assertEquals(product.getSize(), productPage.getSelectedSize());
    }

    @Test
    @DisplayName("Verify Product Price")
    @Description("Check that the price on the product page is displayed correctly.")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    public void assertProductPrice() {
        homePage.clickSearchButton();
        searchPage.setSearchInput(product.getName());
        searchPage.clickProductCardSearchResult(product.getName(), product.getColor());
        Assertions.assertEquals(Double.parseDouble(product.getPrice()), productPage.getPriceAsDouble());
    }
}
