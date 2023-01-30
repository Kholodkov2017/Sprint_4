package pageobject;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Constants.WAITING_TIMEOUT;

public class SuccessOrderPopupPageObject extends PageObjectBase {
    /**
     * Заголовок формы успешного заказа
     */
    @FindBy(xpath = ".//div[text() = 'Заказ оформлен']")
    private WebElement successOrderPopupTitle;

    /**
     * Текст формы успешного заказа
     */
    @FindBy(xpath = ".//div[@class='Order_Text__2broi']")
    private WebElement successOrderPopupMessage;

    /**
     * Кнопка 'Просмотреть статус' на форме успешного заказа
     */
    @FindBy(xpath = ".//button[text() = 'Посмотреть статус']")
    private WebElement checkStatusButton;

    public SuccessOrderPopupPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод имитирующий ожидание момента отображения формы успешного выполнения заказа
     */
    public void waiteUntilSuccessOrderPopupWillDisplayed() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(driver -> (
                successOrderPopupTitle.isDisplayed()));

    }

    /**
     * Проверка, был ли заказ оформлен успешно
     * @return - статус успешности оформления заказа
     */
    public boolean checkIsSuccessOrderPopupWillBeDisplayed() {
        return successOrderPopupTitle.isDisplayed();
    }

    /**
     * Метод, получающий номер заказа из текста формы
     * @return - номер заказа
     */
    public String getOrderNumber() {
       try {
            return StringUtils.substringBetween(successOrderPopupMessage.getText(), ": ", ".");
       } catch (Exception e) {
           return "";
       }
    }

    /**
     * Метод клика по кнопке 'Просмотреть статус'
     */
    public void clickToCheckOrderStatusButton() {
        checkStatusButton.click();
    }
}
