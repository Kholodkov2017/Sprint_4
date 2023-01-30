package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Constants.WAITING_TIMEOUT;


public class QuestionPageObject extends PageObjectBase {
    private static final String ACCORDION_HEADING_ID_PART = "accordion__heading-";
    private static final String ACCORDION_PANEL_ID_PART = "accordion__panel-";

    // Заголовок секции вопросов о важном
    private By questionTitle =
            By.xpath(".//div[@class='Home_FourPart__1uthg']/div[@class='Home_SubHeader__zwi_E']");

    // Аккордион секции о важном
    private By questionAccordionComponent = By.className("accordion");

    // Локатор получения элементов аккордиона
    private By accordionItems = By.xpath(".//div[@data-accordion-component='AccordionItem']");

    /**
     * Метод генерации локатора для конретного вопроса
     * @param index - индекс элемента аккордеона
     * @return возвращает объект локатора
     */
    private By getAccordionHeadingByIndex(int index) {
        return By.id(ACCORDION_HEADING_ID_PART + index);
    }

    /**
     * Метод генерации локатора для конкретного ответа
     * @param index - индекс элемента аккордеона
     * @return возвращает объект локатора
     */
    private By getAccordionPanelByIndex(int index) {
        return By.id(ACCORDION_PANEL_ID_PART + index);
    }

    /**
     * Конструктор Page Object для домашней страницы
     * @param driver - объект драйвера
     */
    public QuestionPageObject(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для получения количества вопросов в аккордеоне
     * @return возвращает количество вопросов в аккордеоне
     */
    public int getQuestionsCount() {
        return driver.findElements(accordionItems).size();
    }

    /**
     * Метод для получения текста заголовка секции с вопросами
     * @return возращает текст заголовка секции с вопросами
     */
    public String getQuestionTitleText() {
        return driver.findElement(questionTitle).getText();
    }

    /**
     * Метод для получения текста вопроса по индексу элемента
     * @param index - индекс элемента в аккордеоне
     * @return возвращает текст вопроса по индексу
     */
    public String getQuestionTextByIndex(int index) {
        return driver.findElement(getAccordionHeadingByIndex(index)).getText();
    }

    /**
     * Метод для получения текста ответа на вопрос
     * @param index - индекс элемента в аккордеоне
     * @return возвращает текст ответа на вопрос
     */
    public String getQuestionAnswerByIndex(int index) {
        return driver.findElement(getAccordionPanelByIndex(index)).getText();
    }

    /**
     * Метод для имитации клика по заголовку аккордеона
     * @param index - индекс элемента в аккордеоне
     */
    public void clickToAccordionHeading(int index) {
        driver.findElement(getAccordionHeadingByIndex(index)).click();
    }


    /**
     * Метод ожидающий, пока домашняя строница прогрузится
     */
    public  void waitUntilHomePageWillBeLoaded() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Метод имитирующий ожидание момента отображения ответа
     * @param index - индекс элемента в аккордеоне
     */
    public void waitForAnswerDisplayed(int index) {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(driver -> (
                driver.findElement(getAccordionPanelByIndex(index)).isDisplayed()));
    }


    /**
     * Метод, проверяющий, была ли закрыта панель с ответом
     * @param index - индекс предыдущего элемента аккордеона
     * @return возвращает признак отображения элемента
     */
    public boolean checkIsPanelClosed(int index) {
        return !driver.findElement(getAccordionPanelByIndex(index)).isDisplayed();
    }

    /**
     * Метод, проверяющий, была ли открыта панель с ответом
     * @param index - индекс предыдущего элемента аккордеона
     * @return возвращает признак отображения элемента
     */
    public boolean checkIsPanelOpen(int index) {
        return driver.findElement(getAccordionPanelByIndex(index)).isDisplayed();
    }

    /**
     * Метод для скроллинга к аккордеону с вопросами
     */
    public void scrollToQuestionSection() {
        WebElement element = driver.findElement(questionAccordionComponent);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
