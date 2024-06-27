package praktikum.stellarBurgerPOM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс локаторов и методов страницы регистрации пользователя
public class RegisterPage {
    private WebDriver webDriver;

    //Локатор надписи Регистрация
    private By registerTextLocator = By.xpath("//*[text()='Регистрация']");

    //Локатор поля Имя
    private By nameFieldLocator = By.xpath("//div/label[text()='Имя']/ancestor::div/input[@name='name']");

    //Локатор поля Email
    private By emailFieldLocator = By.xpath("//div/label[text()='Email']/ancestor::div/input[@name='name']");

    //Локатор поля Пароль
    private By passwordFieldLocator = By.xpath("//div/label[text()='Пароль']/ancestor::div/input[@name='Пароль']");

    //Локатор кнопки Зарегистрироваться
    private By registerButtonLocator = By.xpath("//*[text()='Зарегистрироваться']");

    //Локатор ссылки для перехода на форму входа
    private By enterLinkLocator = By.xpath("//*[text()='Войти']");

    //Локатор надписи Некорректный пароль
    private By incorrectPasswordTextLocator = By.xpath("//*[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Шаг заполнения полей Имя, Email и пароль")
    public void fillRegisterInfo(String name, String email, String password) {
        WebElement nameInput = webDriver.findElement(nameFieldLocator);
        nameInput.sendKeys(name);

        WebElement emailInput = webDriver.findElement(emailFieldLocator);
        emailInput.sendKeys(email);

        WebElement passwordInput = webDriver.findElement(passwordFieldLocator);
        passwordInput.sendKeys(password);
    }

    @Step("Шаг клика по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        WebElement buttonRegister = webDriver.findElement(registerButtonLocator);
        buttonRegister.click();
    }

    @Step("Шаг клика по ссылке Войти")
    public void clickEnterLink() {
        WebElement enter = webDriver.findElement(enterLinkLocator);
        enter.click();
    }

    @Step("Шаг ожидания отображения надписи Регистрация")
    public boolean registerIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(registerTextLocator));
        WebElement textRegister = webDriver.findElement(registerTextLocator);
        textRegister.isDisplayed();
        return true;
    }

    @Step("Шаг ожидания отображения надписи Некорректный пароль")
    public boolean incorrectPasswordIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordTextLocator));
        WebElement textIncorrectPassword = webDriver.findElement(incorrectPasswordTextLocator);
        textIncorrectPassword.isDisplayed();
        return true;
    }
}
