package praktikum.stellarBurgerPOM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс локаторов и методов страницы входа в личный кабинет
public class LoginPage {
    private WebDriver webDriver;

    //Локатор надписи Вход
    private By enterTextLocator = By.xpath("//*[text()='Вход']");

    //Локатор поля Email
    private By emailFieldLocator = By.xpath("//*[@name='name']");

    //Локатор поля Пароль
    private By passwordFieldLocator = By.xpath("//*[@name='Пароль']");

    //Локатор кнопки Войти
    private By enterButtonLocator = By.xpath("//*[text()='Войти']");

    //Локатор ссылки для перехода на форму регистрации
    private By registerLinkLocator = By.xpath("//*[text()='Зарегистрироваться']");

    //Локатор ссылки для перехода на форму восстановления пароля
    private By resetPasswordLinkLocator = By.xpath("//*[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Шаг заполнения полей Email и Пароль")
    public void fillLoginInfo (String email, String password) {
        WebElement emailInput = webDriver.findElement(emailFieldLocator);
        emailInput.sendKeys(email);

        WebElement passwordInput = webDriver.findElement(passwordFieldLocator);
        passwordInput.sendKeys(password);
    }

    @Step("Шаг клика по кнопке Войти")
    public void clickEnterButton() {
        WebElement buttonEnter = webDriver.findElement(enterButtonLocator);
        buttonEnter.click();
    }

    @Step("Шаг клика по ссылке Зарегистрироваться")
    public void clickRegisterLink() {
        WebElement register = webDriver.findElement(registerLinkLocator);
        register.click();
    }

    @Step("Шаг клика по ссылке Восстановить пароль")
    public void clickResetPasswordLink() {
        WebElement resetPassword = webDriver.findElement(resetPasswordLinkLocator);
        resetPassword.click();
    }

    @Step("Шаг ожидания отображения надписи Вход")
    public boolean enterIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(enterTextLocator));
        WebElement textEnter = webDriver.findElement(enterTextLocator);
        textEnter.isDisplayed();
        return true;
    }
}
