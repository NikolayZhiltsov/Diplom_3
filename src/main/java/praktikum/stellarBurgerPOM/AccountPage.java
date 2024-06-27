package praktikum.stellarBurgerPOM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс локаторов и методов страницы Личного кабинета
public class AccountPage {
    private WebDriver webDriver;

    //Локатор надписи Профиль
    private By profileTextLocator = By.xpath("//*[text()='Профиль']");

    //Локатор кнопки Конструктор
    private By constructorButtonLocator = By.xpath("//*[text()='Конструктор']");

    //Локатор логотипа Stellar Burgers
    private By stellarBurgersLogoLocator = By.xpath("//div/a");

    //Локатор кнопки Выход
    private By exitButtonLocator = By.xpath("//*[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Шаг клика по кнопке Конструктор")
    public void clickConstructorButton() {
        WebElement clickConstructor = webDriver.findElement(constructorButtonLocator);
        clickConstructor.click();
    }

    @Step("Шаг клика по лого Stellar Burgers")
    public void clickStellarBurgersLogo() {
        WebElement clickLogo = webDriver.findElement(stellarBurgersLogoLocator);
        clickLogo.click();
    }

    @Step("Шаг клика по кнопке Выход")
    public void clickExitButton() {
        WebElement clickExit = webDriver.findElement(exitButtonLocator);
        clickExit.click();
    }

    @Step("Шаг ожидания отображения надписи Профиль")
    public boolean profileIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(profileTextLocator));
        WebElement textProfile = webDriver.findElement(profileTextLocator);
        textProfile.isDisplayed();
        return true;
    }
}
