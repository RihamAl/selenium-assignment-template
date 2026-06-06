import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By FEATURED_ITEMS_HEADING = By.xpath("//h2[normalize-space()='Features Items']");
    private static final By HERO_HEADING = By.xpath("//h2[normalize-space()='Full-Fledged practice website for Automation Engineers']");

    public HomePage(WebDriver driver) {
        this(driver, true);
    }

    HomePage(WebDriver driver, boolean openPage) {
        super(driver);
        if (openPage) {
            driver.get(TestData.BASE_URL);
            acceptCookieConsentIfPresent();
        }
        waitForElementVisible(FEATURED_ITEMS_HEADING);
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }

    public String getHeroHeadingText() {
        return getText(HERO_HEADING);
    }

    public boolean isFeaturedItemsVisible() {
        return isElementDisplayed(FEATURED_ITEMS_HEADING);
    }

    public ProductsPage openProductsPage() {
        driver.get(TestData.BASE_URL + "/products");
        return new ProductsPage(driver, false);
    }

    public ContactUsPage openContactUsPage() {
        driver.get(TestData.BASE_URL + "/contact_us");
        return new ContactUsPage(driver, false);
    }

    public LoginPage logout() {
        driver.get(TestData.BASE_URL + "/logout");
        return new LoginPage(driver, false);
    }
}