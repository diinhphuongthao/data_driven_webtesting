package demoqa.repo;

public class DateSpickerRepo {
    public static final String TXT_INPUT_DATE = "//input[@id='datePickerMonthYearInput']";
    public static final String LBL_MONTH_DATE = "//select[@class='react-datepicker__month-select']";
    public static final String DROPDOW_MONTH_DATE = "//select[@class=\"react-datepicker__month-select\"]/option";
    public static final String LBL_YEAR_DATE = "//select[@class='react-datepicker__year-select']";
    public static final String DROPDOW_YEAR_DATE = "//select[@class=\"react-datepicker__year-select\"]/option";
    public static final String TXT_INPUT_DATE_AND_TIME = "//input[@id='dateAndTimePickerInput']";
    public static final String LBL_MONTH = "//div[@class='react-datepicker__month-read-view']";
    public static final String DROPDOW_MONTH = "//div[@class='react-datepicker__month-dropdown']//div[@class='react-datepicker__month-option']";
    public static final String LBL_YEAR = "//div[@class='react-datepicker__year-read-view']";
    public static final String FIRST_OPTION_YEAR = "//div[@class='react-datepicker__year-dropdown']//div[2]";
    public static final String BTN_NEXT_YEAR = "//div[@class='react-datepicker__year-dropdown']//div[1]";
    public static final String DROPDOW_YEAR = "//div[@class='react-datepicker__year-dropdown']//div[@class='react-datepicker__year-option']";
    public static final String LBL_DAY = "//div[@class=\"react-datepicker__month\"]//div[contains(@class, \"react-datepicker__day\")][position() <= 35]";
    public static final String LBL_LIST_TIME = "//ul[@class='react-datepicker__time-list']/li";
    public static final String PREVIOUS_YEAR_BUTTON = "//a[contains(@class,'react-datepicker__navigation react-datepicker__navigation--years react-datepicker__navigation--years-previous')]";
    public static final String LAST_OPTION_YEAR = "//div[contains(@class,'react-datepicker__year-dropdown-container react-datepicker__year-dropdown-container--scroll')]//div[contains(@class,'react-datepicker__year-dropdown')]//div[12]";
}
