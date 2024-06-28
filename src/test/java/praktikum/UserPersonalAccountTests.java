package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.modelAPI.User;
import praktikum.stellarBurgerPOM.AccountPage;
import praktikum.stellarBurgerPOM.LoginPage;
import praktikum.stellarBurgerPOM.MainPage;
import praktikum.stepsAPI.UserAPISteps;

import static org.junit.Assert.assertTrue;

public class UserPersonalAccountTests {
    private WebDriver webDriver;
    User user = new User();
    UserAPISteps userAPISteps = new UserAPISteps();
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        email = RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@" +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() + "." +
                RandomStringUtils.randomAlphabetic(2).toLowerCase();
        password = RandomStringUtils.randomAlphabetic(6);
        name = RandomStringUtils.randomAlphabetic(10);
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        userAPISteps.createUser(user);
        webDriver = WebDriverFactory.getDriver();
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();
        mainPage.constructBurgerIsDisplayed();
    }

    @Test
    @DisplayName("Проверяем переход залогиненного пользователя в Личный Кабинет с главной страницы")
    public void openPersonalAccountFromMainPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход залогиненного пользователя из Личного Кабинета на конструктор булок по клику на кнопку Конструктор")
    public void openConstructorFromConstructorClickInPersonalAccount() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);
        accountPage.profileIsDisplayed();
        accountPage.clickConstructorButton();

        assertTrue(mainPage.constructBurgerIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход залогиненного пользователя из Личного Кабинета на конструктор булок по клику на логотип Stellar Burgers")
    public void openConstructorFromLogoClickInPersonalAccount() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);
        accountPage.profileIsDisplayed();
        accountPage.clickStellarBurgersLogo();

        assertTrue(mainPage.constructBurgerIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем выход из аккаунта залогиненного пользователя кликом по кнопке Выход в Личном Кабинете")
    public void exitFromAccountWithExitButtonInPersonalAccount() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);
        accountPage.profileIsDisplayed();
        accountPage.clickExitButton();

        LoginPage loginPage = new LoginPage(webDriver);

        assertTrue(loginPage.enterIsDisplayed());
    }

    @After
    public void tearDown() {
        webDriver.quit();
        String accessToken = userAPISteps.loginUser(user)
                .extract().body().path("accessToken");
        if (accessToken != null) {
            user.setAccessToken(accessToken);
            userAPISteps.deleteUser(user);
        }
    }
}
