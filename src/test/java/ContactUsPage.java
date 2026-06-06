import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {
    private static final By CONTACT_HEADING = By.xpath("//h2[contains(normalize-space(), 'Get In Touch')]");
    private static final By NAME_INPUT = By.xpath("//input[@name='name']");
    private static final By EMAIL_INPUT = By.xpath("//input[@name='email']");
    private static final By SUBJECT_INPUT = By.xpath("//input[@name='subject']");
    private static final By MESSAGE_TEXTAREA = By.xpath("//textarea[@name='message']");
    private static final By ATTACHMENT_INPUT = By.xpath("//input[@type='file' and @name='upload_file']");
    private static final By SUBMIT_BUTTON = By.xpath("//input[@data-qa='submit-button' and @value='Submit']");
    private static final By SUCCESS_MESSAGE = By.xpath("//*[contains(normalize-space(.), 'Success! Your details have been submitted successfully.')]");

    public ContactUsPage(WebDriver driver) {
        this(driver, true);
    }

    ContactUsPage(WebDriver driver, boolean openPage) {
        super(driver);
        if (openPage) {
            driver.get(TestData.BASE_URL + "/contact_us");
            acceptCookieConsentIfPresent();
        }
        waitForElementVisible(CONTACT_HEADING);
    }

    public String submitContactForm(String name, String email, String subject, String message) {
        typeText(NAME_INPUT, name);
        typeText(EMAIL_INPUT, email);
        typeText(SUBJECT_INPUT, subject);
        typeText(MESSAGE_TEXTAREA, message);
        click(SUBMIT_BUTTON);
        acceptAlertIfPresent();
        return getText(SUCCESS_MESSAGE);
    }

    public String submitContactFormWithAttachment(
            String name,
            String email,
            String subject,
            String message,
            Path attachment) {
        typeText(NAME_INPUT, name);
        typeText(EMAIL_INPUT, email);
        typeText(SUBJECT_INPUT, subject);
        typeText(MESSAGE_TEXTAREA, message);
        uploadFile(ATTACHMENT_INPUT, attachment);
        click(SUBMIT_BUTTON);
        acceptAlertIfPresent();
        return getText(SUCCESS_MESSAGE);
    }
}
