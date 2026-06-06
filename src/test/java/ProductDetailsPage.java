import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    private static final By PRODUCT_NAME = By.xpath("//div[@class='product-information']/h2");
    private static final By ADD_TO_CART_BUTTON = By.xpath("//div[@class='product-information']//button[normalize-space()='Add to cart']");
    private static final By VIEW_CART_LINK = By.xpath("//div[@id='cartModal']//a[normalize-space()='View Cart']");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        waitForElementVisible(PRODUCT_NAME);
    }

    public String getProductName() {
        return getText(PRODUCT_NAME);
    }

    public CartPage addToCartAndOpenCart() {
        click(ADD_TO_CART_BUTTON);
        click(VIEW_CART_LINK);
        return new CartPage(driver, false);
    }
}