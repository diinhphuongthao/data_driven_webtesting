package guru99.page;

import guru99.components.AdvertisementPopup;
import guru99.components.LeftMenu;
import org.slf4j.Logger;
import untils.keywords.ExcelUtils;
import untils.keywords.WebUI;
import untils.helper.LogHelper;

import java.io.IOException;

public class BasePage {
    protected Logger logger = LogHelper.getLogger();
    protected WebUI webUI;
    protected ExcelUtils excelUtils;
    public AdvertisementPopup objAdvertisementPopup;
    public LeftMenu objLeftMenu;
    public BasePage(WebUI webUI) throws IOException {
        this.webUI = webUI;
        objAdvertisementPopup = new AdvertisementPopup(this.webUI);
        objLeftMenu = new LeftMenu(this.webUI);
//        this.excelUtils = new ExcelUtils("D:\\VTI\\Excel_Test_Data_Repository\\src\\main\\resources\\testdata\\LoginTest.xlsx");
    }

}
