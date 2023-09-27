package guru99.test;

import org.testng.annotations.Test;
import guru99.page.Login;
import guru99.page.Manage;
import untils.keywords.ExcelUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.fail;

public class LoginTest extends BaseTest {

    private Login objLogin;

    private ExcelUtils excelUtils;
    private Manage objManager;

    private List<List<String>> testData;

    public LoginTest() throws IOException {
        super();
        objLogin = new Login(this.webUI);
        excelUtils = new ExcelUtils("D:\\VTI\\Excel_Test_Data_Repository\\src\\main\\resources\\testdata\\LoginTest.xlsx");
        testData = excelUtils.getTestData("Sheet1");
    }

    @Test(description = "NC001: Should show error message at customer id text box after pressing the Tab key", groups = {
            "smoketest", "functiontest"
    })
    public void NC001_should_show_error_message_at_customer_id_text_box_after_press_Tab_key(Method method) {
        logger.info("Test case: " + method.getName());
        objLogin.inputUserId("");
        assertTrue(objLogin.shouldBeToShowUserIdErrorMessage(excelUtils.getColumnValueByIdAndColumnName("NC001", "Message_error")),
                MessageFormat.format("Should be to show User Id error message as ''{0}''",
                        excelUtils.getColumnValueByIdAndColumnName("NC001", "Message_error")));
    }

    @Test(description = "NC002: Login Successfully", groups = {
            "smoketest", "functiontest"})
    public void NC002_login_successfully(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objManager = objLogin.loginGuru99BankWith(excelUtils.getColumnValueByIdAndColumnName("NC002", "Input_userId"), excelUtils.getColumnValueByIdAndColumnName("NC002", "Input_password"));
        assertTrue(objManager.shouldBeToShowManagerIdAs(excelUtils.getColumnValueByIdAndColumnName("NC002", "managerId") + excelUtils.getColumnValueByIdAndColumnName("NC002", "Input_userId")),
                MessageFormat.format("Should be to show Manager Id as ''{0}{1}''",
                        excelUtils.getColumnValueByIdAndColumnName("NC002", "managerId"), excelUtils.getColumnValueByIdAndColumnName("NC002", "Input_userId")));
    }
}






