package pageObjectSchedulingAmes;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIsSchedulingAmes.AddStockPlanUI;

public class AddStockPlanPageObject extends BasePage {
    WebDriver driver;

    public AddStockPlanPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProductLineDropdownAdd(String productLineAdd) {
        waitForElementVisible(driver, AddStockPlanUI.PRODUCT_LINE_PARENT);
        selectItemInEditableDropDown(driver,AddStockPlanUI.PRODUCT_LINE_PARENT,AddStockPlanUI.PRODUCT_LINE_CHILDREN,productLineAdd);
    }
    public void selectMachineDropDownAdd(String machineCode) {
        waitForElementVisible(driver,AddStockPlanUI.MACHINE_CODE_PARENT);
        selectItemInDropdown(driver,AddStockPlanUI.MACHINE_CODE_PARENT,AddStockPlanUI.MACHINE_CODE_CHILDREN,machineCode);
    }

    public void selectArticleDropDownAdd(String articleAdd) {
        waitForElementVisible(driver,AddStockPlanUI.ARTICLE_CODE_PARENT);
        selectItemInEditableDropDown(driver,AddStockPlanUI.ARTICLE_CODE_PARENT,AddStockPlanUI.ARTICLE_CODE_CHILDREN,articleAdd);
    }


    public void enterQty(String plannedQty) {

        waitForElementVisible(driver, AddStockPlanUI.PLANNED_QTY_PACKING_AMOUNT);
        waitForElementClickable(driver, AddStockPlanUI.PLANNED_QTY_TEXTBOX);
        sendkeyToElement(driver,AddStockPlanUI.PLANNED_QTY_TEXTBOX,plannedQty);
    }

    public void selectTBMOrderNumber(String pcr99) {
        waitForElementVisible(driver, AddStockPlanUI.TBM_ORDER_PARENT);
        selectItemInDropdown(driver,AddStockPlanUI.TBM_ORDER_PARENT,AddStockPlanUI.TBM_ORDER_CHILDREN,pcr99);
    }

    public void selectStatus(String statusPOAdd) {
        waitForElementVisible(driver, AddStockPlanUI.STATUS_PARENT);
        selectItemInDropdown(driver,AddStockPlanUI.STATUS_PARENT,AddStockPlanUI.STATUS_CHILDREN,statusPOAdd);
    }

    public void enterComment(String commnent) {
        waitForElementVisible(driver, AddStockPlanUI.COMMENT_TEXTBOX);
        sendkeyToElement(driver,AddStockPlanUI.COMMENT_TEXTBOX,commnent);
    }

    public void clickSavebutton() {
        waitForElementClickable(driver,AddStockPlanUI.SAVE_BUTTON);
        clickToElement(driver,AddStockPlanUI.SAVE_BUTTON);
    }


    public String getPlanEndFromUI() {
        waitForElementVisible(driver, AddStockPlanUI.PLANNED_START_DATE);

        return getElementAtribute(driver,AddStockPlanUI.PLANNED_START_DATE,"value");
    }
}
