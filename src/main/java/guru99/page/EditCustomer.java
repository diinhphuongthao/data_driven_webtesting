package guru99.page;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import guru99.repo.EditCustomerRepo;
import untils.keywords.WebUI;

import java.io.IOException;


public class EditCustomer extends BasePage {

    public EditCustomer(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Should be to show customer id error message as '{0}'")
    public Boolean shouldBeToShowCustomerIdErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_CUSTOMER_ID_ERROR_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_CUSTOMER_ID_ERROR_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_CUSTOMER_ID_ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input customer id to edit: {0}")
    public Customer inputCustomerId(String customerid) throws IOException {
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_CUSTOMER_ID_T0_EDIT, Keys.chord(Keys.TAB));
        } else {
            webUI.clearText(EditCustomerRepo.LBL_CUSTOMER_ID_T0_EDIT);
            webUI.delayInSecond(1);
            webUI.sendKeys(EditCustomerRepo.LBL_CUSTOMER_ID_T0_EDIT, customerid);
        }
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_CUSTOMER_ID_T0_EDIT);

        return new Customer(this.webUI);
    }

    @Step("Should be to show address error message as '{0}'")
    public Boolean shouldBeToShowAdressErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_ADDRESS_ERROR);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_ADDRESS_ERROR, errorMessage)) {
            webUI.delayInSecond(2);

            return true;
        }
        return false;
    }

    public void clearTextarea(String textareaId) {
        WebElement textarea = webUI.findElement(EditCustomerRepo.LBL_ADDRESS);
        textarea.click();
        while (!textarea.getAttribute("value").isEmpty()) {
            textarea.sendKeys(Keys.ARROW_LEFT, Keys.BACK_SPACE);
        }
        webUI.takeScreenShotAndHighLightElement(textareaId);
    }
    @Step("input address to edit: {0}")
    public Customer inputAddress(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_ADDRESS);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_ADDRESS, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(EditCustomerRepo.LBL_ADDRESS, customerid);
        }
        return new Customer(this.webUI);
    }

    @Step("Should be to show city error message as '{0}'")
    public Boolean shouldBeToShowCityErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_CITY_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_CITY_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_CITY_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input city to edit: {0}")
    public Customer inputCity(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_CITY);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_CITY, Keys.chord(Keys.TAB));
        } else {
            webUI.sendKeys(EditCustomerRepo.LBL_CITY, customerid);
        }
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_CITY);

        return new Customer(this.webUI);
    }

    @Step("Should be to show state error message as '{0}'")
    public Boolean shouldBeToShowStateErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_STATE_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_STATE_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_STATE_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input state to edit: {0}")
    public Customer inputState(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_STATE);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_STATE, Keys.chord(Keys.TAB));
        } else {

            webUI.sendKeys(EditCustomerRepo.LBL_STATE, customerid);
        }
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_STATE);

        return new Customer(this.webUI);
    }

    @Step("Should be to show pin error message as '{0}'")
    public Boolean shouldBeToShowPinErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_PIN_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_PIN_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_PIN_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input pin to edit: {0}")
    public Customer inputPin(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_PIN);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_PIN, Keys.chord(Keys.TAB));
        } else {

            webUI.sendKeys(EditCustomerRepo.LBL_PIN, customerid);
        }
        webUI.delayInSecond(2);
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_PIN);

        return new Customer(this.webUI);
    }

    @Step("Should be to show phone error message as '{0}'")
    public Boolean shouldBeToShowPhoneErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_MOBILE_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_MOBILE_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_MOBILE_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input phone to edit: {0}")
    public Customer inputPhone(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_MOBILE_NUMBER);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_MOBILE_NUMBER, Keys.chord(Keys.TAB));
        } else {

            webUI.sendKeys(EditCustomerRepo.LBL_MOBILE_NUMBER, customerid);
        }
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_MOBILE_NUMBER);

        return new Customer(this.webUI);
    }

    @Step("Should be to show email error message as '{0}'")
    public Boolean shouldBeToShowEmailErrorMessageAs(String errorMessage) {
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_MAIL_MESSAGE);
        if (webUI.verifyElementText(EditCustomerRepo.TXT_MAIL_MESSAGE, errorMessage)) {
            webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.TXT_MAIL_MESSAGE);
            return true;
        }
        return false;
    }

    @Step("input mail to edit: {0}")
    public Customer inputMail(String customerid) throws IOException {
        webUI.clearText(EditCustomerRepo.LBL_EMAIL_CUSTOMER);
        webUI.delayInSecond(1);
        if (customerid.isBlank()) {
            webUI.sendKeys(EditCustomerRepo.LBL_EMAIL_CUSTOMER, Keys.chord(Keys.TAB));
        } else {

            webUI.sendKeys(EditCustomerRepo.LBL_EMAIL_CUSTOMER, customerid);
        }
        webUI.takeScreenShotAndHighLightElement(EditCustomerRepo.LBL_EMAIL_CUSTOMER);
        return new Customer(this.webUI);
    }

    @Step("click submit button")
    public void clickSubmit() {
        webUI.clickElement(EditCustomerRepo.BTN_SUBMIT_EDIT_CUSTOMER);
        webUI.delayInSecond(2);
    }

    @Step("Should be to show customer name as '{0}'")
    public boolean shouldBeToShowCustomerNameAs(String customerName) {
        webUI.takeScreenShot();
        return webUI.verifyElementText(EditCustomerRepo.LBL_NAME, customerName);
    }
}
