package guru99.test;

import org.testng.annotations.Test;
import guru99.page.EditCustomer;
import guru99.page.Login;


import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import static org.testng.Assert.assertTrue;

public class EditCustomerTest extends BaseTestEditCustoner {

    private EditCustomer objEditCustomer;

    public EditCustomerTest() throws IOException {
        super();
        objLogin = new Login(this.webUI);
        setDataFile(EditCustomerTest.class.getSimpleName());
    }

    @Test(description = "EC001: Should show error message at customer id text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC001_should_show_error_message_at_customer_id_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer = objManager.objLeftMenu.goToEditCustomer();
        objEditCustomer.objAdvertisementPopup.closePopup();
        objEditCustomer.inputCustomerId("");
        assertTrue(objEditCustomer.shouldBeToShowCustomerIdErrorMessageAs(findTestData("EC001.error_message")),
                MessageFormat.format("Should be to show Customer Id error message as ''{0}''",
                        findTestData("EC001.error_message")));
    }

    @Test(description = "EC002: Should show error message at customer id text box after enter character value in Customer id Field", groups = {
            "smoketest", "functiontest"})
    public void EC002_should_show_error_message_at_customer_id_text_box_after_enter_character_value(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputCustomerId(findTestData("EC002.customer_id"));
        assertTrue(objEditCustomer.shouldBeToShowCustomerIdErrorMessageAs(findTestData("EC002.error_message")),
                MessageFormat.format("Should be to show Customer Id error message as ''{0}''after enter character value in Customer id Field '{1}' ",
                        findTestData("EC002.error_message"), findTestData("EC002.customer_id")));
    }

    @Test(description = "EC003: Should show error message at customer id text box after enter Special Character In Customer id  Field", groups = {
            "smoketest", "functiontest"})
    public void EC003_should_show_error_message_at_customer_id_text_box_after_enter_special_character(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputCustomerId(findTestData("EC003.customer_id"));
        assertTrue(objEditCustomer.shouldBeToShowCustomerIdErrorMessageAs(findTestData("EC003.error_message")),
                MessageFormat.format("Should be to show Customer Id error message as ''{0}''after enter character value in Customer id Field '{1}' ",
                        findTestData("EC003.error_message"), findTestData("EC003.customer_id")));
    }

    @Test(description = "EC004: input customer id to edit", groups = {"functiontest"})
    public void EC004_input_customer_id_to_edit(Method method) throws IOException {
        logger.info("Test case: " + method);
        objEditCustomer.inputCustomerId(findTestData("EC004.customer_id"));
        objEditCustomer.clickSubmit();
        assertTrue(objEditCustomer.shouldBeToShowCustomerNameAs(findTestData("EC004.customer_name")), findTestData("EC004.customer_name"));

    }

    @Test(description = "EC005: Should show error message at address text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC005_should_show_error_message_at_address_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
//        webUI.clearText(EditCustomerRepo.LBL_ADDRESS);
//        webUI.delayInSecond(2);
        objEditCustomer.inputAddress("");
        webUI.delayInSecond(2);
        assertTrue(objEditCustomer.shouldBeToShowAdressErrorMessageAs(findTestData("EC005.error_message")),
                MessageFormat.format("Should be to show address error message as ''{0}''",
                        findTestData("EC005.error_message")));
    }

    @Test(description = "EC006: Should show error message at city text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC006_should_show_error_message_at_city_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputCity("");
        assertTrue(objEditCustomer.shouldBeToShowCityErrorMessageAs(findTestData("EC006.error_message")),
                MessageFormat.format("Should be to show city error message as ''{0}''",
                        findTestData("EC006.error_message")));
    }

    @Test(description = "EC007: Should show error message at city text box after enter enter numeric value in CITY Field", groups = {
            "smoketest", "functiontest"})
    public void EC007_should_show_error_message_at_city_text_box_after_enter_numeric_value(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputCity(findTestData("EC007.customer_city"));
        assertTrue(objEditCustomer.shouldBeToShowCityErrorMessageAs(findTestData("EC007.error_message")),
                MessageFormat.format("Should be to show city error message as ''{0}''after enter enter numeric value in CITY Field '{1}' ",
                        findTestData("EC007.error_message"), findTestData("EC007.customer_city")));
    }

    @Test(description = "EC008: Should show error message at city text box after enter enter Special Character In CITY Field", groups = {
            "smoketest", "functiontest"})
    public void EC008_should_show_error_message_at_city_text_box_after_enter_enter_special_character(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputCity(findTestData("EC008.customer_city"));
        assertTrue(objEditCustomer.shouldBeToShowCityErrorMessageAs(findTestData("EC008.error_message")),
                MessageFormat.format("Should be to show city error message as ''{0}''after enter enter Special Character '{1}' ",
                        findTestData("EC008.error_message"), findTestData("EC008.customer_city")));
    }

    @Test(description = "EC009: Should show error message at state text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC009_should_show_error_messag_at_state_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputState("");
        webUI.delayInSecond(2);
        assertTrue(objEditCustomer.shouldBeToShowStateErrorMessageAs(findTestData("EC009.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''",
                        findTestData("EC009.error_message")));
    }

    @Test(description = "EC010: Should show error message at city text box after enter enter numeric value In state Field", groups = {
            "smoketest", "functiontest"})
    public void EC010_should_show_error_message_at_state_text_box_after_enter_enter_number_value(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputState(findTestData("EC010.customer_state"));
        assertTrue(objEditCustomer.shouldBeToShowStateErrorMessageAs(findTestData("EC010.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC010.error_message"), findTestData("EC010.customer_state")));
    }

    @Test(description = "EC011: Should show error message at city text box after enter enter Special Character In state Field", groups = {
            "smoketest", "functiontest"})
    public void EC011_should_show_error_message_at_city_text_box_after_enter_enter_special_character(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputState(findTestData("EC011.customer_state"));
        assertTrue(objEditCustomer.shouldBeToShowStateErrorMessageAs(findTestData("EC011.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''after enter enter Special Character '{1}' ",
                        findTestData("EC011.error_message"), findTestData("EC011.customer_state")));
    }

    @Test(description = "EC012: Should show error message at pin text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC012_should_show_error_messag_at_pin_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputPin("");
        webUI.delayInSecond(2);
        assertTrue(objEditCustomer.shouldBeToShowPinErrorMessageAs(findTestData("EC012.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''",
                        findTestData("EC012.error_message")));
    }

    @Test(description = "EC013: Should show error message at pin text box after enter enter not enoungh numeric value ", groups = {
            "smoketest", "functiontest"})
    public void EC013_should_show_error_message_at_pin_text_box_after_enter_not_enough_number_value(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputPin(findTestData("EC013.customer_pin"));
        assertTrue(objEditCustomer.shouldBeToShowPinErrorMessageAs(findTestData("EC013.error_message")),
                MessageFormat.format("Should be to show pin error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC013.error_message"), findTestData("EC013.customer_pin")));
    }

    @Test(description = "EC014: Should show error message at pin text box after enter enter not over numeric value ", groups = {
            "smoketest", "functiontest"})
    public void EC014_should_show_error_message_at_pin_text_box_after_enter_not_over_number_value(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
//        String pin = objEditCustomer.inputPin(findTestData("EC014.customer_pin")).toString();
//        if(pin.length()>6);
        objEditCustomer.inputPin(findTestData("EC014.customer_pin"));
        webUI.delayInSecond(1);
        assertTrue(objEditCustomer.shouldBeToShowPinErrorMessageAs(findTestData("EC014.error_message")),
                MessageFormat.format("Should be to show pin error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC014.error_message"), findTestData("EC014.customer_pin")));
    }

    @Test(description = "EC015: Should show error message at pin text box after enter enter special character value ", groups = {
            "smoketest", "functiontest"})
    public void EC015_should_show_error_message_at_pin_text_box_after_enter_enter_special_character(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputPin(findTestData("EC015.customer_pin"));
        assertTrue(objEditCustomer.shouldBeToShowPinErrorMessageAs(findTestData("EC015.error_message")),
                MessageFormat.format("Should be to show pin error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC015.error_message"), findTestData("EC015.customer_pin")));
    }

    @Test(description = "EC016: Should show error message at phone text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC016_should_show_error_messag_at_phone_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputPhone("");
        webUI.delayInSecond(2);
        assertTrue(objEditCustomer.shouldBeToShowPhoneErrorMessageAs(findTestData("EC016.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''",
                        findTestData("EC016.error_message")));
    }

    @Test(description = "EC017: Should show error message at phone text box after enter enter special character value ", groups = {
            "smoketest", "functiontest"})
    public void EC017_should_show_error_message_at_phone_text_box_after_enter_enter_special_character(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputPhone(findTestData("EC017.customer_phone"));
        assertTrue(objEditCustomer.shouldBeToShowPhoneErrorMessageAs(findTestData("EC017.error_message")),
                MessageFormat.format("Should be to show pin error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC017.error_message"), findTestData("EC017.customer_phone")));
    }

    @Test(description = "EC018: Should show error message at mail text box after press Tab key", groups = {
            "smoketest", "functiontest"})
    public void EC018_should_show_error_messag_at_mail_text_box_after_press_Tab_key(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputMail("");
        webUI.delayInSecond(2);
        assertTrue(objEditCustomer.shouldBeToShowEmailErrorMessageAs(findTestData("EC018.error_message")),
                MessageFormat.format("Should be to show state error message as ''{0}''",
                        findTestData("EC018.error_message")));
    }

    @Test(description = "EC019: Should show error message at mail text box after enter email must be in format ", groups = {
            "smoketest", "functiontest"})
    public void EC019_should_show_error_message_at_phone_text_box_after_enter_enter_email_must_be_in_format(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        objEditCustomer.inputMail(findTestData("EC019.customer_mail"));
        assertTrue(objEditCustomer.shouldBeToShowEmailErrorMessageAs(findTestData("EC019.error_message")),
                MessageFormat.format("Should be to show pin error message as ''{0}''after enter enter numeric value '{1}' ",
                        findTestData("EC019.error_message"), findTestData("EC019.customer_mail")));
    }

}
