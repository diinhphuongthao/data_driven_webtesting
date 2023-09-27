package guru99.page;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import guru99.repo.NewCustomerRepo;
import untils.keywords.WebUI;

import java.io.IOException;

public class Customer extends BasePage {

    public Customer(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Input customer name: {0}")
    public void inputCustomerName(String customerName){
        if(customerName.isBlank()){
            webUI.sendKeys(NewCustomerRepo.TXT_CUSTOMER_NAME, Keys.chord(Keys.TAB));
        }else{
            webUI.sendKeys(NewCustomerRepo.TXT_CUSTOMER_NAME, customerName);
        }
        webUI.takeScreenShotAndHighLightElement(NewCustomerRepo.TXT_CUSTOMER_NAME);
    }
    @Step("Should be to show Customer name error message as ''{0}''")
    public boolean shouldBeToShowCustomerNameErrorMessageAs(String errorMessage){
        if(webUI.verifyElementText(NewCustomerRepo.LBL_CUSTOMER_NAME_ERROR_MESSAGE, errorMessage) ){
            webUI.takeScreenShotAndHighLightElement(NewCustomerRepo.LBL_CUSTOMER_NAME_ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
