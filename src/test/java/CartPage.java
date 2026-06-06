import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By SHOPPING_CART_HEADING = By.xpath("//li[normalize-space()='Shopping Cart']");

    public CartPage(WebDriver driver) {
        this(driver, true);
    }

    CartPage(WebDriver driver, boolean openPage) {
        super(driver);
        if (openPage) {
            driver.get(TestData.BASE_URL + "/view_cart");
            acceptCookieConsentIfPresent();
        }
        waitForElementVisible(SHOPPING_CART_HEADING);
    }

    private By productRowByName(String productName) {
        return By.xpath("//tr[.//a[normalize-space()='" + productName + "']]");
    }

    public boolean containsProduct(String productName) {
        return isElementDisplayed(productRowByName(productName));
    }
}
