import org.junit.Assert;
import org.junit.Test;

public class NavigationAndFormTests extends BaseTest {
    @Test
    public void homePage_shouldShowImportantContentAfterLogin() {
        HomePage homePage = new LoginPage(driver).loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        Assert.assertTrue(homePage.isFeaturedItemsVisible());
        Assert.assertTrue(homePage.getHeroHeadingText().contains("Automation Engineers"));
        Assert.assertTrue(homePage.header().getLoggedInUserText().contains(TestData.USER_DISPLAY_NAME));
    }

    @Test
    public void afterLogin_shouldNavigateToProductsPage() {
        HomePage homePage = new LoginPage(driver).loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        ProductsPage productsPage = homePage.openProductsPage();

        Assert.assertTrue(productsPage.isProductsPageVisible());
        Assert.assertEquals("Automation Exercise - All Products", productsPage.getPageTitle());
    }

    @Test
    public void afterLogin_shouldAddProductToCartAndShowItInCart() {
        HomePage homePage = new LoginPage(driver).loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        ProductsPage productsPage = homePage.openProductsPage();
        ProductDetailsPage productDetailsPage = productsPage.openFirstProductDetails();
        String productName = productDetailsPage.getProductName();

        CartPage cartPage = productDetailsPage.addToCartAndOpenCart();

        Assert.assertTrue(cartPage.containsProduct(productName));
    }

    @Test
    public void afterLogin_shouldSubmitContactForm() {
        HomePage homePage = new LoginPage(driver).loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);
        ContactUsPage contactUsPage = homePage.openContactUsPage();

        String successMessage = contactUsPage.submitContactForm(
                TestData.USER_DISPLAY_NAME,
                TestData.USER_EMAIL,
                "Selenium assignment",
                "This form was submitted from an automated test.");

        Assert.assertTrue(successMessage.toLowerCase().contains("successfully"));
    }
}