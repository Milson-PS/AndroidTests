package tests;

import base.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;

public class UnauthorizedUserOrderTests extends TestBase {
    private AuthPage authPage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setupPages() {
        authPage = new AuthPage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void NoAuthOrderUserTest() {
        try {
            Allure.step("1. Начало теста заказа неавторизованного пользователя");
            authPage.dontAllowButton().selectCity();

            Allure.step("2. Добавление первого товара в корзину");
            searchPage.searchProduct("1472159");
            productPage.addToCart();

            Allure.step("2.1. Возврат на главную страницу");
            searchPage.goToHomePage();

            Allure.step("3. Добавление второго товара в корзину");
            searchPage.searchProduct("1115507");
            productPage.addToCart();

            Allure.step("4. Переход в корзину");
            cartPage.goToCart();

            Allure.step("5. Оформление заказа");
            cartPage.proceedToCheckout();
            cartPage.continueAsGuest();

            Allure.step("6. Выбор адреса самовывоза");
            cartPage.selectPickupAddress();

            Allure.step("7. Нажатие кнопки 'Список'");
            cartPage.clickListButton();

            Allure.step("8. Выбор пункта самовывоза");
            cartPage.selectPickupPoint();

            Allure.step("9. Выбор метода оплаты банковской картой");
            cartPage.selectBankCardPayment();

            Allure.step("10. Ввод email");
            cartPage.enterEmail("mps7017@yandex.ru");

            Allure.step("11. Добавление контактных данных");
            cartPage.clickAddContactDataButton();
            cartPage.enterFirstName("Андрей");
            cartPage.enterLastName("Пупкин");
            cartPage.enterPhoneNumber("+79123456789");

            Allure.step("12. Сохранение контактных данных");
            cartPage.clickSaveContactData();

            Allure.step("14. Оформление заказа");
            cartPage.placeOrder();
            Allure.step("13. Подтверждение данных");
            cartPage.selectRadioButton();

            Allure.step("14. Оформление заказа");
            cartPage.placeOrder();

            Allure.step("15. Ожидание загрузки страницы оплаты");
            cartPage.waitForPaymentPageToLoad();

            Allure.step("16. Переключение на контекст WEBVIEW");
            cartPage.switchToWebViewContext();

            Allure.step("17. Проверка текста на форме");
            cartPage.checkYoomoneyText();

            Allure.step("18. Ввод данных карты");
            cartPage.enterCardDetails("4111 1111 1111 1111", "12", "25", "123");


            Allure.step("20. Нажатие кнопки оплаты");
            cartPage.scrollToAndClickPayButton();

            Allure.step("21. Ожидание успешного завершения оплаты");
            boolean isSuccess = cartPage.waitForSuccessPage();
            if (isSuccess) {
                Allure.step("Оплата прошла успешно");
            } else {
                Allure.step("Оплата не прошла");
            }

            Allure.step("22. Завершение теста");
        } catch (Exception e) {
            Allure.step("Тест завершился с ошибкой: " + e.getMessage());
            throw e;
        }
    }
}