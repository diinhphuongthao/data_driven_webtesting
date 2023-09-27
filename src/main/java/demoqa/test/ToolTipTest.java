package demoqa.test;

import demoqa.basePages.BaseToolTip;
import demoqa.page.ToolTip;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;
public class ToolTipTest extends BaseToolTip {

    private ToolTip objToolTip;

    public ToolTipTest() throws IOException {
        super();
        objToolTip = new ToolTip(this.webUI);
    }

    @Test(description = "TC001: Should  present tool tips messsage", groups = {
            "functiontests"})
    public void TC001_should_show_tool_tip_message(Method method) {
        logger.info("Test case: " + method.getName());
        objToolTip.hoverButton();
        webUI.delayInSecond(2);
        objToolTip.getTextToolTip();
        assertTrue(objToolTip.shouldPresentHoverButtonInHTMLDom(), "Should show tool tip message on UI");
    }

}
