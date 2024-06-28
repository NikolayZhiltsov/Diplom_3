package praktikum.stellarBurgerPOM;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

//Класс локаторов и мтеодов главной страницы
public class MainPage {
    private WebDriver webDriver;

    //Локатор надписи Соберите бургер
    private By costructBurgerLocator = By.xpath("//*[text()='Соберите бургер']");

    //Локатор кнопки Войти в аккаунт
    private By loginInAccountButtonLocator = By.xpath("//*[text()='Войти в аккаунт']");

    //Локатор кнопки Личный кабинет
    private By accountButtonLocator = By.xpath("//*[text()='Личный Кабинет']");

    //Локатор вкладки Булки
    private By bunsTabLocator = By.xpath("//*[@class = 'text text_type_main-default' and text()='Булки']");

    //Локатор вкладки Соусы
    private By saucesTabLocator = By.xpath("//*[@class = 'text text_type_main-default' and text()='Соусы']");

    //Локатор вкладки Начинки
    private By fillingsTabLocator = By.xpath("//*[@class = 'text text_type_main-default' and text()='Начинки']");

    //Локатор выбранного раздела Булки
    private By bunsListLocator = By.xpath("//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");

    //Локатор выбранного раздела Соусы
    private By saucesListLocator = By.xpath("//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //Локатор выбранного раздела Начинки
    private By fillingsListLocator = By.xpath("//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.webDriver = driver;
    }

    @Step("Шаг клика по кнопке Войти в аккаунт")
    public void clickLoginButton() {
        WebElement clickLogin = webDriver.findElement(loginInAccountButtonLocator);
        clickLogin.click();
    }

    @Step("Шаг клика по кнопке Личный кабинет")
    public void clickAccountButton() {
        WebElement clickAccount = webDriver.findElement(accountButtonLocator);
        clickAccount.click();
    }

    @Step("Шаг выбора вкладки Булки")
    public boolean selectBunsTab() {
        WebElement bunsTab = webDriver.findElement(bunsTabLocator);
        bunsTab.click();
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(bunsListLocator));
        WebElement bunsList = webDriver.findElement(bunsTabLocator);
        bunsList.isDisplayed();
        return true;
    }

    @Step("Шаг выбора вкладки Соусы")
    public boolean selectSaucesTab() {
        WebElement saucesTab = webDriver.findElement(saucesTabLocator);
        saucesTab.click();
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(saucesListLocator));
        WebElement saucesList = webDriver.findElement(saucesListLocator);
        saucesList.isDisplayed();
        return true;
    }

    @Step("Шаг выбора вкладки Ничинки")
    public boolean selectFillingsTab() {
        WebElement fillingsTab = webDriver.findElement(fillingsTabLocator);
        fillingsTab.click();
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fillingsListLocator));
        WebElement fillingsList = webDriver.findElement(fillingsListLocator);
        fillingsList.isDisplayed();
        return true;
    }

    @Step("Шаг ожидания отображения надписи Соберите бургер")
    public boolean constructBurgerIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(costructBurgerLocator));
        WebElement construct = webDriver.findElement(costructBurgerLocator);
        construct.isDisplayed();
        return true;
    }
}
