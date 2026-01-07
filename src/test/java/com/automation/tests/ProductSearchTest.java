package com.automation.tests;
import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
public class ProductSearchTest extends BaseTest {
    @DataProvider(name = "searchItems")
    public Object[][] getSearchData() {
        return new Object[][] {
                {"T-Shirt"}
                // {"Dress"}
        };
    }
    @Test(dataProvider = "searchItems")
    public void verifyProductSearch(String product) {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        // navigate to products page
        homePage.navigateToProducts();
        
        softAssert.assertTrue(productsPage.isProductPageVisible(), "Products page is not visible");

        // search
        homePage.searchFor(product);
        // verify search results
        List<WebElement> results = productsPage.getSearchResultList();
        softAssert.assertFalse(results.isEmpty(), "No search results found for: " + product);
        
        // verify each result contains the search term
        for (WebElement element : results) {
            String productName = element.getText().toLowerCase();
            softAssert.assertTrue(productName.contains(product.toLowerCase()),
                    "Product name does not contain search term. Product: " + productName + ", Search Term: " + product);
        }
        softAssert.assertAll();
    }

    
}