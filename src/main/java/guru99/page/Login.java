package guru99.page;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import guru99.repo.LoginRepo;
import untils.keywords.WebUI;

import java.io.IOException;

public class Login extends BasePage {

    public Login(WebUI webUI) throws IOException {
        super(webUI);

    }
    @Step("Input user id: {0}")
    public void inputUserId(String userId) {
        if (userId.isBlank()) {
            webUI.sendKeys(LoginRepo.TXT_USER_ID, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(LoginRepo.TXT_USER_ID, userId);
        }
        webUI.takeScreenShotAndHighLightElement(LoginRepo.TXT_USER_ID);
    }

    @Step("Should be to show customer id error message as '{0}'")
    public boolean shouldBeToShowUserIdErrorMessage(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(LoginRepo.LBL_USER_ID_ERROR_MESSAGE);
        if (webUI.verifyElementText(LoginRepo.LBL_USER_ID_ERROR_MESSAGE, errorMessage)) {
            webUI.takeScreenShot();
            return true;
        }
        return false;
    }
    @Step("Input user password: {0}")
    public void inputUserPassword(String userPassword) {
        if(userPassword.isBlank()) {
            webUI.sendKeys(LoginRepo.TXT_USER_PASSWORD, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(LoginRepo.TXT_USER_PASSWORD, userPassword);
        }
        webUI.takeScreenShotAndHighLightElement(LoginRepo.TXT_USER_PASSWORD);
    }
    @Step("Click Login button")
    public void clickLoginButton() {
        webUI.takeScreenShotAndHighLightElement(LoginRepo.BTN_LOGIN);
        webUI.clickJS(LoginRepo.BTN_LOGIN);
        webUI.delayInSecond(3);
        webUI.takeScreenShot();
    }

    @Step("Login Guru 99 Bank with user id '{0}' and password '{1}'")
    public Manage loginGuru99BankWith(String userId, String userPassword) throws IOException{
        inputUserId(userId);
        inputUserPassword(userPassword);
        clickLoginButton();
        return new Manage(this.webUI);
    }


}