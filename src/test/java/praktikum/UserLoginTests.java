package praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.modelAPI.User;
import praktikum.stellarBurgerPOM.*;
import praktikum.stepsAPI.UserAPISteps;

import static org.junit.Assert.assertTrue;

public class UserLoginTests {
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
    }

    @Test
    @DisplayName("Проверяем вход по кнопке Войти в аккаунт на главной")
    public void mainPageLoginWithLoginButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();

        mainPage.constructBurgerIsDisplayed();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем вход по кнопке Личный Кабинет")
    public void mainPageLoginWithAccountButton() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();

        mainPage.constructBurgerIsDisplayed();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем вход по кнопке в форме регистрации")
    public void registrationPageLogin() {
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.clickEnterLink();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();

        MainPage mainPage = new MainPage(webDriver);
        mainPage.constructBurgerIsDisplayed();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем вход по кнопке в форме восстановления пароля")
    public void resetPasswordPageLogin() {
        webDriver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(webDriver);
        resetPasswordPage.clickEnterLink();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();

        MainPage mainPage = new MainPage(webDriver);
        mainPage.constructBurgerIsDisplayed();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
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
