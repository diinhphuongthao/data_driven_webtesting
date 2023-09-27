package guru99.components;

import guru99.page.EditCustomer;
import io.qameta.allure.Step;
import guru99.page.Customer;
import guru99.repo.LeftMenuRepo;
import untils.keywords.WebUI;

import java.io.IOException;


public class LeftMenu extends BaseComponent {

    public LeftMenu(WebUI webUI) {
        super(webUI);
    }

    @Step("Click New Customer at Left Menu")
    public Customer goToNewCustomer() throws IOException {
        webUI.clickElement(LeftMenuRepo.LNK_NEW_CUSTOMER);
        return new Customer(webUI);
    }

    @Step("Click Edit Customer at Left Menu")
    public EditCustomer goToEditCustomer() throws IOException {
        webUI.clickElement(LeftMenuRepo.LNK_EDIT_CUSTOMER);
        return new EditCustomer(webUI);
    }
}
