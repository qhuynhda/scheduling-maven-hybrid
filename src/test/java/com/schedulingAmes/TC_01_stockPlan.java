package com.schedulingAmes;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectSchedulingAmes.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TC_01_stockPlan extends BaseTest {

    private WebDriver driver;
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private StockPlanPageObject stockPlanPage;
    private AddStockPlanPageObject addStockPlanPage;
    private String userName, password;

    List<String> allValuePlanStartUI = new ArrayList<String>();
    private int routingQty = 79680;
    private int machineHours = 4800;
    private float cycleTime = (float) machineHours / routingQty;
    private int performance = 90;
    private int planQty = 100;


    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {


        driver = getBrowserDriver(browserName, url);

        userName = "ames-admin";
        password = "P@ssw0rd01";
        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void TC_01_Create_New_Stock_Plan() throws ParseException {
        log.info("TC_01_stockPlan- Step 1: Click on main menu");
        homePage.clickMenu();

        log.info("TC_01_stockPlan- Step 2: Click on Stock Plan menu");
        homePage.clickStockPlanMenu();

        log.info("TC_01_stockPlan- Step 3: Click on Stock Plan Child Menu");
        homePage.clickStockPlanChildMenu();
        stockPlanPage = PageGeneratorManager.getStockPlanPage(driver);

        log.info("TC_01_stockPlan- Step 4: Select Product Line Dropdown");
        stockPlanPage.selectProductLineDropdown("PCR");

        log.info("TC_01_stockPlan- Step 5: Select Eguipment Group");
        stockPlanPage.selectEquipmentGroupDropDown("PCRSTOCK");

        log.info("TC_01_stockPlan- Step 6: Select Machine");
        stockPlanPage.selectMachineDropDown("Belt cutter");

        log.info("TC_01_stockPlan- Step 7: Click filter");
        stockPlanPage.clickFilter();

        log.info("TC_01_stockPlan- Step 8: Get the last Stock PO");
        // stockPlanPage.getValuesByColumnName("Planned Start Duration /Setup Time");
        System.out.println("get start plan:" + stockPlanPage.getValuesByColumnName("Planned Start Duration /Setup Time"));
        String endDateLastPOStock = stockPlanPage.calculatePlanEndDate(stockPlanPage.getValuesByColumnName("Planned Start Duration /Setup Time"), planQty, cycleTime, performance);


        log.info("TC_01_stockPlan- Step 9: Click Modify");
        stockPlanPage.clickModifyButton();

        log.info("TC_01_stockPlan- Step 10: Verify lock user");
        verifyTrue(stockPlanPage.isLockCurrentSuccessDisplay());
        //verifyFalse(stockPlanPage.isLockCurrentSuccessDisplay("Lock current success"));


        log.info("TC_01_stockPlan- Step 11: Click New Stock Plan");
        stockPlanPage.clickCreateNewStockPlanButton();

        addStockPlanPage = PageGeneratorManager.getAddStockPlanPage(driver);
        sleepInSeconds(1);

        log.info("TC_01_stockPlan- Step 12: Select dropdown product line");
        addStockPlanPage.selectProductLineDropdownAdd("PCR");

        log.info("TC_01_stockPlan- Step 13: Select Machine");
        addStockPlanPage.selectMachineDropDownAdd("Belt cutter");
        sleepInSeconds(2);
        log.info("TC_01_stockPlan- Step 14: Select Article");
        addStockPlanPage.selectArticleDropDownAdd("BLT0042A");

        log.info("TC_01_stockPlan- Step 15: Enter quantity");
        addStockPlanPage.enterQty("100");


        //mAssert.assertEquals();

        log.info("TC_01_stockPlan- Step 16: Select TBM Order Number");
        addStockPlanPage.selectTBMOrderNumber("PCR99");

        log.info("TC_01_stockPlan- Step 17: Select status");
        addStockPlanPage.selectStatus("Planned");

        log.info("TC_01_stockPlan- Step 18: Enter Text comment");
        addStockPlanPage.enterComment("text comment");

        log.info("TC_01_stockPlan- Step19: Verify Start and End date");

        verifyEquals(addStockPlanPage.getPlanEndFromUI(), endDateLastPOStock);


        log.info("TC_01_stockPlan- Step 20: Click Save");
        addStockPlanPage.clickSavebutton();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        verifyTrue(stockPlanPage.isSaveStockSuccessfully());

        log.info("TC_01_stockPlan- Step 22: Verify the new stock PO is created");
        stockPlanPage.isStockPOcreatedSuccessfully();

        log.info("TC_01_stockPlan- Step 22: Verify sort list by time");
        //stockPlanPage.isPOStockSortASCPlanStart("Planned Start Duration /Setup Time");
        //verifyTrue(stockPlanPage.isPOStockSortASCPlanStart("Planned Start Duration /Setup Time"));

        log.info("TC_01_stockPlan- Step 23: Verify line no is - for PO status is Outputting");
        //stockPlanPage.getLineNoDisplayForOutputtingPO("Status Actual/Plan","L.No");

    }
    @Test
    public void TC_02_Delete_StockPO(){

        log.info("TC_01_stockPlan- Step 01: Select Stock PO");
        stockPlanPage.selectStockPO();

        log.info("TC_01_stockPlan- Step 02: Verify Stock save success");
        stockPlanPage.clickOnDeleteButton();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        stockPlanPage.verifyDeletePopup();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        stockPlanPage.clickOnCancelButtonOnPopup();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        stockPlanPage.clickOnDeleteButton();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        stockPlanPage.clickOnYesButtonOnPopup();

        log.info("TC_01_stockPlan- Step 21: Verify Stock save success");
        stockPlanPage.verifyStockPONotShowOnList();


    }
}
