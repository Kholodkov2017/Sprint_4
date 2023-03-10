package pageobject;

import model.OrderModel;
import helpers.OrderButtonEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Constants.WAITING_TIMEOUT;

public class OrderPageObject extends PageObjectBase {
    public OrderPageObject(WebDriver driver) {
        super(driver);
    }

    //region Плейсхолдеры элементов первой ('Для кого самокат') и второй ('Про аренду') форм
    private static final String FIRSTNAME_FIELD_PLACEHOLDER = "* Имя";
    private static final String LASTNAME_FIELD_PLACEHOLDER = "* Фамилия";
    private static final String ADDRESS_FIELD_PLACEHOLDER = "* Адрес: куда привезти заказ";
    private static final String METRO_STATION_FIELD_PLACEHOLDER = "* Станция метро";
    private static final String PHONE_FIELD_PLACEHOLDER = "* Телефон: на него позвонит курьер";

    private static final String CALENDAR_FIELD_PLACEHOLDER = "* Когда привезти самокат";

    private static final String RENT_PERIOD_FIELD_PLACEHOLDER = "* Срок аренды";

    private static final String SCOOTER_COLOR_FIELD_PLACEHOLDER = "Цвет самоката";

    private static final String COURIER_COMMENT_FIELD_PLACEHOLDER = "Комментарий для курьера";
    //endregion

    /**
     * Кнопка 'Заказать' в хедере главной страницы
     */
    private final By headerOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']" +
            "/button[@class='Button_Button__ra12g' and text()='Заказать']");

    /**
     * Кнопка 'Заказать' в секции 'Как это работает'
     */
    private final By howItWorksSectionOrderButton = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/" +
            "button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");

    /**
     * Кнопка 'Заказать' на форме 'Про ареднду'
     */
    private final By rentPageOrderButton =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    /**
     * Кнопка 'Назад' на форме 'Про аренду'
     */
    private final By rentPageBackButton =
            By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");


    //region Заголовки первой и второй форм оформления заказа
    private final By orderHeaderTitle = By.xpath(".//div[@class='Order_Content__bmtHS']/" +
            "div[@class='Order_Header__BZXOb' and text() = 'Для кого самокат']");
    private final By aboutRentTitle =
            By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
    //endregion

    //region Локаторы элементов формы
    private final By firstNameInput = By.xpath(generateFormFieldXPath(FIRSTNAME_FIELD_PLACEHOLDER));
    private final By lastNameInput = By.xpath(generateFormFieldXPath(LASTNAME_FIELD_PLACEHOLDER));
    private final By addressInput = By.xpath(generateFormFieldXPath(ADDRESS_FIELD_PLACEHOLDER));
    private final By metroStationInput = By.xpath(generateFormFieldXPath(METRO_STATION_FIELD_PLACEHOLDER));
    private final By phoneInput = By.xpath(generateFormFieldXPath(PHONE_FIELD_PLACEHOLDER));
    private final By calendarInput = By.xpath(generateFormFieldXPath(CALENDAR_FIELD_PLACEHOLDER));
    private final By rentPeriodInput = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final By scooterColourInput = By.xpath(generateFormFieldXPath(SCOOTER_COLOR_FIELD_PLACEHOLDER));
    private final By courierCommentInput = By.xpath(generateFormFieldXPath(COURIER_COMMENT_FIELD_PLACEHOLDER));
    //endregion

    /**
     * Локатор кнопки 'Далее' на форме 'Для кого самокат'
     */
    private  final By nextOrderButton =
            By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");

    /**
     * Метод, генерирущий локаторы элементов формы
     * @param placeholderText - плейсхолдер элемента формы
     * @return - локатор элемента формы
     */
    private static String generateFormFieldXPath(String placeholderText) {
        return ".//input[@placeholder='"+ placeholderText+ "']";
    }

    /**
     * Метод, выбирающий станцию метро из выпадающего списка
     * @param dataIndex - индекс нужной станции
     * @return локатор для выбранного варианта станци
     */
    private WebElement selectMetroStation(String dataIndex) {
        return driver.findElement(By.xpath(".//ul[@class='select-search__options']" +
                "/li[@data-index='"+dataIndex+"']"));
    }

    /**
     * Метод ожидающий, пока домашняя строница прогрузится
     */
    public  void waitUntilHomePageWillBeLoaded() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Метод, проверяющий, открылась ли форма 'Для кого самокат'
     */
    public boolean checkIsOrderFormOpen() {
        return driver.findElement(orderHeaderTitle).isDisplayed();
    }

    /**
     * Метод имитирующий ожидание момента отображения формы заказа самоката
     */
    public void waiteUntilOrderFormWillDisplayed() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(driver -> (
                driver.findElement(orderHeaderTitle).isDisplayed()));
    }

    /**
     * Метод имитирующий ожидание момента отображения формы 'Про ренту'
     */
    public void waiteUntilRentFormWillDisplayed() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(driver -> (
                driver.findElement(aboutRentTitle).isDisplayed()));
    }

    /**
     * Метод для скроллинга к кнопке закакзать секции "Как это работает"
     */
    public void scrollToHowItWorksOrderButton() {
        WebElement element = driver.findElement(howItWorksSectionOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Клик по кнопке заказа в секции заголовка
     */
    public void clickToOrderButtonInHeaderSection() {
        driver.findElement(headerOrderButton).click();
    }

    /**
     * Клик по кнопке заказа в секции "Как это работает"
     */
    public void clickToOrderButtonInHowItWorksSection() {
        scrollToHowItWorksOrderButton();
        driver.findElement(howItWorksSectionOrderButton).click();
    }

    /**
     * Метод, заполняющий данные формы 'Для кого самокат'
     * @param inputData - сгенирированные данные для формы
     */
    public void fillFirstPartOrderForm(OrderModel inputData) {
        driver.findElement(firstNameInput).sendKeys(inputData.getFirstName());
        driver.findElement(lastNameInput).sendKeys(inputData.getLastName());
        driver.findElement(addressInput).sendKeys(inputData.getAddress());
        driver.findElement(phoneInput).sendKeys(inputData.getPhone());
        if (!inputData.getMetroStation().isEmpty()) {
            driver.findElement(metroStationInput).click();
            selectMetroStation(inputData.getMetroStation()).click();
        }
    }

    /**
     * Метод, заполняющий форму 'Про аренду'
     * @param inputData - сгенерированные данные для формы
     */
    public void fillTheSecondPartOrderForm(OrderModel inputData) {
        driver.findElement(calendarInput).click();
        driver.findElement(By.xpath(".//button[@aria-label='"+inputData.getOrderMonth()+"']")).click();
        driver.findElement(By.xpath(".//div[@role='button' and contains(text(), '"
                + inputData.getDeliveryDate() + "')]")).click();
        driver.findElement(rentPeriodInput).click();
        if (!inputData.getRentPeriod().isEmpty()) {
            driver.findElement(By.xpath( ".//div[@class='Dropdown-menu']/" +
                    "div[text()='"+inputData.getRentPeriod()+"']")).click();
        }
        driver.findElement(By.xpath(".//label[@for='black']")).click();
        driver.findElement(courierCommentInput).sendKeys(inputData.getDeliveryDate());
    }

    /**
     * Клик по кнопке 'Далее' на форме 'Для кого самокат'
     */
    public void clickToNextOrderStepButton() {
        driver.findElement(nextOrderButton).click();
    }

    /**
     * Клик по кнопке 'Заказать' на странице 'Про аренду'
     */
    public void clickToOrderButtonOnRentSection() {
        driver.findElement(rentPageOrderButton).click();
    }

    /**
     * Метод, проверяющий, отобразилась ли форма 'Про аренду'
     * @return
     */
    public boolean checkIsRentStepDisplayed() {
        return driver.findElement(aboutRentTitle).isDisplayed();
    }

    /**
     * Шаг, обрабатывающий форму 'Для кого самокат'
     * @param inputData - сгенерированые данные для формы
     * @param buttonType - тип кнопки 'Заказать' (в хедере или в секции 'Как это работает')
     */
    public void fillTheFirstPartOrderForm(OrderModel inputData, OrderButtonEnum buttonType) {
        clickToCookieButton();

        if (buttonType.equals(OrderButtonEnum.rentSectionOrderButton)) {
            scrollToHowItWorksOrderButton();
            clickToOrderButtonInHowItWorksSection();
        } else {
            clickToOrderButtonInHeaderSection();
        }

        waiteUntilOrderFormWillDisplayed();

        fillFirstPartOrderForm(inputData);

        clickToNextOrderStepButton();
    }
}
