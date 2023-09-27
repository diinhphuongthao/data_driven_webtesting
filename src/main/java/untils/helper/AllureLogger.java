package untils.helper;

import io.qameta.allure.Attachment;

import java.text.MessageFormat;

public class AllureLogger {

    @Attachment(value = "{0}", type = "text/plain")
    public static String info(String message) {
        return MessageFormat.format("INFO: {0}", message);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String error(String message) {
        return MessageFormat.format("ERROR: {0}", message);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String warn(String message) {
        return MessageFormat.format("WARN: {0}", message);
    }
}
