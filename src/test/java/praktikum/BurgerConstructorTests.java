package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.stellarBurgerPOM.MainPage;

import static org.junit.Assert.*;

public class BurgerConstructorTests {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = WebDriverFactory.getDriver();
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Проверяем переход к разделу Соусы")
    public void selectSaucesTab() {
        MainPage mainPage = new MainPage(webDriver);
        assertTrue(mainPage.selectSaucesTab());
    }

    @Test
    @DisplayName("Проверяем переход к разделу Начинки")
    public void selectFillingsTab() {
        MainPage mainPage = new MainPage(webDriver);
        assertTrue(mainPage.selectFillingsTab());
    }

    @Test
    @DisplayName("Проверяем переход к разделу Булки")
    public void selectBunsTab() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectFillingsTab();
        assertTrue(mainPage.selectBunsTab());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
