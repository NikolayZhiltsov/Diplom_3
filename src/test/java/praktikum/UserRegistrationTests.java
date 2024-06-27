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
import praktikum.stellarBurgerPOM.RegisterPage;
import praktikum.stepsAPI.UserAPISteps;

import static org.junit.Assert.assertTrue;

public class UserRegistrationTests {
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
        webDriver = WebDriverFactory.getDriver();
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Проверяем успешную регистрацию пользователя")
    public void successRegistration() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registerIsDisplayed();
        registerPage.fillRegisterInfo(name, email, password);
        registerPage.clickRegisterButton();

        loginPage.enterIsDisplayed();
        loginPage.fillLoginInfo(email, password);
        loginPage.clickEnterButton();

        mainPage.constructBurgerIsDisplayed();
        mainPage.clickAccountButton();

        AccountPage accountPage = new AccountPage(webDriver);

        assertTrue(accountPage.profileIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем неуспешную регистрацию с паролем короче 6 знаков")
    public void failRegistration() {
        password = RandomStringUtils.randomAlphabetic(5);

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterIsDisplayed();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registerIsDisplayed();
        registerPage.fillRegisterInfo(name, email, password);
        registerPage.clickRegisterButton();

        assertTrue(registerPage.incorrectPasswordIsDisplayed());
    }


    @After
    public void tearDown() {
        webDriver.quit();
        user.setEmail(email);
        user.setPassword(password);
        String accessToken = userAPISteps.loginUser(user)
                .extract().body().path("accessToken");
        if (accessToken != null) {
            user.setAccessToken(accessToken);
            userAPISteps.deleteUser(user);
        }
    }
}
