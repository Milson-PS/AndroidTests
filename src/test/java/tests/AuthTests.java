package tests;

import base.TestBase;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;

public class AuthTests extends TestBase {
    private AuthPage authPage;

    @BeforeMethod
    public void setupPage() {
        authPage = new AuthPage(driver);
    }

    @Test
    public void successfulAuthTest() {
        Allure.step("Начало теста успешной авторизации");
        authPage
                .dontAllowButton()
                .selectCity()
                .openProfileTab()
                .clickLoginButton()
                .clearPhoneNumber()
                .enterPhoneNumber("79351111360")
                .clearPhoneNumber()
                .enterPhoneNumber("89351111360")
                .clearPhoneNumber()
                .pastePhoneNumber("79351111360")
                .clearPhoneNumber()
                .pastePhoneNumber("89351111360")
                .clearPhoneNumberButKeepPlus7()
                .pastePhoneNumber("92351111360")
                .clearPhoneNumber()
                .enterPhoneNumber("79351111360");
        Allure.step("Проверка успешной авторизации");
        Assert.assertTrue(authPage.isGetSmsCodeButtonEnabled(), "Кнопка получения SMS активна.");
        authPage.clickGetSmsCodeButton().enterSmsCode("1111");

        Assert.assertTrue(authPage.isAuthSuccessful(), "Авторизация не выполнена, элемент информации о телефоне не найден.");
    }
}
