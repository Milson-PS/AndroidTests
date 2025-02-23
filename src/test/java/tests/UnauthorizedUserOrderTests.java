package tests;

import base.TestBase;
import io.qameta.allure.Allure;
import org.testng.Assert;
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
        Allure.step("1. Начало теста заказа неавторизованного пользователя");
        authPage
                .dontAllowButton()
                .selectCity();

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

      //  Allure.step("5. Проверка количества товаров в корзине");
        //int itemCount = cartPage.getCartItemCount();
        //Assert.assertEquals(itemCount, 2, "В корзине должно быть 2 товара");

        Allure.step("6. Оформление заказа");
        cartPage.proceedToCheckout();
        cartPage.continueAsGuest();

        Allure.step("7. Выбор адреса самовывоза");
        cartPage.selectPickupAddress();

        Allure.step("8. Нажатие кнопки 'Список'");
        cartPage.clickListButton();

        Allure.step("9. Выбор пункта самовывоза");
        cartPage.selectPickupPoint();

        Allure.step("10. Выбор метода оплаты банковской картой");
        cartPage.selectBankCardPayment();

        Allure.step("11. Нажатие кнопки 'Оформить заказ'");
        cartPage.placeOrder();

        Allure.step("12. Завершение теста");
    }
}
        //Добавление товара в корзину
        // Нажимаем на кнопку поиск (метод Base page) //android.view.ViewGroup[@resource-id="ru.citilink.develop:id/toolbar"] (resource-id ru.citilink.develop:id/toolbar)
        // Вставляем номер товара 1924513 //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextSearchToolbar"] resource-id ru.citilink.develop:id/editTextSearchToolbar
        // Кликаем на появившийся товар //androidx.recyclerview.widget.RecyclerView[@resource-id="ru.citilink.develop:id/recyclerViewSuggestedProducts"]/android.view.ViewGroup[1]
        //Нажимаем кнопку добавить в корзину //androidx.cardview.widget.CardView[@resource-id="ru.citilink.develop:id/buttonProductAddToCart"] resource-id ru.citilink.develop:id/buttonProductAddToCart
        //Возвращаемся в главное меню  (//android.widget.ImageView[@resource-id="ru.citilink.develop:id/navigation_bar_item_icon_view"])[1]  ru.citilink.develop:id/navigation_bar_item_icon_view
        // Нажимаем на кнопку поиск (метод Base page) //android.view.ViewGroup[@resource-id="ru.citilink.develop:id/toolbar"] (resource-id ru.citilink.develop:id/toolbar)
        // Вставляем номер товара 1924438 //android.widget.EditText[@resource-id="ru.citilink.develop:id/editTextSearchToolbar"] resource-id ru.citilink.develop:id/editTextSearchToolbar
        // Кликаем на появившийся товар //androidx.recyclerview.widget.RecyclerView[@resource-id="ru.citilink.develop:id/recyclerViewSuggestedProducts"]/android.view.ViewGroup[1]
        //Нажимаем кнопку добавить в корзину //androidx.cardview.widget.CardView[@resource-id="ru.citilink.develop:id/buttonProductAddToCart"] resource-id ru.citilink.develop:id/buttonProductAddToCart
        //
        //Перейти в корзину //android.widget.FrameLayout[@content-desc="Корзина"] ru.citilink.develop:id/ordering_graph
        // Проверить что два товара добавлены в корзину асерт
        //
        //Нажать кнопку к оформлению //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonMakeOrder"]  ru.citilink.develop:id/buttonMakeOrder
        //Нажать кнопку продолжить как гость //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonGuestContinue"]  ru.citilink.develop:id/buttonGuestContinue
        //
        // Оформление заказа (отдельная страница)
        //Выбрать адрес самовывоза кнопка выбрать //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonContentOrderingDeliverySelfAddress"] ru.citilink.develop:id/buttonContentOrderingDeliverySelfAddress
        //Нажать кнопку список //android.widget.LinearLayout[@content-desc="Список"]
        //Кликнуть кнопку Заберу отсюда (//android.widget.Button[@resource-id="ru.citilink.develop:id/buttonSetDeliverySelf"])[1]  ru.citilink.develop:id/buttonSetDeliverySelf
        //
        //Выбрать метод оплаты банковской картой (//android.widget.RadioButton[@resource-id="ru.citilink.develop:id/radioButtonName"])[5] ru.citilink.develop:id/radioButtonName
        //
        //Нажать кнопку оформить заказ  //android.widget.Button[@resource-id="ru.citilink.develop:id/buttonMakeOrder"]  ru.citilink.develop:id/buttonMakeOrder











//Без авторизации положить несколько товаров в корзину в приложении (со скидкой, без скидки, коробками)
//В корзине нажать на кнопку "К оформлению"
//Нажать на кнопку "Продолжить как гость"
//Нажать на поле "Город доставки"(android)
//Выбрать требуемый город
//В блоке Способ получения переключить радиобаттон на "Самовывоз"
//Нажать кнопку "Выбрать" в блоке выбора Адреса самовывоза
//Выбрать любую точку на карте и нажать "Заберу отсюда"
//В блоке Способ оплаты выбрать радиобаттон "Банковской картой онлайн"
//Номер карты
//4111 1111 1111 1111
//        2034/12  попробуй срок действия поставить 2025
//        123
//В поле "Получить чек по" выбрать любой способ получения чека Email или SMS и ввести необходимые валидные данные
//В блоке Контактные данные нажать на кнопку "Добавить"
//На экране Контактные данные ввести валидные данные в необходимые поля:
//Имя
//        Фамилия
//Телефон
//и нажать кнопку "Сохранить"
//Активировать чекбокс "Подтвердите правильность указанных контактных данных" тапнув по полю
//В блоке Комментарий к заказу при необходимости добавляем Комментарий
//Нажать кнопку "Оформить"
//Нажать кнопку "Оформить"
//Нажать на кнопку "Оплатить онлайн"
//В поле Номер карты ввести номер тестовой карты из пула тестовых карт для stage
//Заполнить поля Срок действия и Код
//Нажать кнопку Заплатить ХХХХ Р
//В появившемся окне нажать кнопку Confirm
