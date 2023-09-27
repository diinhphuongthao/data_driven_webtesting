package demoqa.test;

import demoqa.basePages.BaseDatePicker;
import demoqa.page.DateSpicker;
import org.testng.annotations.Test;


import java.io.IOException;
import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

public class DateSpickerTest extends BaseDatePicker {

    private DateSpicker objDateSpicker;

    public DateSpickerTest() throws IOException {
        super();
        objDateSpicker = new DateSpicker(this.webUI);
    }

    @Test(description = "TC001: Chose Day", groups = {
            "functiontests"})
    public void TC001_chose_day(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objDateSpicker.choseMonth(), "Should chose month");
    }

    @Test(description = "TC002: Chose Year", groups = {
            "functiontests"})
    public void TC002_chose_year(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objDateSpicker.chooseYear(), "Should chose year");
    }

    @Test(description = "TC003: Chose Day", groups = {
            "functiontests"})
    public void TC003_chose_day(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objDateSpicker.choseDay(), "Should chose day");
    }

    @Test(description = "TC004: Chose Time", groups = {
            "functiontests"})
    public void TC004_chose_time(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objDateSpicker.choseTime(), "Should chose time");
    }

    @Test(description = "TC005: Chose month date", groups = {
            "functiontests"})
    public void TC005_chose_month_date(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objDateSpicker.choseMonthDate(), "Should chose month date");
    }


}
