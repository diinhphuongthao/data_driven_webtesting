package guru99.components;

import io.qameta.allure.Step;
import guru99.repo.AdvertisementPopupRepo;
import untils.keywords.WebUI;


public class AdvertisementPopup extends BaseComponent {
    public AdvertisementPopup(WebUI webUI) {
        super(webUI);
    }

    @Step("Click Close button on Advertisement popup")
    public void closePopup() {
        if (webUI.waitForElementVisible(AdvertisementPopupRepo.FRA_GOOGLE_ADS,20)) {
            webUI.switchToIFrame(AdvertisementPopupRepo.FRA_GOOGLE_ADS);
            if (webUI.waitForElementVisible(AdvertisementPopupRepo.BTN_CLOSE,20)) {
                webUI.clickElement(AdvertisementPopupRepo.BTN_CLOSE);
            } else {
                webUI.switchToIFrame(AdvertisementPopupRepo.FRA_GOOGLE_ADS);
                webUI.clickElement(AdvertisementPopupRepo.BTN_CLOSE);
            }
            webUI.switchToDefaultContext();

        }

    }
}
