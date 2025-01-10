package commons;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePageFactory {
    protected void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void refreshCurrent(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptToAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void cancelToAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    protected String getTextInAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    protected void sendkeyToAlert(WebDriver driver, String keysToSend) {
        driver.switchTo().alert().sendKeys(keysToSend);
    }

    protected void switchToWindowByID(WebDriver driver, String expectedID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(expectedTitle)) {
                driver.switchTo().window(id);
                String actualTitle = driver.getTitle();
                if (actualTitle.equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    protected void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    protected void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    protected Set<Cookie> getBrowserCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    protected void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }
    protected void clickToElement(WebDriver driver, WebElement element){
        element.click();
    }
    protected String sendKeyToElement(WebDriver driver, WebElement element, String value){
        return element.getText();
    }
    protected void waitForElementVisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
    }
    protected void waitForElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
    }
    protected String getElementAttribute(WebDriver driver, WebElement element,String attributeName)
    {
        return element.getAttribute(attributeName);
    }
    protected String getElementText(WebDriver driver, WebElement element)
    {
        return element.getText();
    }
}
