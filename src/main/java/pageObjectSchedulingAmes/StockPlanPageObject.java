package pageObjectSchedulingAmes;

import commons.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIsSchedulingAmes.StockPlanUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockPlanPageObject extends BasePage {
    WebDriver driver;

    public StockPlanPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void selectProductLineDropdown(String productLine) {
        waitForElementVisible(driver, StockPlanUI.PRODUCT_LINE_DROPDOWN_PARENT);
        selectItemInDropdown(driver, StockPlanUI.PRODUCT_LINE_DROPDOWN_PARENT, StockPlanUI.PRODUCT_LINE_DROPDOWN_CHILDREN_LIST, productLine);
    }

    public void selectMachineDropDown(String machineCode) {
        waitForElementVisible(driver, StockPlanUI.MACHINE_CODE_DROPDOWN_PARENT);
        selectItemInDropdown(driver, StockPlanUI.MACHINE_CODE_DROPDOWN_PARENT, StockPlanUI.MACHINE_CODE_DROPDOWN_CHILDREN_LIST, machineCode);
    }

    public void enterArticle(String article) {
        waitForElementVisible(driver, StockPlanUI.ARTICLE_CODE_DROPDOWN_PARENT);
        selectItemInDropdown(driver, StockPlanUI.ARTICLE_CODE_DROPDOWN_PARENT, StockPlanUI.ARTICLE_CODE_DROPDOWN_CHILDREN_LIST, article);
    }

    public void clickFilter() {
        waitForElementVisible(driver, StockPlanUI.FILTER_BUTTON);
        clickToElement(driver, StockPlanUI.FILTER_BUTTON);
    }

    public void selectEquipmentGroupDropDown(String pcrstock) {
        waitForElementVisible(driver, StockPlanUI.EQUIPMENT_GROUP_DROPDOWN_PARENT);
        selectItemInDropdown(driver, StockPlanUI.EQUIPMENT_GROUP_DROPDOWN_PARENT, StockPlanUI.EQUIPMENT_GROUP_DROPDOWN_CHILDREN_LIST, pcrstock);
    }

    public void clickModifyButton() {
        waitForElementVisible(driver, StockPlanUI.MODIFY_BUTTON);
        clickToElement(driver, StockPlanUI.MODIFY_BUTTON);
    }

    public void clickCreateNewStockPlanButton() {
        waitForElementVisible(driver, StockPlanUI.CREATE_PO_BUTTON);
        clickToElement(driver, StockPlanUI.CREATE_PO_BUTTON);
    }

    public boolean isLockCurrentSuccessDisplay() {
        waitForElementVisible(driver, StockPlanUI.MESSAGE_LOCK_CURRENT_USER);
        return isElementDisplayed(driver, StockPlanUI.MESSAGE_LOCK_CURRENT_USER);
    }


    public boolean isSaveStockSuccessfully() {
        waitForElementVisible(driver, StockPlanUI.MESSAGE_SAVE_STOCK_SUCCESS);
        return isElementDisplayed(driver, StockPlanUI.MESSAGE_SAVE_STOCK_SUCCESS);
    }

    public void enterToTextboxbyIndex(String rownIndex, String columnName, String valueToSendKey) {

    }


    public String getValuesByColumnName(String columnName) {
        List<String> allValues = new ArrayList<String>();

        int columnIndex = getListElementSize(driver, StockPlanUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        List<WebElement> allRowValues = getListWebElement(driver, StockPlanUI.ALL_VALUE_BY_COLUMN_INDEX, String.valueOf(columnIndex));

        for (WebElement rowValue : allRowValues) {
            try {

                allValues.add(rowValue.getText().trim());
            } catch (NoSuchElementException e) {
                System.out.println("No valid value found for this row.");
            }
        }
        // }
        if (allValues.isEmpty()) {
            System.out.println("No values found in the column.");
            return null; // Return null if no values are found
        }
        String lastValue = allValues.get(allValues.size() - 1);
        System.out.println("Last value: " + lastValue);

        return lastValue; // Return the last value
    }

    public String calculatePlanEndDate(String valuesByColumnName, int planQty, float cycleTime, int performance) throws ParseException {

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM HH:mm");
        Date parsedDate = inputFormat.parse(valuesByColumnName);

        // Step 2: Reformat the date to "dd/MM/yyyy HH:mm"
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = outputFormat.format(parsedDate);
        System.out.println("Formatted date: " + formattedDate);
        System.out.println("planQty: " + planQty + ", cycleTime: " + cycleTime + ",Performance" + performance);
        float minutesToProduce = (planQty * cycleTime * 100) / performance;
        int roundedMinutesToProduce = (int) Math.ceil(minutesToProduce);

        // Calculate the whole minutes and the fractional part (seconds)
        int wholeMinutes = (int) roundedMinutesToProduce;
        int remainingSeconds = Math.round((roundedMinutesToProduce - wholeMinutes) * 60);
        System.out.println("Minutes to Produce: " + wholeMinutes + ", Remaining Seconds: " + remainingSeconds);

        // Step 4: Add the calculated minutes to the parsed date
        Calendar currentYearCalendar = Calendar.getInstance();
        int currentYear = currentYearCalendar.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedDate);
        calendar.set(Calendar.YEAR, currentYear); // Set the current year
        calendar.add(Calendar.MINUTE, wholeMinutes); // Add minutes
        calendar.add(Calendar.SECOND, remainingSeconds);
        // Step 5: Format the end date
        String endDate = outputFormat.format(calendar.getTime());
        System.out.println("End Date: " + endDate);


        return endDate;
    }

    public boolean isPOStockSortASCPlanStart(String columnName) {
        List<String> formattedValues = new ArrayList<>();
        List<String> sortedFormattedValues = new ArrayList<>();

        int columnIndex = getListElementSize(driver, StockPlanUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;

        List<WebElement> allRowValues = getListWebElement(driver, StockPlanUI.ALL_VALUE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM HH:mm"); // Adjust the date format if needed
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM HH:mm"); // For formatting output


        for (WebElement rowValue : allRowValues) {
            try {
                String planStartText = rowValue.getText().trim();
                if (!planStartText.isEmpty()) {
                    Date planStartDate = dateFormat.parse(planStartText);
                    /*allValues.add(planStartDate);
                    System.out.println(rowValue.getText());*/
                    String formattedDate = outputDateFormat.format(planStartDate);
                    formattedValues.add(formattedDate);
                    System.out.println(formattedDate);
                }
            } catch (NoSuchElementException e) {
                System.out.println("No valid value found for this row.");
            } catch (Exception e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }
        }
        // }
        /*List<Date> poPlanDateClone = new ArrayList<>(allValues);
       // System.out.println("POPlan:"+ poPlanDateClone);
        Collections.sort(poPlanDateClone);*/
        sortedFormattedValues.addAll(formattedValues);
        Collections.sort(sortedFormattedValues);
        System.out.println("POPlan:"+ sortedFormattedValues);
        return sortedFormattedValues.equals(formattedValues);

    }

    public void isLineNoDisplayForOutputtingPO(String columnName, String lineNoColumnName) {
        List<Date> allValues = new ArrayList<Date>();

        int statusColumnIndex = getListElementSize(driver, StockPlanUI.COLUMN_INDEX_BY_COLUMN_NAME, columnName) + 1;
        int lineNoColumnIndex = getListElementSize(driver, StockPlanUI.COLUMN_INDEX_BY_COLUMN_NAME, lineNoColumnName) + 1;

        System.out.println("Status Column Index: " + statusColumnIndex);
        System.out.println("L.No Column Index: " + lineNoColumnIndex);

        // List<WebElement> allColumnValues = getListWebElement(driver, StockPlanUI.ALL_VALUE_BY_COLUMN_INDEX_STATUS_ACTUAL_PLAN, String.valueOf(columnIndex));
        System.out.println("Values in the column:");
        // for (WebElement columnValue : allColumnValues) {
        //     System.out.println(columnValue.getText()); // Print actual text
        //  }
        // int lineNoColumnIndex = 1;
        // Get all rows in the table body
        List<WebElement> allRows = getListWebElement(driver, StockPlanUI.ALL_ROW_ELEMEENT);
        System.out.println("Total rows found: " + allRows.size());
        // for (int i = 0; i<allRows.size(); i++) {
       for (WebElement row : allRows) {
            try {
                WebElement statusElement = getWebElement(driver, StockPlanUI.LINE_NO, String.valueOf(statusColumnIndex));

                String statusText = statusElement.getText().trim();
                if (statusText.equalsIgnoreCase("OUTPUTTING") || statusText.equalsIgnoreCase("DOWNLOADED")) {
                    System.out.println("status" + statusText);
                    //  System.out.println(i);
                    WebElement lineNoElement = getWebElement(driver, StockPlanUI.LINE_NO, String.valueOf(lineNoColumnIndex));
                    //System.out.println("L.No for " + statusText + ": " + lineNoElement.getText().trim());
                    String lineNo = lineNoElement.getText().trim();
                    //System.out.println(row.getText());
                    System.out.println("Status: " + statusText + " | L.No: " + lineNo);

                }
            } catch (Exception e) {
                System.out.println("Error processing row: " + e.getMessage());
            }
        }

        //}
    }

    public boolean onlyExportAfterClickFilter(String buttonText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,buttonText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,buttonText);
    }

    public boolean isCreatePOEnabled(String buttonText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,buttonText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,buttonText);

    }

    public boolean isSequence(String sequenceText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,sequenceText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,sequenceText);
    }

    public boolean isRearrange(String rearrangeText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,rearrangeText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,rearrangeText);
    }

    public boolean isReplan(String replanText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,replanText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,replanText);
    }

    public boolean isBulkRelease(String bulkReleaseText) {
        waitForElementClickable(driver, StockPlanUI.BUTTON_BY_TEXT,bulkReleaseText);
        return isElementDisplayed(driver, StockPlanUI.BUTTON_BY_TEXT,bulkReleaseText);
    }

    public void selectStockPO() {
    }

    public void clickOnDeleteButton() {
    }

    public void verifyDeletePopup() {
    }

    public void clickOnCancelButtonOnPopup() {
    }

    public void clickOnYesButtonOnPopup() {
    }

    public void verifyStockPONotShowOnList() {
    }

    public void isStockPOcreatedSuccessfully() {
        List<String> allValues = new ArrayList<>();
        // Get all rows in the table body
        List<WebElement> allRows = getListWebElement(driver, StockPlanUI.ALL_ROW_ELEMEENT);
        System.out.println("Total rows found: " + allRows.size());


        waitForElementVisible(driver,StockPlanUI.TABLE_BODY_CSS);
        // Locate the scrollable element
       // WebElement scrollableElement = driver.findElement(By.cssSelector("div.gridjs-wrapper > table.gridjs-table[css='1']"));
        sleepInSeconds(5);
        scrollVertically(driver, getWebElement(driver, StockPlanUI.TABLE_BODY_CSS), 500);

        sleepInSeconds(5);

        for(WebElement row:allRows)
        {   String rowText=row.getText();
            allValues.add(rowText);
        }

        String lastRowData = allValues.get(allValues.size()-1);
        System.out.println("last row data:"+ lastRowData);

    }
}


