package untils.helper;

import org.slf4j.Logger;

import java.io.File;

public class FileHelper {


    private static final String EXCEL_EXTENSION = ".xlsx";
    private static String TEST_DATA_FOLDER = "testdata";
    private static String RESOURCE_FOLDER = "resources";

    private static String JSON_EXTENSION = ".json";

    private static String SOURCE_FOLDER = "src";

    private static String MAIN_FOLDER = "main";

    private static String USER_DIR = "user.dir";
    private static Logger logger = LogHelper.getLogger();

    public static String getDataJSONFilePath(String dataJsonName) {
        logger.info(System.getProperty(USER_DIR) + File.separator + SOURCE_FOLDER + File.separator + MAIN_FOLDER
                + File.separator + RESOURCE_FOLDER + File.separator + TEST_DATA_FOLDER + File.separator
                + dataJsonName + JSON_EXTENSION);
        return System.getProperty(USER_DIR) + File.separator + SOURCE_FOLDER + File.separator + MAIN_FOLDER
                + File.separator + RESOURCE_FOLDER + File.separator + TEST_DATA_FOLDER + File.separator
                + dataJsonName + JSON_EXTENSION;
    }

    public static String getDataExcelFilePath(String dataExcelName) {
        String filePath = USER_DIR + File.separator + SOURCE_FOLDER + File.separator + MAIN_FOLDER + File.separator +
                RESOURCE_FOLDER + File.separator + TEST_DATA_FOLDER + File.separator + dataExcelName + EXCEL_EXTENSION;
        return filePath;
    }

}

