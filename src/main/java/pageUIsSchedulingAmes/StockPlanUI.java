package pageUIsSchedulingAmes;

public class StockPlanUI {

    public static final String PRODUCT_LINE_DROPDOWN_CHILDREN_LIST = "xpath=//label[text()='Product Line']/following-sibling::div/div/ul/li/span";


    public static final String PRODUCT_LINE_DROPDOWN_PARENT = "xpath=//label[text()='Product Line']/following-sibling::div/input";

    public static final String EQUIPMENT_GROUP_DROPDOWN_CHILDREN_LIST = "xpath=//label[contains(text(),'Equipment Groups')]/following-sibling::div//ul//li/label";

    public static final String EQUIPMENT_GROUP_DROPDOWN_PARENT = "xpath=//label[contains(text(),'Equipment Groups')]/following-sibling::div//button";
    public static final String MACHINE_CODE_DROPDOWN_CHILDREN_LIST = "xpath=//label[contains(text(),'Machine code')]/following-sibling::div//ul//li//label";

    public static final String MACHINE_CODE_DROPDOWN_PARENT = "xpath=//label[contains(text(),'Machine code')]/following-sibling::div/div";
    public static final String ARTICLE_CODE_DROPDOWN_CHILDREN_LIST = "xpath=//label[contains(text(),'Machine code')]/following-sibling::div//ul[@role='listbox']";
    public static final String ARTICLE_CODE_DROPDOWN_PARENT = "xpath=//label[contains(text(),'Article Code')]/following-sibling::div/div";
    public static final String FILTER_BUTTON = "xpath=//button[text()=' Modify']//parent::div//button[1]";
    public static final String MODIFY_BUTTON = "xpath=//button[text()=' Modify']";
    public static final String RELEASE_BUTTON = "xpath=//button[text()=' Release']";
    public static final String CREATE_PO_BUTTON = "xpath=//button[text()=' Create PO']";
    public static final String SEQUENCE_BUTTON = "xpath=//button[text()=' Sequence']";
    public static final String REARRANGE_BUTTON = "xpath=//button[text()=' Rearrange']";
    public static final String REPLAN_BUTTON = "xpath=//button[text()=' Replan']";
    public static final String BULK_RELEASE_BUTTON = "xpath=//button[text()=' Bulk Release']";
    public static final String EXPORT_BUTTON = "xpath=//button[text()=' Export CSV]";
    public static final String MESSAGE_LOCK_CURRENT_USER = "xpath=//div[@id='ToastPlug']//span";
    public static final String MESSAGE_SAVE_STOCK_SUCCESS = "xpath=//div[@id='ToastPlug']//span";


    //public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//div[text()='Planned Start Duration /Setup Time']/ancestor::th/preceding-sibling::th";
    public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//div[text()='%s']/ancestor::th/preceding-sibling::th";
    // public static final String COLUMN_INDEX_BY_COLUMN_NAME = "xpath=//div[text()='Status Actual/Plan']/ancestor::th/preceding-sibling::th";

    public static final String ALL_VALUE_BY_COLUMN_INDEX = "xpath=//tr/td[%s]/span/div[contains(text(), '-')]";

    public static final String ALL_VALUE_BY_COLUMN_INDEX_STATUS_ACTUAL_PLAN = "xpath=//tr/td[%s]//span[contains(@data-test, 'mm-po-status')]/i";


    public static final String ALL_ROW_ELEMEENT = "xpath=//table/tbody/tr";

    public static final String LINE_NO = "xpath=//td[%s]";
    public static final String BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public static final String BUTTON_SEL = "xpath=//button[text()='%s']";

    public static final String DELETE_STOCK_PO_BUTTON_BY_TEXT = "xpath=//div[text()=\"You\\'re deleting a PO. Would you like to continue?\"]//following-sibling::div//button[text()=\" %s\"]";

    public static final String TABLE_BODY = "xpath=//span[contains(text(), 'Stop Order')]/ancestor::div/following-sibling::div//table/tbody";

    public static final String TABLE_BODY_CSS = "css=div#grid-table-79969 div[role='complementary'] div.gridjs-wrapper";


}
