package commons;


import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest extends BasePage {
    private WebDriver driver;
    protected final Logger log;
    public BaseTest(){
        log = LogManager.getLogger(getClass());
    }
    public WebDriver getDriver() {
        return driver;
    }


    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        //equals: đúng giá trị- đúng hoa thường
        //egualsIgnoreCase: đúng giá trị không phân biệt hoa thường
        if (browser == BrowserList.FIREFOX) {
            driver = new FirefoxDriver();
        } else if (browser == BrowserList.CHROME) {
            driver = new ChromeDriver();
        } else if (browser == BrowserList.EDGE) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        openPageUrl(driver, "https://demo.nopcommerce.com/");
        return driver;
    }
    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        //equals: đúng giá trị- đúng hoa thường
        //egualsIgnoreCase: đúng giá trị không phân biệt hoa thường
        switch (browser){
            case FIREFOX:
                driver=new FirefoxDriver();
                break;
            case CHROME:
                driver=new ChromeDriver();
                break;
            case EDGE:
                driver=new EdgeDriver();
                break;
            case SAFARI:
                driver=new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get(url);
        return driver;
    }

    protected String getEmailAddress() {
        Random rand = new Random();
        return "kevinlap" + rand.nextInt(99999) + "@gmail.net";
    }

    protected void closeBrowser()  {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            //log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            //log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
           // log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info("------PASSED---------");
        } catch (Throwable e) {
            pass = false;
            log.info("------FAILED---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = false;
        try {
            Assert.assertFalse(condition);
            log.info("------PASSED---------");
        } catch (Throwable e) {
            pass = false;
            log.info("------FAILED---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("------PASSED---------");
        } catch (Throwable e) {
            pass = false;
            log.info("------FAILED---------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
    }
    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
