package pageObjectSchedulingAmes;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static StockPlanPageObject getStockPlanPage(WebDriver driver){
        return new StockPlanPageObject(driver);
    }

    public static AddStockPlanPageObject getAddStockPlanPage(WebDriver driver) {
        return new AddStockPlanPageObject(driver);
    }
}