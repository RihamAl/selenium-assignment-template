import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {
    private static final By ACCOUNT_DELETED_HEADING = By.xpath("//b[normalize-space()='Account Deleted!']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
        waitForElementVisible(ACCOUNT_DELETED_HEADING);
    }

    public boolean isAccountDeletedVisible() {
        return isElementDisplayed(ACCOUNT_DELETED_HEADING);
    }
}
