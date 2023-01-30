package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjectBase {
    protected final WebDriver driver;
    protected PageObjectBase(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * Локатор для кнопки согласия на использование куки
     */
    private By cookieButton = By.id("rcc-confirm-button");

    /**
     * Клик по кнопке согласия на использование куки
     */
    public void clickToCookieButton() {
        driver.findElement(cookieButton).click();
    }
}
