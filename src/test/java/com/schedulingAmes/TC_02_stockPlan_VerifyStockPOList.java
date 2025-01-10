package com.schedulingAmes;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjectSchedulingAmes.*;

import java.util.ArrayList;
import java.util.List;

public class TC_02_stockPlan_VerifyStockPOList extends BaseTest {


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
    public void TC_01_Verify_Stock_PO_List() {
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

        stockPlanPage.isStockPOcreatedSuccessfully();

        log.info("TC_01_stockPlan- Step 8: Verify buttons after click filter");
       // verifyTrue(stockPlanPage.onlyExportAfterClickFilter(" Export CSV"));

        log.info("TC_01_stockPlan- Step 9: Verify sort list by time");
       // verifyTrue(stockPlanPage.isPOStockSortASCPlanStart("Planned Start Duration /Setup Time"));
       // verifyTrue(stockPlanPage.isPOStockSortASCPlanStart("Planned Start Duration /Setup Time"));

        log.info("TC_01_stockPlan- Step 10: Verify line no is - for PO status is Outputting");
        //stockPlanPage.isLineNoDisplayForOutputtingPO("Status Actual/Plan","L.No");

        log.info("TC_01_stockPlan- Step 11: click Modify");
      //  stockPlanPage.clickModifyButton();

        log.info("TC_01_stockPlan- Step 12: Verify Create PO buttons visible after Modify");
      //  verifyTrue(stockPlanPage.isCreatePOEnabled(" Create PO"));

        log.info("TC_01_stockPlan- Step 13: Verify Sequence buttons visible after Modify");
      //  verifyTrue(stockPlanPage.isSequence(" Sequence"));

        log.info("TC_01_stockPlan- Step 14: Verify Rearrange buttons visible after Modify");
      //  verifyTrue(stockPlanPage.isRearrange(" Rearrange"));

        log.info("TC_01_stockPlan- Step 15: Verify Replan buttons visible after Modify");
      //  verifyTrue(stockPlanPage.isReplan(" Replan"));

        log.info("TC_01_stockPlan- Step 16: Verify Bulk Release buttons visible after Modify");
      //  verifyTrue(stockPlanPage.isBulkRelease(" Bulk Release"));

        log.info("TC_01_stockPlan- Step 17: Verify Export CSV buttons visible after Modify");
      //  verifyTrue(stockPlanPage.onlyExportAfterClickFilter(" Export CSV"));
    }

}
