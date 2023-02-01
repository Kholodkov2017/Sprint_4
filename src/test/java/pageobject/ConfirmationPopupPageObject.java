package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import static helpers.Constants.WAITING_TIMEOUT;

public class ConfirmationPopupPageObject extends PageObjectBase {
    public ConfirmationPopupPageObject(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Кнопка 'Да' на форме потдверждения заказа
     */
    @FindBy(xpath = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']")
    WebElement yesButton;

    /**
     * Кнопка 'Нет' на форме подтверждения заказа
     */
    @FindBy(xpath = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Нет']")
    WebElement noButton;

    /**
     * Заголовок формы подтверждения заказа
     */
    @FindBy(xpath = ".//div[@class='Order_Modal__YZ-d3']/" +
            "div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']")
    WebElement confirmationPopupTitle;

    /**
     * Метод имитирующий ожидание момента отображения пупапа подтверждения
     */
    public void waiteUntilOrderFormWillDisplayed() {
        new WebDriverWait(driver, WAITING_TIMEOUT).until(driver -> (
                confirmationPopupTitle.isDisplayed()));
    }

    /**
     * Метод проверки отображения заголовка всплывающего окна
     * @return Отображен ли заголовок или нет
     */
    public boolean isPopUpDisplayed() {
        return confirmationPopupTitle.isDisplayed();
    }

    /**
     * Клик по кнопке да на странице подтверждения заказа
     */
    public void clickYesButton() {
        yesButton.click();
    }

    /**
     * Клик по кнопке нет на странице пдтверждения заказа
     */
    public void clickNoButton() {
        noButton.click();
    }

}
