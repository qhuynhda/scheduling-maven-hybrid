package pageObjectSchedulingAmes;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIsSchedulingAmes.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMenu() {
        waitForElementClickable(driver, HomePageUI.MENU_BUTTON);
        clickToElement(driver, HomePageUI.MENU_BUTTON);
    }

    public void clickStockPlanMenu() {
        waitForElementClickable(driver, HomePageUI.STOCK_PLAN_PARENT);
        clickToElement(driver, HomePageUI.STOCK_PLAN_PARENT);
    }

    public void clickStockPlanChildMenu() {
        waitForElementClickable(driver, HomePageUI.STOCK_PLAN_CHILDREN);
        clickToElement(driver, HomePageUI.STOCK_PLAN_CHILDREN);
    }
}
