package demoqa.basePages;

import demoqa.test.ToolTipTest;
import org.slf4j.Logger;
import org.testng.annotations.*;
import untils.helper.LogHelper;
import untils.keywords.WebUI;


import java.io.File;
import java.lang.reflect.Method;

public class BaseToolTip {
    protected static Logger logger = LogHelper.getLogger();
    private static String DEMO_QA_URL = "https://demoqa.com/tool-tips";
    protected final WebUI webUI;

    public BaseToolTip() {
        webUI = new WebUI();
    }

    @BeforeClass
    public void beforeClass() {
        logger.info("=====================Before Class: " + ToolTipTest.class.getName());
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(String browser) {
        webUI.openBrowser(browser, DEMO_QA_URL);
        String allureResultsDirPath = "D:\\VTI\\Assignment_Automation_Framework\\target\\allure-results";
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
        logger.info("=====================After Class: " + ToolTipTest.class.getName());
    }


}
