package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Метод для перехода в корзину
    public CartPage goToCart() {
        try {
            // Ожидание и клик по кнопке в корзине
            WebElement cartButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//androidx.cardview.widget.CardView[@resource-id='ru.citilink.develop:id/buttonProductItemInCartCart']/androidx.cardview.widget.CardView/android.widget.LinearLayout")
                    )
            );
            cartButton.click();
            System.out.println("Успешно перешли в корзину.");
        } catch (Exception e) {
            System.out.println("Не удалось перейти в корзину: " + e.getMessage());
            throw e; // Повторно выбрасываем исключение, чтобы тест упал
        }
        return this;
    }

    // Метод для получения количества товаров в корзине
    public int getCartItemCount() {
        try {
            // Ожидание элемента с количеством товаров
            WebElement cartItemCount = wait.until(
                    ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/textViewCartItemCount"))
            );
            return Integer.parseInt(cartItemCount.getText());
        } catch (Exception e) {
            System.out.println("Не удалось получить количество товаров в корзине: " + e.getMessage());
            return 0; // Возвращаем 0, если элемент не найден
        }
    }

    // Метод для нажатия кнопки оформления заказа
    public CartPage proceedToCheckout() {
        try {
            WebElement checkoutButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonMakeOrder"))
            );
            checkoutButton.click();
            System.out.println("Нажата кнопка оформления заказа.");
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку оформления заказа: " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для нажатия кнопки "Продолжить как гость"
    public CartPage continueAsGuest() {
        try {
            WebElement guestButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonGuestContinue"))
            );
            guestButton.click();
            System.out.println("Нажата кнопка 'Продолжить как гость'.");
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку 'Продолжить как гость': " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для выбора адреса самовывоза
    public CartPage selectPickupAddress() {
        try {
            WebElement pickupAddressButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonContentOrderingDeliverySelfAddress"))
            );
            pickupAddressButton.click();
            System.out.println("Нажата кнопка выбора адреса самовывоза.");
        } catch (Exception e) {
            System.out.println("Не удалось выбрать адрес самовывоза: " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для нажатия кнопки "Список"
    public CartPage clickListButton() {
        try {
            WebElement listButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Список']"))
            );
            listButton.click();
            System.out.println("Нажата кнопка 'Список'.");
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку 'Список': " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для выбора пункта самовывоза
    public CartPage selectPickupPoint() {
        try {
            WebElement pickupPointButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@resource-id='ru.citilink.develop:id/buttonSetDeliverySelf'])[1]"))
            );
            pickupPointButton.click();
            System.out.println("Выбран пункт самовывоза.");
        } catch (Exception e) {
            System.out.println("Не удалось выбрать пункт самовывоза: " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для выбора метода оплаты банковской картой
    public CartPage selectBankCardPayment() {
        try {
            WebElement bankCardRadioButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName'])[5]"))
            );
            bankCardRadioButton.click();
            System.out.println("Выбран метод оплаты банковской картой.");
        } catch (Exception e) {
            System.out.println("Не удалось выбрать метод оплаты банковской картой: " + e.getMessage());
            throw e;
        }
        return this;
    }

    // Метод для нажатия кнопки "Оформить заказ"
    public CartPage placeOrder() {
        try {
            WebElement placeOrderButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonMakeOrder"))
            );
            placeOrderButton.click();
            System.out.println("Нажата кнопка 'Оформить заказ'.");
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку 'Оформить заказ': " + e.getMessage());
            throw e;
        }
        return this;
    }
}