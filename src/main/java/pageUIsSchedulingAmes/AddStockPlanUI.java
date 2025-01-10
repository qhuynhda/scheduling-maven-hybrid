package pageUIsSchedulingAmes;

public class AddStockPlanUI {

    public static final String PRODUCT_LINE_PARENT = "xpath=//label[text()='Product Line']/following-sibling::div/input";

    public static final String PRODUCT_LINE_CHILDREN = "xpath=//label[text()='Product Line']/following-sibling::div/div/ul/li/span";


    public static final String MACHINE_CODE_PARENT = "xpath=//label[text()='Machine Code']/following-sibling::div/input";

    public static final String MACHINE_CODE_CHILDREN = "xpath=//label[text()='Machine Code']/following-sibling::div/div/ul/li/span";

    public static final String ARTICLE_CODE_PARENT= "xpath=//label[text()='Article Code']/following-sibling::div/input";
    public static final String ARTICLE_CODE_CHILDREN = "xpath=//label[text()='Article Code']/following-sibling::div/div/ul/li/span";

    public static final String PLANNED_QTY_PACKING_AMOUNT= "xpath=//label[text()='Planned Qty']/following-sibling::div";
    public static final String PLANNED_QTY_TEXTBOX = "xpath=//label[text()='Planned Qty']/following-sibling::input";

    public static final String BOM_VERSION_TEXTBOX = "xpath=//label[text()='BOM Version']/following-sibling::div/input";
    public static final String LINKED_ORDER_TEXTBOX = "xpath=//label[text()='Linked Order']/following-sibling::div/input";


    public static final String TBM_ORDER_PARENT= "xpath=//label[text()='TBM Order No']/following-sibling::div/input";
    public static final String TBM_ORDER_CHILDREN = "xpath=//label[text()='TBM Order No']/following-sibling::div/div/ul/li/span";
    public static final String PLANNED_START_DATE = "xpath=//div[text()='Planned Start Date And Time ']//input";
    public static final String PLANNED_END_DATE = "xpath=//div[text()='Planned End Date And Time ']//input";
    public static final String STATUS_PARENT= "xpath=//label[text()='Status']/following-sibling::div/input";
    public static final String STATUS_CHILDREN = "xpath=//label[text()='Status']/following-sibling::div/div/ul/li/span";
    public static final String COMMENT_TEXTBOX = "xpath=//label[text()='Comment']/following-sibling::div/textarea";
    public static final String SAVE_BUTTON = "xpath=//button[text()=' Save']";
    public static final String CANCEL_BUTTON = "xpath=//button[text()=' Cancel']";
    public static final String TOAST_MESSAGE="xpath=//div[@id='ToastPlug']//span";

    public static final String MESSAGE_LOCK_CURRENT_USER = "xpath=//div[@id='ToastPlug']//span";
    public static final String MESSAGE_SAVE_STOCK_SUCCESS = "xpath=//div[@id='ToastPlug']//span";

}
