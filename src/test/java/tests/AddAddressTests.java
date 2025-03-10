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
            profilePage.enterCity("Москва")
                    .enterStreet("Ленина")
                    .enterHouse("21");

            Allure.step("7. Сохранение адреса");
            profilePage.clickSaveButton();

            Allure.step("8. Проверка, что адрес успешно сохранен");
            Assert.assertTrue(profilePage.isAddressSaved(), "Адрес не был сохранен");

            Allure.step("9. Заполнение всех необязательных полей");
            profilePage.clickAddAddressButton()
                    .enterCity("Москва")
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


            Allure.step("18. Проверка адреса доставки");
            profilePage.verifyDeliveryAddress();

        } catch (Exception e) {
            Allure.step("Ошибка в тесте: " + e.getMessage());
            throw e;
        }
    }
}





//Открыть приложение
//Перейти в Профиль -> Мои адреса //android.widget.FrameLayout[@content-desc="Профиль"] ru.citilink.develop:id/profile_graph
//Мои адреса //android.widget.TextView[@resource-id="ru.citilink.develop:id/textViewLabel" and @text="Мои адреса"] ru.citilink.develop:id/textViewLabel
//Клик на "+" //android.widget.Button[@resource-id="ru.citilink.develop:id/add"] ru.citilink.develop:id/add


//Жмем сохранить с пустой формой //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonSave"] ru.citilink.develop:id/buttonSa
// проверяем что появилась ошибка //android.widget.TextView[@resource-id="ru.citilink.develop:id/textinput_error" and @text="Выберите город из списка"]
//проверяем что появилась ошибка //android.widget.TextView[@resource-id="ru.citilink.develop:id/textinput_error" and @text="Введите улицу или выберите из списка"]
//проверяем что появилась ошибка //android.widget.TextView[@resource-id="ru.citilink.develop:id/textinput_error" and @text="Введите номер дома"]
//
//

//обязательные поля - Город, Улица, Дом
// Город //android.widget.AutoCompleteTextView[@resource-id="ru.citilink.develop:id/autoCompleteTextViewCity"] ru.citilink.develop:id/autoCompleteTextViewCity
// Вводим Москва
// клик на Улица //android.widget.AutoCompleteTextView[@resource-id="ru.citilink.develop:id/autoCompleteTextViewStreet"] ru.citilink.develop:id/autoCompleteTextViewStreet
// ВВодим Ленина
//Клик на дом  //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextHouse"]  ru.citilink.develop:id/editTextHouse
// ввод 21
//Клик на корпус ru.citilink.develop:id/editTextHouse ru.citilink.develop:id/editTextCorpus
//ВВодим 1
//Клик на строение //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextBuilding"]  ru.citilink.develop:id/editTextBuilding
// ВВодим 2А
//Клик на подьезд //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextPorch"] ru.citilink.develop:id/editTextPorch
// Вводим 3
//Клик на этаж //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextFloor"] ru.citilink.develop:id/editTextFloor
// Вводим 1
// Клик на квартира //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextFlat"]  ru.citilink.develop:id/editTextFlat
//Вводим 9
//Жмем сохранить //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonSave"] ru.citilink.develop:id/buttonSave

// проверить что адрес сохранен //android.widget.LinearLayout[@resource-id="ru.citilink.develop:id/linearLayoutDragArea"] ru.citilink.develop:id/linearLayoutDragArea



//необязательные поля - Корпус, Строение, Подъезд, Этаж, Квартира
//кнопка Сохранить
//Клик на кнопку Отменить
//Клик на "+"
//Отправить запрос на сервер с пустой формой
//обязательные поля Город, Улица и Дом подсветились красным, под полями появились тексты ошибок
//Выберите город из списка
//Введите улицу или выберите из списка
//Введите номер дома
//Заполнить все обязательные поля корректными данными
//Отправить форму
//Форма отправлена
//переход на экран с адресами
//адрес успешно сохранен в списке Мои адреса
//Заполнить все поля формы
//Сохранить
//Форма отправлена
//переход на экран с адресами
//адрес успешно сохранен в списке Мои адреса
//Проверить наличие добавленного адреса в списке адресов при оформлении заказа:
//Положить товар в корзину
//Перейти в оформление заказа
//Клик по кнопке Изменить в блоке Адрес доставки
//Сохраненный адрес попал в список
