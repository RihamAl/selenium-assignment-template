import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    private static final By ACCOUNT_CREATED_HEADING = By.xpath("//b[normalize-space()='Account Created!']");
    private static final By CONTINUE_BUTTON = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
        waitForElementVisible(ACCOUNT_CREATED_HEADING);
    }

    public boolean isAccountCreatedVisible() {
        return isElementDisplayed(ACCOUNT_CREATED_HEADING);
    }

    public HomePage continueToHomePage() {
        click(CONTINUE_BUTTON);
        return new HomePage(driver, false);
    }

    public HeaderComponent continueToLoggedInHeader() {
        click(CONTINUE_BUTTON);
        driver.get(TestData.BASE_URL);
        return new HeaderComponent(driver);
    }
}
