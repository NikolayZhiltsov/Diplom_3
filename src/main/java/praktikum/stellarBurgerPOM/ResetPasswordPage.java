package praktikum.stellarBurgerPOM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс лоокаторов и методов страницы восстановления пароля
public class ResetPasswordPage {
    private WebDriver webDriver;

    //Локатор надписи Восстановление пароля
    private By resetPasswordTextLocator = By.xpath("//*[text()='Восстановление пароля']");

    //Локатор ссылки для перехода на форму входа
    private By enterLinkLocator = By.xpath("//*[text()='Войти']");

    public ResetPasswordPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Шаг клика по ссылке Войти")
    public void clickEnterLink() {
        WebElement enter = webDriver.findElement(enterLinkLocator);
        enter.click();
    }

    @Step("Шаг ожидания отображения надписи Восстановление пароля")
    public boolean resetPasswordIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(resetPasswordTextLocator));
        WebElement textResetPassword = webDriver.findElement(resetPasswordTextLocator);
        textResetPassword.isDisplayed();
        return true;
    }
}
