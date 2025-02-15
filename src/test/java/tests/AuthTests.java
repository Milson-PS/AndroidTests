package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthPage;

public class AuthTests extends TestBase {
    @Test
    public void successfulAuthTest() {
        AuthPage authPage = new AuthPage(driver);

        // Отказываемся от уведомлений
        authPage.dontAllowButton();

        // Выбираем город
        authPage.selectCity();

        // Переходим в Профиль
        authPage.openProfileTab();

        // Нажимаем на кнопку "Войти или зарегистрироваться"
        authPage.clickLoginButton();

        // Вводим номер телефона и получаем смс код
        authPage.clearPhoneNumber();
        authPage.enterPhoneNumber("79351111360"); // Вводим номер телефона
        authPage.clickGetSmsCodeButton(); // Получаем код

        // Вводим код из SMS "1111"
        authPage.enterSmsCode("1111");

        // Проверяем, что авторизация успешна
        Assert.assertTrue(authPage.isAuthSuccessful(), "Ошибка авторизации: не найден элемент с информацией о телефоне.");
    }
}