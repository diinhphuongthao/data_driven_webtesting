package untils.keywords;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class ExcelUtils {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;
    private int currentRow = 0;
    private Map<String, Integer> columnMapping = new HashMap<>();

    public ExcelUtils(String path) throws IOException {

        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);

            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Map<String, String> getAllTestData() {
        Map<String, String> testCaseData = new HashMap<>();
        String currentTestCase = null;
        StringBuilder testData = new StringBuilder();

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow currentRow = sheet.getRow(rowNum);
            if (currentRow != null) {
                XSSFCell idCell = currentRow.getCell(0);
                if (idCell != null && idCell.getCellType() == CellType.STRING) {
                    String testCaseId = idCell.getStringCellValue();
                    if (!testCaseId.equals(currentTestCase)) {
                        // Lưu kết quả của testcase trước (nếu có)
                        if (currentTestCase != null) {
                            testCaseData.put(currentTestCase, testData.toString().trim());
                        }
                        // Bắt đầu testcase mới
                        currentTestCase = testCaseId;
                        testData = new StringBuilder(currentTestCase + " has ");
                    }

                    for (int columnIndex = 1; columnIndex < currentRow.getLastCellNum(); columnIndex++) {
                        XSSFCell cell = currentRow.getCell(columnIndex);
                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            XSSFRow headerRow = sheet.getRow(0);
                            if (headerRow != null) {
                                XSSFCell headerCell = headerRow.getCell(columnIndex);
                                if (headerCell != null && headerCell.getCellType() == CellType.STRING) {
                                    String columnName = headerCell.getStringCellValue();
                                    String cellValue = cell.getStringCellValue();
                                    testData.append(columnName).append(": ").append(cellValue).append(" , ");
                                }
                            }
                        }
                    }
                }
            }
        }

        // Lưu kết quả của testcase cuối cùng
        if (currentTestCase != null) {
            testCaseData.put(currentTestCase, testData.toString().trim());
        }

        return testCaseData;
    }

    // Phương thức để đọc dữ liệu theo id testcase và trả về theo dạng yêu cầu
    public String getTestData1(String idTestCase) {
        StringBuilder testData = new StringBuilder();

        boolean isInsideTestCase = false;

        // Kiểm tra xem hàng đầu tiên (header) có tồn tại
        XSSFRow headerRow = sheet.getRow(0);
        if (headerRow != null) {
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow = sheet.getRow(rowNum);
                if (currentRow != null) {
                    XSSFCell idCell = currentRow.getCell(0);
                    if (idCell != null && idCell.getCellType() == CellType.STRING) {
                        String testCaseId = idCell.getStringCellValue();
                        if (testCaseId.equals(idTestCase)) {
                            if (!isInsideTestCase) {
                                testData.append(idTestCase).append(" has ");
                                isInsideTestCase = true;
                            }

                            for (int columnIndex = 1; columnIndex < currentRow.getLastCellNum(); columnIndex++) {
                                XSSFCell cell = currentRow.getCell(columnIndex);
                                if (cell != null && cell.getCellType() == CellType.STRING) {
                                    // Kiểm tra xem cột trong header có tồn tại
                                    XSSFCell headerCell = headerRow.getCell(columnIndex);
                                    if (headerCell != null && headerCell.getCellType() == CellType.STRING) {
                                        String columnName = headerCell.getStringCellValue();
                                        String cellValue = cell.getStringCellValue();
                                        testData.append(columnName).append(": ").append(cellValue).append(" , ");
                                    }
                                }
                            }
                        } else if (isInsideTestCase) {
                            break;
                        }
                    }
                }
            }
        }

        return testData.toString().trim().replaceAll(",$", "");
    }


    public Map<String, Map<String, String>> getAllData() {
        Map<String, Map<String, String>> allData = new HashMap<>();

        Iterator<Row> rowIterator = sheet.iterator();
        String currentId = null;
        Map<String, String> rowData = new HashMap<>();
        boolean readingData = false;
        List<String> columnNames = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell idCell = row.getCell(0);

            if (idCell != null && idCell.getCellType() == CellType.STRING) {
                if (currentId != null) {
                    allData.put(currentId, rowData);
                }
                currentId = idCell.getStringCellValue();
                rowData = new HashMap<>();
                readingData = true;

                columnNames.clear();
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    Cell headerCell = row.getCell(j);
                    String header = (headerCell != null && headerCell.getCellType() == CellType.STRING) ?
                            headerCell.getStringCellValue() : "Column_" + j;
                    columnNames.add(header);
                }
            } else if (readingData) {
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    if (j <= columnNames.size()) {
                        Cell dataCell = row.getCell(j);
                        String header = columnNames.get(j - 1);
                        String data = (dataCell != null) ? getDataValueAsString(dataCell) : "";
                        rowData.put(header, data);
                    }
                }
            }
        }

        if (currentId != null) {
            allData.put(currentId, rowData);
        }

        return allData;
    }

    private String getDataValueAsString(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return "";
        }
    }

    public Map<String, String> getDataById(String targetId) {
        Map<String, String> resultData = new HashMap<>();

        Map<String, Map<String, String>> allData = getAllData();

        if (allData.containsKey(targetId)) {
            resultData = allData.get(targetId);
        }

        return resultData;
    }

    public String getColumnValueByIdAndColumnName(String targetId, String columnName) {
        Map<String, String> dataForId = getDataById(targetId);

        if (dataForId.containsKey(columnName)) {
            return dataForId.get(columnName);
        } else {
            return "Column not found";
        }
    }

    // returns the row count in a sheet
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }

    }


    // returns the data from a cell
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {

                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + 1 + "/" +
                            cellText;
                }
                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }


    // returns the data from a cell
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText =
                            (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;


                }


                return cellText;
            } else if (cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }


    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;


            sheet = workbook.getSheetAt(index);


            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);


            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {

        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;


            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {

                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;
            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            //cell style for hyperlinks

            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(XSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            //hlink_style.setWrapText(true);

            XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // returns true if sheet is created successfully else false
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully else false if sheet does not exist
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if column is created successfully
    public boolean addColumn(String sheetName, String colName) {


        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);


            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }


    // removes a column and all the contents
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName))
                return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(FillPatternType.NO_FILL);


            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    // find whether sheets exists
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }


    // returns number of columns in a sheet
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();


    }


    //String sheetName, String testCaseName,String keyword ,String URL,String message
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {


        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {

                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }


        return true;
    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }

    public List<List<String>> getTestData(String sheetName) {
        List<List<String>> testDataList = new ArrayList<>();

        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index != -1) {
                Sheet sheet = workbook.getSheetAt(index);
                Iterator<Row> rowIterator = sheet.iterator();

                // Skip the header row if needed
                if (rowIterator.hasNext()) {
                    rowIterator.next(); // Skip the header row
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.iterator();
                    List<String> rowData = new ArrayList<>();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = "";

                        // Handle different cell types as needed (STRING, NUMERIC, etc.)
                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(cell.getNumericCellValue());
                                break;
                            // Add more cases for other cell types if necessary
                        }

                        rowData.add(cellValue);
                    }

                    testDataList.add(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testDataList;
    }

    public String findValueInSheet(String sheetName, String targetValue) {
        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index != -1) {
                Sheet sheet = workbook.getSheetAt(index);
                Iterator<Row> rowIterator = sheet.iterator();

                // Skip the header row if needed
                if (rowIterator.hasNext()) {
                    rowIterator.next(); // Skip the header row
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.iterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = "";

                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(cell.getNumericCellValue());
                                break;

                        }
                        if (cellValue.equals(targetValue)) {
                            return cellValue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean findTestCaseStart(String idTestCase) {
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow currentRow = sheet.getRow(rowNum); // Đảm bảo sử dụng biến currentRow ở đây
            if (currentRow != null) {
                XSSFCell idCell = currentRow.getCell(columnMapping.get("idtestcase")); // Sử dụng ánh xạ cột
                if (idCell != null && idCell.getCellType() == CellType.STRING) {
                    String testCaseId = idCell.getStringCellValue();
                    if (testCaseId.equals(idTestCase)) {
                        this.currentRow = rowNum; // Lưu lại dòng bắt đầu của testcase
                        return true;
                    }
                }
            }
        }
        return false;
    }


}







