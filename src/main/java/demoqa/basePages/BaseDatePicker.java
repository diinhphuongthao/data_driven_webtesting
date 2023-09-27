package demoqa.basePages;

import demoqa.test.DateSpickerTest;
import guru99.test.LoginTest;
import org.slf4j.Logger;
import org.testng.annotations.*;
import untils.helper.LogHelper;
import untils.keywords.WebUI;


import java.io.File;
import java.lang.reflect.Method;

public class BaseDatePicker {
    protected static Logger logger = LogHelper.getLogger();
    private static String DATE_SPICKER_URL = "https://demoqa.com/date-picker";
    protected final WebUI webUI;

    public BaseDatePicker() {
        webUI = new WebUI();
    }

    @BeforeClass
    public void beforeClass() {
        logger.info("=====================Before Class: " + DateSpickerTest.class.getName());
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(String browser) {
        webUI.openBrowser(browser, DATE_SPICKER_URL);
        String allureResultsDirPath = "D:\\VTI\\Assignment_Automation_Framework\\allure-results";
        File allureResultsDir = new File(allureResultsDirPath);
        if (allureResultsDir.exists() && allureResultsDir.isDirectory()) {
            deleteDirectory(allureResultsDir);
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("==================Before Method: " + method.getName());
    }

    private static void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }

    @AfterMethod
    public void afterMethod(Method method) {
        logger.info("==================After Method: " + method.getName());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        webUI.closeBrowser();
    }

    @AfterClass
    public void afterClass() {
        logger.info("=====================After Class: " + DateSpickerTest.class.getName());
    }

}
