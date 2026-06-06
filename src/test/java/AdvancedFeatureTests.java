import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;

public class AdvancedFeatureTests extends BaseTest {
    @Test
    public void browserHistory_shouldNavigateBackAndForwardBetweenHomeAndProducts() {
        StaticContentPage page = new StaticContentPage(driver).open("/");
        page.waitForPageTitle("Automation Exercise");

        page.open("/products");
        page.waitForPageTitle("Automation Exercise - All Products");

        driver.navigate().back();
        page.waitForPageTitle("Automation Exercise");
        Assert.assertEquals(TestData.BASE_URL + "/", page.getCurrentUrl());

        driver.navigate().forward();
        page.waitForPageTitle("Automation Exercise - All Products");
        Assert.assertTrue(page.getCurrentUrl().contains("/products"));
    }

    @Test
    public void browserCookie_shouldBeAddedReadAndDeleted() {
        driver.get(TestData.BASE_URL);

        Cookie assignmentCookie = new Cookie("selenium_assignment", "enabled");
        driver.manage().addCookie(assignmentCookie);

        Assert.assertEquals("enabled", driver.manage().getCookieNamed("selenium_assignment").getValue());

        driver.manage().deleteCookieNamed("selenium_assignment");

        Assert.assertNull(driver.manage().getCookieNamed("selenium_assignment"));
    }

    @Test
    public void publicPages_shouldDisplayExpectedContent() {
        StaticContentPage page = new StaticContentPage(driver);
        PageExpectation[] pages = {
                new PageExpectation("/", "Automation Exercise", "Features Items"),
                new PageExpectation("/products", "Automation Exercise - All Products", "All Products"),
                new PageExpectation("/contact_us", "Automation Exercise - Contact Us", "Get In Touch"),
                new PageExpectation("/test_cases", "Automation Practice Website for UI Testing - Test Cases", "Test Cases")
        };

        for (PageExpectation expectedPage : pages) {
            page.open(expectedPage.path);

            Assert.assertEquals(expectedPage.title, page.getPageTitle());
            Assert.assertTrue(page.isTextVisible(expectedPage.visibleText));
        }
    }

    @Test
    public void productsPage_shouldShowOverlayWhenHoveringOverProduct() {
        ProductsPage productsPage = new ProductsPage(driver);

        String overlayText = productsPage.hoverOverFirstProductAndGetOverlayText();

        Assert.assertTrue(overlayText.contains("Blue Top"));
        Assert.assertTrue(overlayText.contains("Add to cart"));
    }

    @Test
    public void contactForm_shouldUploadFileAndSubmitSuccessfully() throws IOException {
        Path uploadFile = Files.createTempFile("selenium-contact-upload", ".txt");
        Files.write(uploadFile, "Uploaded by Selenium test".getBytes(StandardCharsets.UTF_8));
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        String successMessage = contactUsPage.submitContactFormWithAttachment(
                TestData.USER_DISPLAY_NAME,
                TestData.USER_EMAIL,
                "Selenium file upload",
                "This contact form includes a file attachment.",
                uploadFile);

        Assert.assertTrue(successMessage.toLowerCase().contains("successfully"));
    }

    @Test
    public void signup_shouldUseRadioButtonsAndDropdownsToCreateTemporaryAccount() {
        String email = TestData.uniqueSignupEmail();
        LoginPage loginPage = new LoginPage(driver);

        AccountCreatedPage accountCreatedPage = loginPage
                .startSignup("Selenium Student", email)
                .createAccountWithRequiredDetails();

        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible());

        HeaderComponent header = accountCreatedPage.continueToLoggedInHeader();
        Assert.assertTrue(header.getLoggedInUserText().contains("Selenium Student"));

        AccountDeletedPage accountDeletedPage = header.deleteAccount();
        Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible());
    }

    private static final class PageExpectation {
        private final String path;
        private final String title;
        private final String visibleText;

        private PageExpectation(String path, String title, String visibleText) {
            this.path = path;
            this.title = title;
            this.visibleText = visibleText;
        }
    }
}
