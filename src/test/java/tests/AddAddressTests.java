package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import io.qameta.allure.Allure;

public class AddAddressTests extends TestBase {
    private AuthPage authPage;
    private ProfilePage profilePage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setupPages() {
        authPage = new AuthPage(driver);
        profilePage = new ProfilePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testAddAddress() {
        try {
            Allure.step("1. Авторизация в системе");
            authPage.loginAsDefaultUser();

            Allure.step("2. Переход в 'Мои адреса'");
            profilePage.openMyAddresses();

            Allure.step("3. Нажатие кнопки '+' для добавления нового адреса");
            profilePage.clickAddAddressButton();

            Allure.step("4. Попытка сохранить адрес с пустой формой");
            profilePage.clickSaveButton();

            Allure.step("5. Проверка ошибок для обязательных полей");
            Assert.assertTrue(profilePage.isCityErrorDisplayed(), "Ошибка 'Выберите город из списка' не отображается");
            Assert.assertTrue(profilePage.isStreetErrorDisplayed(), "Ошибка 'Введите улицу или выберите из списка' не отображается");
            Assert.assertTrue(profilePage.isHouseErrorDisplayed(), "Ошибка 'Введите номер дома' не отображается");

            Allure.step("6. Заполнение обязательных полей");
            profilePage.enterCity("Борки")
                    .enterStreet("Ленина")
                    .enterHouse("21");

            Allure.step("7. Сохранение адреса");
            profilePage.clickSaveButton();

            Allure.step("8. Проверка, что адрес успешно сохранен");
            Assert.assertTrue(profilePage.isAddressSaved(), "Адрес не был сохранен");

            Allure.step("9. Заполнение всех необязательных полей");
            profilePage.clickAddAddressButton()
                    .enterCity("Борки")
                    .enterStreet("Ленина")
                    .enterHouse("21")
                    .enterCorpus("1")
                    .enterBuilding("2А")
                    .enterPorch("3")
                    .enterFloor("1")
                    .enterFlat("9");

            Allure.step("10. Сохранение с заполненными всеми полями");
            profilePage.clickSaveButton();

            Allure.step("11. Проверка, что адрес сохранен с дополнительными полями");
            Assert.assertTrue(profilePage.isAddressSaved(), "Адрес с дополнительными полями не был сохранен");

            Allure.step("12. Возврат на главную страницу");
            searchPage.clickBackToProfileButton();
            searchPage.goToHomePage();

            Allure.step("13. Добавление товара в корзину");
            searchPage.searchProduct("1472159");
            productPage.addToCart();

            Allure.step("14. Переход в корзину");
            cartPage.goToCart();

            Allure.step("15. Оформление заказа");
            cartPage.proceedToCheckout();

            Allure.step("16. Выбор доставки");
            profilePage.selectDeliveryButton();
            profilePage.clickDeliveryButton();


            Allure.step("17. Проверка адреса доставки");
            profilePage.verifyDeliveryAddress();

        } catch (Exception e) {
            Allure.step("Ошибка в тесте: " + e.getMessage());
            throw e;
        }
    }
}

