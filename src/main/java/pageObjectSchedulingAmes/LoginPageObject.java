package pageObjectSchedulingAmes;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIsSchedulingAmes.LoginSchedulingAmesUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void enterUserName(String userName) {
        waitForElementVisible(driver, LoginSchedulingAmesUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginSchedulingAmesUI.USERNAME_TEXTBOX, userName);
    }

    public void enterPassword(String password) {
        waitForElementVisible(driver, LoginSchedulingAmesUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginSchedulingAmesUI.PASSWORD_TEXTBOX, password);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, LoginSchedulingAmesUI.LOGIN_BUTTON);
        clickToElement(driver, LoginSchedulingAmesUI.LOGIN_BUTTON);
    }
}
