import org.junit.Assert;
import org.junit.Test;

public class AuthenticationTests extends BaseTest {
    @Test
    public void loginForm_shouldAcceptEmailAndPasswordInput() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.fillLoginForm(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        Assert.assertTrue(loginPage.isLoginFormVisible());
        Assert.assertEquals(TestData.USER_EMAIL, loginPage.getLoginEmailValue());
    }

    @Test
    public void loginWithValidCredentials_shouldOpenDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals("Automation Exercise - Signup / Login", loginPage.getPageTitle());

        HomePage homePage = loginPage.loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        Assert.assertEquals("Automation Exercise", homePage.getPageTitle());
        Assert.assertTrue(homePage.header().isLoggedInUserVisible());
        Assert.assertTrue(homePage.header().getLoggedInUserText().contains(TestData.USER_DISPLAY_NAME));
        Assert.assertTrue(homePage.header().isLogoutVisible());
    }

    @Test
    public void loginWithInvalidCredentials_shouldShowErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithInvalidCredentials(TestData.INVALID_EMAIL, TestData.INVALID_PASSWORD);

        Assert.assertEquals("Automation Exercise - Signup / Login", loginPage.getPageTitle());
        Assert.assertTrue(loginPage.getErrorMessage().contains("incorrect"));
    }

    @Test
    public void logout_shouldReturnToLoginPage() {
        HomePage homePage = new LoginPage(driver).loginWithValidCredentials(TestData.USER_EMAIL, TestData.USER_PASSWORD);

        LoginPage loginPage = homePage.logout();

        Assert.assertTrue(loginPage.isLoginFormVisible());
        Assert.assertEquals("Automation Exercise - Signup / Login", loginPage.getPageTitle());
    }
}
