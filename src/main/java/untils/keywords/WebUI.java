package untils.keywords;



import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;


import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import untils.helper.AllureLogger;
import untils.helper.LogHelper;

public class WebUI {

    private static final int defaultTimeOut = 60;
    private static final Logger logger = LogHelper.getLogger();
    //  private WebDriver driver; //global variable
//    private static DriverManager driverManager;


    //Chrome, chrome, ChRome => CHROME
    private static WebDriver driver;
    public void openBrowser(String browser, String... url) {
        logger.info(MessageFormat.format("Launching {0}", browser.toUpperCase()));
        try {
            switch (browser.toUpperCase()) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options); //local variable
                    break;
                case "EDGE":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new EdgeDriver();
                    break;
            }
            logger.info(MessageFormat.format("Launched {0} successfully", browser.toUpperCase()));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot launch {0}. Root cause: {1}", browser, e.getMessage()));
        }
        String rawUrl = url.length != 0 ? url[0] : "";
        if (rawUrl.length() != 0) {
            try {
                logger.info(MessageFormat.format("Navigating to ''{0}''", rawUrl));
                driver.get(rawUrl);
                logger.info(MessageFormat.format("Navigated to ''{0}'' successfully", rawUrl));
            } catch (Exception e) {
                logger.error(MessageFormat.format("Cannot navigate to ''{0}''. Root cause: {1}", rawUrl,
                        e.getMessage()));
            }
        }
    }

    public void closeBrowser() {
        logger.info("Closing the browser");
        try {
            driver.close();
            logger.info("Closed the browser successfully");
        } catch (Exception e) {
            logger.info(
                    MessageFormat.format("Cannot close the browser. Root cause: {0}", e.getMessage()));
        }
    }

    public String getTitle() {
        logger.info("Getting title of the page");
        String title = null;
//        WebDriver driver = driverManager.getDriver();
        try {
            title = driver.getTitle();
            logger.info(MessageFormat.format("Title is ''{0}''", title));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot get title of the page. Root cause: {0}", e.getMessage()));
        }
        return title;
    }

    public String getUrl() {
        logger.info("Getting url of the page");
        String url = null;
//        WebDriver driver = driverManager.getDriver();
        try {
            url = driver.getCurrentUrl();
            logger.info(MessageFormat.format("Url of the page is ''{0}''", url));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot get url of the page. Root cause: {0}", e.getMessage()));
        }
        return url;
    }

    public static void navigateToUrl(String url) {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info(MessageFormat.format("Navigating to ''{0}''", url));
            driver.navigate().to(url);
            logger.info(MessageFormat.format("Navigated to ''{0}'' successfully", url));
        } catch (Exception e) {
            logger.error(
                    MessageFormat
                            .format("Cannot navigate to ''{0}''. Root cause: {1}", url, e.getMessage()));
        }
    }

    public void forward() {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info("Forwarding to next page");
            driver.navigate().forward();
            logger.info("Forwarded to next page successfully");
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot forward to next page. Root cause: {0}", e.getMessage()));
        }
    }

    public void back() {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info("Backing to next page");
            driver.navigate().back();
            logger.info("Back to next page successfully");
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot back to next page. Root cause: {0}", e.getMessage()));
        }
    }

    //Thực hiện phóng to cửa sổ
    public void maximizeWindow() {
//        WebDriver driver = driverManager.getDriver();
        try {
            driver.manage().window().maximize();
            logger.info("Window maximized successfully");
        } catch (Exception e) {
            logger.error("Cannot maximize window. Root cause: " + e.getMessage());
        }
    }

    //Tìm một phần tử trên trang web
    public WebElement findElement(By locator, int... timeOut) {
        WebElement element = null;
        int waitTime = timeOut.length != 0 ? timeOut[0] : defaultTimeOut;
        logger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
        AllureLogger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
//        WebDriver driver = driverManager.getDriver();
        try {
            Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(waitTime))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(locator);
                }
            });
            logger.info(MessageFormat.format("Found {0} web element located by ''{1}''", 1, locator));
            AllureLogger.info(
                    MessageFormat.format("Found {0} web element located by ''{1}''", 1, locator));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
            AllureLogger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
        }
        return element;
    }

    public void setImplicitlyWait(int seconds) {
//        WebDriver driver = driverManager.getDriver();
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public WebElement findElement(String locator, int... timeOut) {
        WebElement element = null;
        logger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
        AllureLogger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
        long startTime = 0;
        long endTime = 0;
        double total;
        int waitTime = timeOut.length != 0 ? timeOut[0] : defaultTimeOut;
//        WebDriver driver = driverManager.getDriver();
        try {
            startTime = System.currentTimeMillis();
//      element = driver.findElement(findBy(locator));

            Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(waitTime))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(findBy(locator));
                }
            });

//      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//      element = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy(locator)));
            endTime = System.currentTimeMillis();
            logger.info(MessageFormat.format("Found {0} web element located by ''{1}''", 1, locator));
            AllureLogger.info(
                    MessageFormat.format("Found {0} web element located by ''{1}''", 1, locator));
        } catch (TimeoutException e) {
            endTime = System.currentTimeMillis();
            logger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
            AllureLogger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
        }
        total = (double) (endTime - startTime) / 1000;
        logger.info(MessageFormat.format("Timeout: ''{0}''", total));
        return element;
    }

    //Tìm nhiều phần tử trên trang web
    public List<WebElement> findElements(By locator) {
        List<WebElement> elements = null;
        logger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
//        WebDriver driver = driverManager.getDriver();
        try {
            elements = driver.findElements(locator);
            logger.info(
                    MessageFormat.format("Found {0} web element(s) located by ''{1}''", elements.size(),
                            locator));
        } catch (NoSuchElementException e) {
            logger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
        }
        return elements;
    }

    public List<WebElement> findElements(String locator) {
        List<WebElement> elements = null;
        logger.info(MessageFormat.format("Finding web element located by ''{0}''", locator));
//        WebDriver driver = driverManager.getDriver();
        try {
            elements = driver.findElements(findBy(locator));
            logger.info(
                    MessageFormat.format("Found {0} web element(s) located by ''{1}''", elements.size(),
                            locator));
        } catch (NoSuchElementException e) {
            logger.error(
                    MessageFormat.format("Cannot find web element located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
        }
        return elements;
    }

    //Clear nội dung của một phần tử (ví dụ: ô input): clearText, clear
    public void clearText(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.clear();
                logger.info("Cleared element: " + locator.toString());
            } catch (Exception e) {
                logger.error(
                        "Cannot clear element: " + locator.toString() + ". Root cause: " + e.getMessage());
            }
        }
    }

    public void clearText(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                element.clear();
                logger.info("Cleared element: " + locator);
            } catch (Exception e) {
                logger.error(
                        "Cannot clear element: " + locator + ". Root cause: " + e.getMessage());
            }
        }
    }


    //Nhập dữ liệu vào một phần tử
    public void setText(By locator, String value) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.sendKeys(value);
                logger.info("Entered text '" + value + "' into element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot enter text into element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    public void setText(String locator, String value) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                element.sendKeys(value);
                logger.info("Entered text '" + value + "' into element: " + locator);
            } catch (Exception e) {
                logger.error("Cannot enter text into element: " + locator + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    public void sendKeys(By locator, String value) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.sendKeys(value);
                logger.info("Entered text '" + value + "' into element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot enter text into element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    public void sendKeys(String locator, String value) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                element.sendKeys(value);
                logger.info("Entered text '" + value + "' into element: " + locator);
            } catch (Exception e) {
                logger.error("Cannot enter text into element: " + locator + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    //Nhấp chuột vào một phần tử: clickElement/click
    public void clickElement(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.click();
                logger.info("Clicked element: " + locator.toString());
            } catch (Exception e) {
                logger.error(
                        MessageFormat.format("Cannot click element: {0}. Root cause: {1}", locator.toString(),
                                e.getMessage()));
            }
        }
    }

    public void clickElement(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                element.click();
                logger.info("Clicked element: " + locator);
            } catch (Exception e) {
                logger.error(
                        MessageFormat.format("Cannot click element: {0}. Root cause: {1}", locator,
                                e.getMessage()));
            }
        }
    }

    public void clickElement(WebElement we) {
        if (we != null) {
            try {
                we.click();
                logger.info("Clicked element: " + we);
            } catch (Exception e) {
                logger.error(
                        MessageFormat.format("Cannot click element: {0}. Root cause: {1}", we,
                                e.getMessage()));
            }
        }
    }

    //Lấy giá trị của một phần tử
    public void getElementAttributeValue(By locator, String attributeName) {
        WebElement element = findElement(locator);
        String value;
        if (element != null) {
            try {
                value = element.getAttribute(attributeName);
                logger.info("Got value '" + value + "' from element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot get value from element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    public void getElementAttributeValue(String locator, String attributeName) {
        WebElement element = findElement(findBy(locator));
        String value;
        if (element != null) {
            try {
                value = element.getAttribute(attributeName);
                logger.info("Got value '" + value + "' from element: " + locator);
            } catch (Exception e) {
                logger.error("Cannot get value from element: " + locator + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

    //Kiểm tra xem phần tử có hiện trên trang web hay không
    public boolean verifyElementVisible(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                if (element.isDisplayed()) {
                    logger
                            .info(MessageFormat.format("Web element located by ''{0}'' is visible", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is displayed: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger
                .error(MessageFormat.format("Web element located by ''{0}'' is not visible", locator));
        return false;
    }

    public boolean verifyElementVisible(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                if (element.isDisplayed()) {
                    logger
                            .info(MessageFormat.format("Web element located by ''{0}'' is visible", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is displayed: " + locator + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger
                .error(MessageFormat.format("Web element located by ''{0}'' is not visible", locator));
        return false;
    }

    public boolean verifyElementNotVisible(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                if (!element.isDisplayed()) { //!false = true, !true = false
                    logger.info(
                            MessageFormat.format("Web element located by ''{0}'' is not visible", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is displayed: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger.error(MessageFormat.format("Web element located by ''{0}'' is visible", locator));
        return false;
    }

    public boolean verifyElementNotVisible(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                if (!element.isDisplayed()) { //!false = true, !true = false
                    logger.info(
                            MessageFormat.format("Web element located by ''{0}'' is not visible", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is displayed: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger.error(MessageFormat.format("Web element located by ''{0}'' is visible", locator));
        return false;
    }

    public boolean verifyElementPresent(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            logger.info(MessageFormat.format("Web element located by ''{0}'' is present", locator));
            return true;
        } else {
            logger.error(
                    MessageFormat.format("Web element located by ''{0}'' is not present", locator));
            return false;
        }
    }

    public boolean verifyElementNotPresent(By locator) {
        WebElement element = findElement(locator);
        if (element == null) {
            logger.info(
                    MessageFormat.format("Web element located by ''{0}'' is not present", locator));
            return true;
        } else {
            logger.error(MessageFormat.format("Web element located by ''{0}'' is present", locator));
            return false;
        }
    }

    public boolean verifyElementNotPresent(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element == null) {
            logger.info(
                    MessageFormat.format("Web element located by ''{0}'' is not present", locator));
            return true;
        } else {
            logger.error(MessageFormat.format("Web element located by ''{0}'' is present", locator));
            return false;
        }
    }

    //Kiểm tra xem một phần tử có được kích hoạt (enabled) hay không
    public boolean verifyElementClickable(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                if (element.isEnabled()) {
                    logger.info(
                            MessageFormat.format("Web element located by ''{0}'' is clickable", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is enabled: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger.error(
                MessageFormat.format("Web element located by ''{0}'' is not clickable", locator));
        return false;
    }

    public boolean verifyElementNotClickable(String locator) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                if (!element.isEnabled()) {
                    logger.info(
                            MessageFormat.format("Web element located by ''{0}'' is not clickable", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is enabled: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        logger.error(MessageFormat.format("Web element located by ''{0}'' is clickable", locator));
        return false;
    }

    public boolean verifyElementAttribute(String locator, String attribute, String attributeValue) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                if (element.getAttribute(attribute).equals(attributeValue)) {
                    logger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}'' with value ''{2}''",
                            locator, attribute, attributeValue));
                    AllureLogger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}'' with value ''{2}''",
                            locator, attribute, attributeValue));
                    return true;
                } else {
                    logger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}'' has no value ''{2}''",
                            locator, attribute, attributeValue));
                    AllureLogger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}'' has no value ''{2}''",
                            locator, attribute, attributeValue));

                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is enabled: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        return false;
    }

    public boolean verifyElementHasAttribute(String locator, String attribute) {
        WebElement element = findElement(findBy(locator));
        if (element != null) {
            try {
                String value = element.getAttribute(attribute);
                if (value != null && !value.isEmpty()) {
                    logger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}''",
                            locator, attribute));
                    AllureLogger.info(MessageFormat.format("Web element located by ''{0}'' has attribute ''{1}''",
                            locator, attribute));
                    return true;
                } else {
                    logger.error(MessageFormat.format("Web element located by ''{0}'' has not attribute ''{1}'' ",
                            locator, attribute));
                    AllureLogger.error(MessageFormat.format("Web element located by ''{0}'' has not attribute ''{1}'' ",
                            locator, attribute));

                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is enabled: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        return false;
    }

    //Kiểm tra xem một phần tử có được chọn (selected) hay không (chủ yếu áp dụng cho
    // các phần tử như checkbox, radio button)
    public boolean verifyElementSelected(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                if (element.isSelected()) {
                    logger.info(
                            MessageFormat.format("Web element located by ''{0}'' is selected", locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error(
                        "Cannot determine if element is selected: " + locator.toString() + ". Root cause: "
                                + e.getMessage());
            }
        }
        return false;
    }

    //Submit một biểu mẫu (Form)
    public void submit(By locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.submit();
                logger.info("Submitted form: " + locator.toString());
            } catch (Exception e) {
                logger.error(
                        "Cannot submit form: " + locator.toString() + ". Root cause: " + e.getMessage());
            }
        }
    }

    //Lấy nội dung text của một phần tử
    public String getElementText(By locator) {
        WebElement element = findElement(locator);
        String text = null;
        if (element != null) {
            try {
                text = element.getText();
                logger.info(
                        MessageFormat.format("Text of web element located by ''{0}'' is ''{1}''", locator,
                                text));
            } catch (Exception e) {
                logger.error("Cannot get text from element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return text;
    }

    public String getElementText(String locator) {
        WebElement element = findElement(findBy(locator));
        String text = null;
        if (element != null) {
            try {
                text = element.getText();
                logger.info(
                        MessageFormat.format("Text of web element located by ''{0}'' is ''{1}''", locator,
                                text));
            } catch (Exception e) {
                logger.error("Cannot get text from element: " + locator + ". Root cause: "
                        + e.getMessage());
            }
        }
        return text;
    }

    public String getElementText(WebElement we) {
        String text = null;
        if (we != null) {
            try {
                text = we.getText();
                logger.info(
                        MessageFormat.format("Text of web element located by ''{0}'' is ''{1}''", we, text));
            } catch (Exception e) {
                logger.error("Cannot get text from element: " + we + ". Root cause: "
                        + e.getMessage());
            }
        }
        return text;
    }

    public boolean verifyElementText(By locator, String expectedText) {
        WebElement element = findElement(locator);
        String actualText = null;
        if (element != null) {
            try {
                actualText = element.getText();
                if (actualText.equals(expectedText)) {
                    logger.info(MessageFormat.format(
                            "Actual text ''{0}'' and expected text ''{1}'' of web element located by ''{2}'' are the same",
                            actualText, expectedText, locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error("Cannot get text from element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        logger.error(MessageFormat.format(
                "Actual text ''{0}'' and expected text ''{1}'' of web element located by ''{2}'' are not the same",
                actualText, expectedText, locator));
        return false;
    }

    public boolean verifyElementText(String locator, String expectedText) {
        WebElement element = findElement(findBy(locator));
        String actualText = null;
        if (element != null) {
            try {
                actualText = element.getText();
                if (actualText.equals(expectedText)) {
                    logger.info(MessageFormat.format(
                            "Actual text ''{0}'' and expected text ''{1}'' of web element located by ''{2}'' are the same",
                            actualText, expectedText, locator));
                    return true;
                }
            } catch (Exception e) {
                logger.error("Cannot get text from element: " + locator + ". Root cause: "
                        + e.getMessage());
            }
        }
        logger.error(MessageFormat.format(
                "Actual text ''{0}'' and expected text ''{1}'' of web element located by ''{2}'' are not the same",
                actualText, expectedText, locator));
        return false;
    }

    //Lấy thẻ của một phần tử
    public String getElementTagName(By locator) {
        WebElement element = findElement(locator);
        String tagName = null;
        if (element != null) {
            try {
                tagName = element.getTagName();
                logger.info("Got tag name '" + tagName + "' from element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot get tag name from element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return tagName;
    }

    //Lấy giá trị thuộc tính CSS của một phần tử
    public String getElementCssValue(By locator, String propertyName) {
        WebElement element = findElement(locator);
        String cssValue = null;
        if (element != null) {
            try {
                cssValue = element.getCssValue(propertyName);
                logger.info(
                        "Got CSS value '" + cssValue + "' for property '" + propertyName + "' from element: "
                                + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot get CSS value for property '" + propertyName + "' from element: "
                        + locator.toString() + ". Root cause: " + e.getMessage());
            }
        }
        return cssValue;
    }

    //Lấy giá trị thuộc tính của một phần tử
    public String getElementAttribute(By locator, String attributeName) {
        WebElement element = findElement(locator);
        String attributeValue = null;
        if (element != null) {
            try {
                attributeValue = element.getAttribute(attributeName);
                logger.info("Got attribute value '" + attributeValue + "' for attribute '" + attributeName
                        + "' from element: " + locator.toString());
            } catch (Exception e) {
                logger.error(
                        "Cannot get attribute value for attribute '" + attributeName + "' from element: "
                                + locator.toString() + ". Root cause: " + e.getMessage());
            }
        }
        return attributeName;
    }

    //Lấy kích thước của một phần tử (chiều rộng và chiều cao)
    public Dimension getElementSize(By locator) {
        WebElement element = findElement(locator);
        Dimension size = null;
        if (element != null) {
            try {
                size = element.getSize();
                logger.info("Got size: " + size.toString() + " for element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot get size for element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return size;
    }

    public int getElementHeight(By locator) {
        WebElement element = findElement(locator);
        int height = 0;
        if (element != null) {
            try {
                height = element.getSize().getHeight();
                logger
                        .info(MessageFormat.format("Height of web element located by ''{0}'' is {1}", locator,
                                height));
            } catch (Exception e) {
                logger.error("Cannot get size for element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return height;
    }

    public int getElementWidth(By locator) {
        WebElement element = findElement(locator);
        int width = 0;
        if (element != null) {
            try {
                width = element.getSize().getWidth();
                logger.info(
                        MessageFormat
                                .format("Width of web element located by ''{0}'' is {1}", locator, width));
            } catch (Exception e) {
                logger.error("Cannot get size for element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return width;
    }

    //Lấy vị trí của một phần tử (tọa độ x và y)
    public Point getElementLocation(By locator) {
        WebElement element = findElement(locator);
        Point location = null;
        if (element != null) {
            try {
                location = element.getLocation();
                logger
                        .info("Got location: " + location.toString() + " for element: " + locator.toString());
            } catch (Exception e) {
                logger.error("Cannot get location for element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
        return location;
    }

    //x
    public void getElementLeftPosition(By locator) {

    }


    //y
    public void getElementVerticalPosition(By locator) {

    }

    //Thực hiện Scroll down
    public void scrollDown() {
//        WebDriver driver = driverManager.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            logger.info("Scrolled down successfully");
        } catch (Exception e) {
            logger.error("Cannot scroll down. Root cause: " + e.getMessage());
        }
    }

    public void waitFor(ExpectedCondition<Boolean> condition, int timeoutInSeconds) {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//        WebDriver driver = driverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for condition: " + e.getMessage());
        }
    }

    // Ví dụ "id:abc"
    public By findBy(String locator) {
        int prefixLocator = locator.indexOf(":"); //lấy vị trí của ký tự : là vị trí số 2
        if (prefixLocator < 0) { // nếu không có ký tự : thì sẽ prefixLocator = -1
            prefixLocator = 0; // gán lại prefixLocator = 0
        }

        String locatorType = locator.substring(0, prefixLocator); // locatorType = id (0 - 2)
        if (locatorType.isEmpty() || locatorType.isBlank() || locatorType.length() >= 5) {
            locatorType = "xpath";
        }
        String locatorExpression;
        if (prefixLocator == 0) {
            locatorExpression = locator.substring(prefixLocator);
        } else {
            locatorExpression = locator.substring(prefixLocator + 1); // locatorExpression = abc
        }
        switch (locatorType) {
            case "id":
                return By.id(locatorExpression);
            case "name":
                return By.name(locatorExpression);
            case "class":
                return By.className(locatorExpression);
            case "css":
                return By.cssSelector(locatorExpression);
            case "tag":
                return By.tagName(locatorExpression);
            case "xpath":
            default:
                return By.xpath(locatorExpression);
        }
    }

    public void selectOptionByIndex(String locator, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is greater than 0. Pls re-input index");
        }
        WebElement we = findElement(findBy(locator));
        try {
            logger.info(MessageFormat.format(
                    "Selecting option of web element located by ''{0}'' with index ''{1}''", locator,
                    index));
            Select select = new Select(we);
            select.selectByIndex(index);
            logger.info(MessageFormat.format(
                    "Selected option of web element located by ''{0}'' with index ''{1}'' successfully",
                    locator, index));
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select option of web element located by ''{0}'' with index ''{1}''. Root cause: {2}",
                    locator, index, e.getMessage()));
        }
    }

    public void selectOptionByValue(String locator, String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is not null. Pls re-input value");
        }
        WebElement we = findElement(findBy(locator));
        try {
            logger.info(MessageFormat.format(
                    "Selecting option of web element located by ''{0}'' with value ''{1}''", locator,
                    value));
            Select select = new Select(we);
            select.selectByValue(value);
            logger.info(MessageFormat.format(
                    "Selected option of web element located by ''{0}'' with value ''{1}'' successfully",
                    locator, value));
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select option of web element located by ''{0}'' with value ''{1}''. Root cause: {2}",
                    locator, value, e.getMessage()));
        }
    }

    public void selectOptionByLabel(String locator, String label) {
        if (label == null) {
            throw new IllegalArgumentException("Label is not null. Pls re-input label");
        }
        WebElement we = findElement(findBy(locator));
        try {
            logger.info(MessageFormat.format(
                    "Selecting option of web element located by ''{0}'' with label ''{1}''", locator,
                    label));
            Select select = new Select(we);
            select.selectByVisibleText(label);
            logger.info(MessageFormat.format(
                    "Selected option of web element located by ''{0}'' with label ''{1}'' successfully",
                    locator, label));
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Cannot select option of web element located by ''{0}'' with label ''{1}''. Root cause: {2}",
                    locator, label, e.getMessage()));
        }
    }

    public void delayInSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info(MessageFormat.format("Delayed ''{0}'' seconds", seconds));
        } catch (InterruptedException e) {
            logger.error(MessageFormat.format("Cannot delay ''{0}'' second(s)", seconds));
        }
    }

    public boolean waitForElementVisible(String locator, int... timeOut) {
        WebElement we = findElement(findBy(locator));
        int waitTime = timeOut.length > 0 ? timeOut[0] : defaultTimeOut; // ? :
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info(
                    MessageFormat.format("Waiting for web element located by ''{0}'' visible", locator));
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
            WebElement foundWe = (WebElement) wait.until(ExpectedConditions.visibilityOf(we));
            if (foundWe != null) {
                logger.info(MessageFormat.format(
                        "Waited for web element located by ''{0}'' visible successfully within ''{1}'' second(s)",
                        locator, waitTime));
                return true;
            } else {
                logger.error(
                        MessageFormat.format(
                                "Web element located by ''{0}'' is still not visible after ''{1}'' second(s)",
                                locator,
                                waitTime));
            }
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format(
                            "Cannot wait for web element located by ''{0}'' visible after ''{1}'' second(s). Root cause: {2}",
                            locator, waitTime, e.getMessage()));
        }
        return false;
    }

    public void clickJS(String locator) {
        WebElement we = findElement(locator);
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info(MessageFormat.format("Clicking web element located by ''{0}''", locator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", we);
            logger.info(
                    MessageFormat.format("Click web element located by ''{0}'' successfully", locator));
        } catch (Exception e) {
            logger
                    .error(MessageFormat.format("Cannot click web element located by ''{0}''", locator));
        }
    }

    public void acceptAlert() {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info("Accepting alert");
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Accepted alert successfully");
        } catch (Exception e) {
            logger
                    .error(MessageFormat.format("Cannot accept alert. Root cause: {0}", e.getMessage()));
        }
    }

    public void dismissAlert() {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info("Dismissing alert");
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Dismissed alert successfully");
        } catch (Exception e) {
            logger
                    .error(MessageFormat.format("Cannot dismiss alert. Root cause: {0}", e.getMessage()));
        }
    }

    public String getAlertText() {
        String text = null;
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info("Getting alert text");
            Alert alert = driver.switchTo().alert();
            text = alert.getText();
            logger.info(MessageFormat.format("Text on alert is ''{0}''", text));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot get alert text. Root cause: {0}", e.getMessage()));
        }
        return text;
    }

    public void setAlertText(String text) {
//        WebDriver driver = driverManager.getDriver();
        try {
            logger.info(MessageFormat.format("Setting alert text: ''{0}''", text));
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(text);
            logger.info(MessageFormat.format("Set alert text ''{0}'' successfully", text));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot set alert text. Root cause: {0}", e.getMessage()));
        }
    }

    public void clickUsingActions(String locator) {
//        WebDriver driver = driverManager.getDriver();
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                Actions actions = new Actions(driver);
//        actions.moveToElement(element);
//        actions.click();
//        Action action = actions.build();
//        action.perform();
                actions.moveToElement(element).click().build().perform();
                logger.info("Clicked element: " + locator);
            } catch (Exception e) {
                logger.error(
                        MessageFormat.format("Cannot click element: {0}. Root cause: {1}", locator,
                                e.getMessage()));
            }
        }
    }

    public void clickUsingActions(WebElement we) {
//        WebDriver driver = driverManager.getDriver();
        if (we != null) {
            try {
                Actions actions = new Actions(driver);
//        actions.moveToElement(we);
//        actions.click();
//        Action action = actions.build();
//        action.perform();
                actions.moveToElement(we).click().build().perform();
                logger.info("Clicked element: " + we);
            } catch (Exception e) {
                logger.error(
                        MessageFormat.format("Cannot click element: {0}. Root cause: {1}", we,
                                e.getMessage()));
            }
        }
    }

    public void clickOffset(WebElement we, int offsetX, int offsetY) {
//        WebDriver driver = driverManager.getDriver();
        if (we != null) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(we, offsetX, offsetY).click().build().perform();
                logger.info("Click offset element:" + we);
            } catch (Exception e) {
                logger.error(MessageFormat.format("Can not click offset element:{0}.Root cause:{1}",
                        we, offsetX, offsetY, e.getMessage()));
            }
        }
    }

    public void clickOffset(String locator, int offsetX, int offsetY) {
//        WebDriver driver = driverManager.getDriver();
        WebElement we = findElement(locator);
        if (we != null) {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(we, offsetX, offsetY).click().build().perform();
                logger.info("Click offset element:" + we);
            } catch (Exception e) {
                logger.error(MessageFormat.format("Can not click offset element:{0}.Root cause:{1}",
                        we, offsetX, offsetY, e.getMessage()));
            }
        }
    }

    public void dragAndDropByOffset(String locator, int offsetX, int offsetY) {
//        WebDriver driver = driverManager.getDriver();
        WebElement we = findElement(locator);
        logger.info(MessageFormat
                .format("Dragging web element located by {0} to [{1},{2}]", locator, offsetX, offsetY));
        if (we != null) {
            try {
                Actions actions = new Actions(driver);
                actions.dragAndDropBy(we, offsetX, offsetY).build().perform();
                logger.info(MessageFormat
                        .format("Dropped web element located by {0} at [{1},{2}] successfully", locator,
                                offsetX, offsetY));
            } catch (Exception e) {
                logger.error(MessageFormat.format(
                        "Can not drag and drop web element located by {0} to [{1},{2}] . Root cause: {3}",
                        locator, offsetX, offsetY, e.getMessage()));
            }
        }
    }

    public void dragAndDropToElement(String sourceElement, String destinationElement) {
//        WebDriver driver = driverManager.getDriver();
        WebElement sourceWe = findElement(sourceElement);
        WebElement destinationWe = findElement(destinationElement);
        logger.info(MessageFormat
                .format("Dragging web element located by {0} to destination web element located by {1}",
                        sourceElement, destinationElement));
        if (sourceWe != null && destinationWe != null) {
            try {
                Actions actions = new Actions(driver);
                actions.dragAndDrop(sourceWe, destinationWe).build().perform();
                logger.info(MessageFormat
                        .format(
                                "Dropped web element located by {0} to destination web element located by {1} successfully",
                                sourceElement, destinationElement));
            } catch (Exception e) {
                logger.error(MessageFormat.format(
                        "Can not drag and drop web element located by {0} to destination web element located by {1} . Root cause: {2}",
                        sourceElement, destinationElement, e.getMessage()));
            }

        }
    }

    public Object executeJavascript(String script, Object... args) {
//        WebDriver driver = driverManager.getDriver();
        try {
            if (args != null) {
                logger.info(
                        MessageFormat.format("Executing script ''{0}'' with argument ''{1}''", script, args));
            } else {
                logger.info(MessageFormat.format("Executing script ''{0}''", script));
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object result = js.executeScript(script, args);
            if (args != null) {
                logger.info(
                        MessageFormat.format("Executed script ''{0}'' with argument ''{1}'' successfully",
                                script, args));
            } else {
                logger.info(MessageFormat.format("Executed script ''{0}'' successfully", script));
            }
            return result;
        } catch (Exception e) {
            if (args != null) {
                logger.error(MessageFormat.format(
                        "Cannot execute script ''{0}'' with argument ''{1}''. Root cause: {2}", script, args,
                        e.getMessage()));
            } else {
                logger.error(MessageFormat.format("Cannot execute script ''{0}''. Root cause: {1}", script,
                        e.getMessage()));
            }
        }
        return null;
    }

    @Attachment(value = "ScreenShot", type = "image/png")
    public byte[] takeScreenShot() {
//        WebDriver driver = driverManager.getDriver();
        byte[] image = null;
        try {
            logger.info("Taking screenshot");
            image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            logger.info("Took screenshot successfully");
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot take screenshot. Root cause: {1}", e.getMessage()));
        }
        return image;
    }

    @Attachment(value = "ScreenShot", type = "image/png")
    public byte[] takeScreenShotAndHighLightElement(String locator) {
//        WebDriver driver = driverManager.getDriver();
        byte[] image = null;
        try {
            logger.info(MessageFormat.format("Highlight web element located by ''{0}'' and taking screenshot", locator));
            WebElement we = findElement(locator);
//      executeJavascript(,
//          Arrays.asList(we));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", we);
            image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            logger.info("Taken screenshot successfully");
            js.executeScript("arguments[0].setAttribute('style', '');", we);
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot take screenshot. Root cause: {1}", e.getMessage()));
        }
        return image;
    }

    public void scrollToElement(String locator) {
//        WebDriver driver = driverManager.getDriver();
        WebElement we = findElement(findBy(locator));
        try {
            logger.info(MessageFormat.format("Scrolling in to web element located by ''{0}''", locator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", we);
            logger.info(MessageFormat.format("Scroll in to web element located by ''{0}'' successfully", locator));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot scroll web element located by ''{0}''", locator));
        }


    }

    public void mouseOver(String locator) {
//        WebDriver driver = driverManager.getDriver();
        WebElement element = findElement(locator);
        if (element != null)
            ;
        {
            try {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).perform();
                logger.info("Mouse over element:", element);
            } catch (Exception e) {
                logger.error(MessageFormat.format("Can not mouse over element {0}.Root cause{1}", element,
                        e.getMessage()));
            }
        }
    }

    public void getTextToolTip(String locator) {
        WebElement element = findElement(locator);
        String expectedText;

        if (element != null) {
            try {
                expectedText = element.getText();

                logger.info("Got value '" + expectedText + "' from element: " + locator.toString());
                //System.out.println("Retrieved tooltip text as :" +title);
            } catch (Exception e) {
                logger.error("Cannot get value from element: " + locator.toString() + ". Root cause: "
                        + e.getMessage());
            }
        }
    }

/*    public boolean uploadFile(String filePath) {
        WebDriver driver = driverManager.getDriver();
        WebElement dropArea = findElement(findBy(UploadFileRepo.BTN_UPLOAD_FILE));
        Actions actions = new Actions(driver);
        actions.moveToElement(dropArea).perform();
        WebElement fileInput = findElement(findBy(UploadFileRepo.BTN_UPLOAD_FILE));
        fileInput.sendKeys(filePath);
        actions.release().perform();
        takeScreenShotAndHighLightElement(UploadFileRepo.BTN_UPLOAD_FILE);
        delayInSecond(3);
        return true;
    }*/

    public void switchToIFrame(String locator) {
//        WebDriver driver = driverManager.getDriver();
        WebElement we = findElement(findBy(locator));
        logger.info(MessageFormat.format("Switching to frame located by ''{0}''", locator));
        try {
            driver.switchTo().frame(we);
            logger.info(MessageFormat.format("Switched to iframe located by ''{0}'' successfully", locator));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot switch to iframe located by ''{0}''. Root cause: {1}",
                            locator, e.getMessage()));
        }
    }

    public void switchToDefaultContext() {
//        WebDriver driver = driverManager.getDriver();
        logger.info("Switching to default context");
        try {
            driver.switchTo().defaultContent();
            logger.info("Switched to default context successfully");
        } catch (Exception e) {
            logger.error(MessageFormat.format("Cannot switch to default context. Root cause: {0}", e.getMessage()));
        }
    }

}

