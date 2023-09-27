package guru99.components;

import org.slf4j.Logger;
import untils.helper.LogHelper;
import untils.keywords.WebUI;


public class BaseComponent {
    protected Logger logger = LogHelper.getLogger();
    protected WebUI webUI;

    public BaseComponent(WebUI webUI) {
        this.webUI = webUI;
    }
}
