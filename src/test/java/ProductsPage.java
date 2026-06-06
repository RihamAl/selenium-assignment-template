import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private static final By ALL_PRODUCTS_HEADING = By.xpath("//h2[normalize-space()='All Products']");
    private static final By FIRST_PRODUCT_CARD = By.xpath("(//div[contains(@class,'features_items')]//div[contains(@class,'single-products')])[1]");
    private static final By FIRST_PRODUCT_OVERLAY = By.xpath("(//div[contains(@class,'features_items')]//div[contains(@class,'product-overlay')]//div[contains(@class,'overlay-content')])[1]");

    public ProductsPage(WebDriver driver) {
        this(driver, true);
    }

    ProductsPage(WebDriver driver, boolean openPage) {
        super(driver);
        if (openPage) {
            driver.get(TestData.BASE_URL + "/products");
            acceptCookieConsentIfPresent();
        }
        waitForElementVisible(ALL_PRODUCTS_HEADING);
    }

    public ProductDetailsPage openFirstProductDetails() {
        driver.get(TestData.BASE_URL + "/product_details/1");
        return new ProductDetailsPage(driver);
    }

    public boolean isProductsPageVisible() {
        return isElementDisplayed(ALL_PRODUCTS_HEADING);
    }

    public String hoverOverFirstProductAndGetOverlayText() {
        new Actions(driver).moveToElement(waitForElementVisible(FIRST_PRODUCT_CARD)).perform();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_PRODUCT_OVERLAY)).getText();
    }
}
