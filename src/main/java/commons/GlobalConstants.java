package commons;

import java.io.File;

public class GlobalConstants {
    //server URL: dev/Testing/Staging
    //Database: Ip/port
    //timeout: short/long
    //username/pass
    //third party: sandbox paypal
    //download/upload folder
    //Relative Project Path
    //OS Name
    //Cloud Testing: Browserstack/SauceLab/Crossbrowser
    public static final String DEV_USER_URL = "https://demo.nopcommerce.com";
    public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com";

    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final String DEV_ADMIN_USERNAME = "admin@yourstore.com";
    public static final String DEV_ADMIN_PASSWORD = "admin";

    public static final String ADMIN_ORANGE_HRM_USERNAME="automationfc";
    public static final String ADMIN_ORANGE_HRM_PASSWORD="Automation@123";


    public static final String OS_NAME = System.getProperty("os.name");
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
    public static final String REPORTING_IMAGE_PATH = RELATIVE_PROJECT_PATH + File.separator + "reportNGSImage" + File.separator;
    public static final String RESOURCE_PATH = RELATIVE_PROJECT_PATH + File.separator + "resources" + File.separator;

}
