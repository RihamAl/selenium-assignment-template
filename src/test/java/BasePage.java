import java.time.Duration;
import java.nio.file.Path;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final By[] COOKIE_CONSENT_BUTTONS = {
            By.xpath("//button[normalize-space()='Consent' or .//p[normalize-space()='Consent']]"),
            By.xpath("//button[contains(normalize-space(), 'Accept') or contains(normalize-space(), 'Agree')]"),
            By.xpath("//button[.//*[contains(normalize-space(), 'Accept') or contains(normalize-space(), 'Agree')]]")
    };

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        WebElement element = waitForElementClickable(locator);
        scrollToElement(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException exception) {
            acceptCookieConsentIfPresent();
            element = waitForElementClickable(locator);
            scrollToElement(element);
            element.click();
        }
    }

    protected void typeText(By locator, String text) {
        WebElement element = waitForElementClickable(locator);
        scrollToElement(element);
        try {
            element.clear();
            element.sendKeys(text);
        } catch (ElementNotInteractableException exception) {
            acceptCookieConsentIfPresent();
            element = waitForElementClickable(locator);
            scrollToElement(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void uploadFile(By locator, Path filePath) {
        WebElement element = waitForElementVisible(locator);
        element.sendKeys(filePath.toAbsolutePath().toString());
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        WebElement element = waitForElementClickable(locator);
        scrollToElement(element);
        new Select(element).selectByVisibleText(visibleText);
    }

    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.stream().anyMatch(WebElement::isDisplayed);
    }

    protected void acceptCookieConsentIfPresent() {
        for (By locator : COOKIE_CONSENT_BUTTONS) {
            List<WebElement> consentButtons = driver.findElements(locator);
            for (WebElement consentButton : consentButtons) {
                if (consentButton.isDisplayed() && consentButton.isEnabled()) {
                    scrollToElement(consentButton);
                    consentButton.click();
                    return;
                }
            }
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected String acceptAlertAndGetText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    protected boolean acceptAlertIfPresent() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleIs(title));
    }

    public void waitForUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void waitForUrlContaining(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public void waitForUrlMatching(String regex) {
        wait.until(ExpectedConditions.urlMatches(regex));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
