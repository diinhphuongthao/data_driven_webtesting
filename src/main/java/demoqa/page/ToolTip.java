package demoqa.page;

import demoqa.repo.ToolTipRepo;
import guru99.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import untils.keywords.WebUI;

import java.io.IOException;


public class ToolTip extends BasePage {

    public ToolTip(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Should not present tool tips messsage")
    public boolean shouldNotPresentToolTipMessage() {
        if (webUI.verifyElementNotPresent(ToolTipRepo.TEXT_TOOL_TIPS)) {
            webUI.takeScreenShot();
            return true;
        }
        return false;
    }

    @Step("Should present tool tips messsage")
    public boolean isToolTipDisplayed() {
//        webUI.clickUsingActions(ToolTipRepo.BTN_TOOL_TIPS);
//        webUI.delayInSecond(1);
//        webUI.takeScreenShotAndHighLightElement(ToolTipRepo.TEXT_TOOL_TIPS);
//        WebElement toolTipText = webUI.findElement(ToolTipRepo.TEXT_TOOL_TIPS);
//
//        String toolTip = toolTipText.getText();
//        webUI.getElementText(ToolTipRepo.TEXT_TOOL_TIPS);
//        webUI.delayInSecond(3);
//        System.out.println("Tooltip text: " + toolTip);
//        if (toolTip != null) {
//            return true;
//        }
//        return false;
        webUI.clickUsingActions(ToolTipRepo.BTN_TOOL_TIPS);
        webUI.delayInSecond(1);
           ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement toolTipText = webUI.findElement(ToolTipRepo.TEXT_TOOL_TIPS);
                String toolTip = toolTipText.getText();
                return toolTip != null && !toolTip.isEmpty();
            }
        };
        webUI.takeScreenShotAndHighLightElement(ToolTipRepo.TEXT_TOOL_TIPS);
        webUI.waitFor(condition, 10);

        WebElement toolTipText = webUI.findElement(ToolTipRepo.TEXT_TOOL_TIPS);
        String toolTip = toolTipText.getText();
        if (toolTip != null && !toolTip.isEmpty()) {
         System.out.println("Tooltip text: " + toolTip);
            return true;
        }
        return false;
    }


    @Step("hover button")
    public void hoverButton(){
        webUI.mouseOver(ToolTipRepo.BTN_TOOL_TIPS);
    }
    @Step("Should  present hover button  messsage in HTML DOM")
    public boolean shouldPresentHoverButtonInHTMLDom() {
        webUI.takeScreenShotAndHighLightElement(ToolTipRepo.BTN_TOOL_TIPS);
        return webUI.verifyElementPresent(ToolTipRepo.BTN_TOOL_TIPS);
    }
    @Step("Get Text ToolTip")
    public void getTextToolTip(){
        webUI.getTextToolTip(ToolTipRepo.TEXT_TOOL_TIPS);
        webUI.verifyElementText(ToolTipRepo.TEXT_TOOL_TIPS,"You hovered over the Button");
        webUI.takeScreenShotAndHighLightElement(ToolTipRepo.TEXT_TOOL_TIPS);
    }




}
