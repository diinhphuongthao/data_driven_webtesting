package guru99.page;

import io.qameta.allure.Step;
import guru99.repo.ManagerRepo;
import untils.keywords.WebUI;

import java.io.IOException;

public class Manage extends BasePage {

    public Manage(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Should be to show Manager Id as '{0}'")
    public boolean shouldBeToShowManagerIdAs(String managerId) {
        if(webUI.verifyElementText(ManagerRepo.LBL_MANAGER_ID, managerId)) {
            webUI.takeScreenShotAndHighLightElement(ManagerRepo.LBL_MANAGER_ID);
            return true;
        }
        return false;
    }
}
