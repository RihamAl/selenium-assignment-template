import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {
    private static final By ACCOUNT_INFORMATION_HEADING = By.xpath("//b[normalize-space()='Enter Account Information']");
    private static final By TITLE_MR_RADIO = By.xpath("//input[@id='id_gender1']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@data-qa='password']");
    private static final By DAY_SELECT = By.xpath("//select[@data-qa='days']");
    private static final By MONTH_SELECT = By.xpath("//select[@data-qa='months']");
    private static final By YEAR_SELECT = By.xpath("//select[@data-qa='years']");
    private static final By FIRST_NAME_INPUT = By.xpath("//input[@data-qa='first_name']");
    private static final By LAST_NAME_INPUT = By.xpath("//input[@data-qa='last_name']");
    private static final By COMPANY_INPUT = By.xpath("//input[@data-qa='company']");
    private static final By ADDRESS_INPUT = By.xpath("//input[@data-qa='address']");
    private static final By COUNTRY_SELECT = By.xpath("//select[@data-qa='country']");
    private static final By STATE_INPUT = By.xpath("//input[@data-qa='state']");
    private static final By CITY_INPUT = By.xpath("//input[@data-qa='city']");
    private static final By ZIPCODE_INPUT = By.xpath("//input[@data-qa='zipcode']");
    private static final By MOBILE_NUMBER_INPUT = By.xpath("//input[@data-qa='mobile_number']");
    private static final By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
        waitForElementVisible(ACCOUNT_INFORMATION_HEADING);
    }

    public AccountCreatedPage createAccountWithRequiredDetails() {
        click(TITLE_MR_RADIO);
        typeText(PASSWORD_INPUT, "Password123!");
        selectByVisibleText(DAY_SELECT, "10");
        selectByVisibleText(MONTH_SELECT, "May");
        selectByVisibleText(YEAR_SELECT, "1995");
        typeText(FIRST_NAME_INPUT, "Selenium");
        typeText(LAST_NAME_INPUT, "Student");
        typeText(COMPANY_INPUT, "ELTE");
        typeText(ADDRESS_INPUT, "Test Street 1");
        selectByVisibleText(COUNTRY_SELECT, "Canada");
        typeText(STATE_INPUT, "Ontario");
        typeText(CITY_INPUT, "Toronto");
        typeText(ZIPCODE_INPUT, "10001");
        typeText(MOBILE_NUMBER_INPUT, "1234567890");
        click(CREATE_ACCOUNT_BUTTON);
        return new AccountCreatedPage(driver);
    }
}
