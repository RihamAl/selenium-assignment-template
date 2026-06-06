import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {
    private static final By LOGGED_IN_USER = By.xpath("//li[contains(normalize-space(.), 'Logged in as')]");
    private static final By LOGOUT_LINK = By.xpath("//header//li[.//a[@href='/logout']]//a[@href='/logout']");
    private static final By DELETE_ACCOUNT_LINK = By.xpath("//header//li[.//a[@href='/delete_account']]//a[@href='/delete_account']");
    private static final By PRODUCTS_LINK = By.xpath("//header//a[@href='/products' and contains(normalize-space(), 'Products')]");
    private static final By CART_LINK = By.xpath("//header//a[@href='/view_cart' and contains(normalize-space(), 'Cart')]");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public String getLoggedInUserText() {
        return getText(LOGGED_IN_USER);
    }

    public boolean isLoggedInUserVisible() {
        return isElementDisplayed(LOGGED_IN_USER);
    }

    public boolean isLogoutVisible() {
        return isElementDisplayed(LOGOUT_LINK);
    }

    public void openProductsPage() {
        click(PRODUCTS_LINK);
    }

    public void openCartPage() {
        click(CART_LINK);
    }

    public void logout() {
        click(LOGOUT_LINK);
    }

    public AccountDeletedPage deleteAccount() {
        click(DELETE_ACCOUNT_LINK);
        return new AccountDeletedPage(driver);
    }
}
