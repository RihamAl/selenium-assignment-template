import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaticContentPage extends BasePage {
    public StaticContentPage(WebDriver driver) {
        super(driver);
    }

    public StaticContentPage open(String path) {
        driver.get(TestData.BASE_URL + path);
        acceptCookieConsentIfPresent();
        return this;
    }

    public boolean isTextVisible(String text) {
        return isElementDisplayed(By.xpath("//*[contains(normalize-space(), '" + text + "')]"));
    }
}
