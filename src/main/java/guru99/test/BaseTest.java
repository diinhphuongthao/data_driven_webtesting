package guru99.test;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.testng.annotations.*;
import guru99.page.Login;
import untils.keywords.WebUI;
import untils.helper.LogHelper;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected static Logger logger = LogHelper.getLogger();
    private static String DEMO_GURU99_URL = "https://demo.guru99.com/v4/";
    protected final WebUI webUI;


    protected Login objLogin;

    public BaseTest() {
        webUI = new WebUI();

    }

    @BeforeClass
    public void beforeClass() {
        logger.info("=====================Before Class: " + LoginTest.class.getName());
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(String browser) throws IOException {
        objLogin = goToLoginPage(browser);

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("==================Before Method: " + method.getName());
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
        logger.info("=====================After Class: " + LoginTest.class.getName());
    }


    @Step("Go to Login page")
    public Login goToLoginPage(String browser) throws IOException {
        webUI.openBrowser(browser, DEMO_GURU99_URL);
        webUI.maximizeWindow();
        return new Login(this.webUI);
    }

}
