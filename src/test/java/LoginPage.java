import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By LOGIN_FORM_HEADING = By.xpath("//h2[normalize-space()='Login to your account']");
    private static final By EMAIL_INPUT = By.xpath("//div[contains(@class,'login-form')]//input[@data-qa='login-email']");
    private static final By PASSWORD_INPUT = By.xpath("//div[contains(@class,'login-form')]//input[@data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.xpath("//div[contains(@class,'login-form')]//button[normalize-space()='Login']");
    private static final By ERROR_MESSAGE = By.xpath("//form[contains(@action,'/login')]//p[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'incorrect')]");
    private static final By SIGNUP_NAME_INPUT = By.xpath("//div[contains(@class,'signup-form')]//input[@data-qa='signup-name']");
    private static final By SIGNUP_EMAIL_INPUT = By.xpath("//div[contains(@class,'signup-form')]//input[@data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.xpath("//div[contains(@class,'signup-form')]//button[@data-qa='signup-button']");

    public LoginPage(WebDriver driver) {
        this(driver, true);
    }

    LoginPage(WebDriver driver, boolean openPage) {
        super(driver);
        if (openPage) {
            driver.get(TestData.BASE_URL + "/login");
            acceptCookieConsentIfPresent();
        }
        waitForElementVisible(LOGIN_FORM_HEADING);
    }

    public HomePage loginWithValidCredentials(String email, String password) {
        fillLoginForm(email, password);
        click(LOGIN_BUTTON);
        return new HomePage(driver, false);
    }

    public LoginPage loginWithInvalidCredentials(String email, String password) {
        fillLoginForm(email, password);
        click(LOGIN_BUTTON);
        waitForElementVisible(ERROR_MESSAGE);
        return this;
    }

    public SignupPage startSignup(String name, String email) {
        typeText(SIGNUP_NAME_INPUT, name);
        typeText(SIGNUP_EMAIL_INPUT, email);
        click(SIGNUP_BUTTON);
        return new SignupPage(driver);
    }

    public void fillLoginForm(String email, String password) {
        typeText(EMAIL_INPUT, email);
        typeText(PASSWORD_INPUT, password);
    }

    public String getLoginEmailValue() {
        return waitForElementVisible(EMAIL_INPUT).getAttribute("value");
    }

    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isLoginFormVisible() {
        return isElementDisplayed(LOGIN_FORM_HEADING);
    }
}
