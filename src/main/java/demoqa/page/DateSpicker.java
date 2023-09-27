package demoqa.page;

import demoqa.repo.DateSpickerRepo;
import guru99.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import untils.keywords.WebUI;


import java.io.IOException;
import java.util.List;

public class DateSpicker extends BasePage {

    public DateSpicker(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Chose Month")
    public boolean choseMonth() {
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.TXT_INPUT_DATE_AND_TIME);
        webUI.clickElement(DateSpickerRepo.TXT_INPUT_DATE_AND_TIME);
        webUI.delayInSecond(2);
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_MONTH);
        webUI.clickElement(DateSpickerRepo.LBL_MONTH);
        webUI.delayInSecond(2);
        List<WebElement> ValueOptions = webUI.findElements(DateSpickerRepo.DROPDOW_MONTH);
        for (WebElement ValueOption : ValueOptions) {
            String actualText = webUI.getElementText(ValueOption);
            if (actualText.equals("June")) {
                webUI.clickElement(ValueOption);
                webUI.delayInSecond(2);
                webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_MONTH);
                return true;
            }
        }
        return false;
    }

    @Step("Choose Year")
    public boolean chooseYear() {
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_YEAR);
        webUI.clickElement(DateSpickerRepo.LBL_YEAR);
        webUI.delayInSecond(2);
        WebElement yearDropdown = webUI.findElement(DateSpickerRepo.DROPDOW_YEAR);
        webUI.clickElement(yearDropdown);
        webUI.delayInSecond(1);
        WebElement firstYearOption = webUI.findElement(DateSpickerRepo.FIRST_OPTION_YEAR);
        String currentYear = webUI.getElementText(firstYearOption);
        int targetYear = 2016;
        if (Integer.parseInt(currentYear) < targetYear) {
            while (!currentYear.equals(String.valueOf(targetYear))) {
                webUI.clickElement(DateSpickerRepo.BTN_NEXT_YEAR);
                webUI.delayInSecond(1);
                firstYearOption = webUI.findElement(DateSpickerRepo.FIRST_OPTION_YEAR);
                currentYear = webUI.getElementText(firstYearOption);
                if (currentYear == null) {
                    return false;
                }
            }
        } else {
            WebElement previousYearButton = webUI.findElement(DateSpickerRepo.PREVIOUS_YEAR_BUTTON);
            while (!currentYear.equals(String.valueOf(targetYear))) {
                webUI.clickElement(previousYearButton);
                webUI.delayInSecond(1);
                firstYearOption = webUI.findElement(DateSpickerRepo.LAST_OPTION_YEAR);
                currentYear = webUI.getElementText(firstYearOption);
                if (currentYear == null) {
                    return false;
                }
            }
        }

        webUI.clickElement(firstYearOption);
        webUI.delayInSecond(2);
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_YEAR);
        return true;
    }

    @Step("Chose Day")
    public boolean choseDay() {
        List<WebElement> ValueOptions = webUI.findElements(DateSpickerRepo.LBL_DAY);
        for (WebElement ValueOption : ValueOptions) {
            String actualText = webUI.getElementText(ValueOption);
            if (actualText.equals("20")) {
                webUI.clickElement(ValueOption);
                webUI.delayInSecond(2);
                webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_DAY);
                return true;
            }
        }
        return false;
    }

    @Step("Chose Time")
    public boolean choseTime() {
        List<WebElement> ValueOptions = webUI.findElements(DateSpickerRepo.LBL_LIST_TIME);
        for (WebElement ValueOption : ValueOptions) {
            String actualText = webUI.getElementText(ValueOption);
            if (actualText.equals("18:00")) {
                webUI.clickElement(ValueOption);
                webUI.delayInSecond(2);
                webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.TXT_INPUT_DATE_AND_TIME);
                return true;
            }
        }
        return false;
    }

    @Step("Chose Month Date")
    public boolean choseMonthDate() {
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.TXT_INPUT_DATE);
        webUI.clickElement(DateSpickerRepo.TXT_INPUT_DATE);
        webUI.delayInSecond(2);
        webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_MONTH_DATE);
        webUI.clickElement(DateSpickerRepo.LBL_MONTH_DATE);
        webUI.delayInSecond(2);
        List<WebElement> ValueOptions = webUI.findElements(DateSpickerRepo.DROPDOW_MONTH_DATE);
        for (WebElement ValueOption : ValueOptions) {
            String actualText = webUI.getElementText(ValueOption);
            if (actualText.equals("June")) {
                webUI.clickElement(ValueOption);
                webUI.delayInSecond(2);
                webUI.takeScreenShotAndHighLightElement(DateSpickerRepo.LBL_MONTH_DATE);
                return true;
            }
        }
        return false;
    }

}
