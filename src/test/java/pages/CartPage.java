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

    // Прокрутка до поля email
    private void scrollToEmailField() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"ru.citilink.develop:id/editTextOrderingPaymentEmail\"))"
        ));
    }

    public CartPage goToCart() {
        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonProductItemInCartCart"))
        );
        cartButton.click();
        return this;
    }

    public CartPage proceedToCheckout() {
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonMakeOrder"))
        );
        checkoutButton.click();
        return this;
    }

    public CartPage continueAsGuest() {
        WebElement guestButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonGuestContinue"))
        );
        guestButton.click();
        return this;
    }

    public CartPage selectPickupAddress() {
        WebElement pickupAddressButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonContentOrderingDeliverySelfAddress"))
        );
        pickupAddressButton.click();
        return this;
    }

    public CartPage clickListButton() {
        WebElement listButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Список']"))
        );
        listButton.click();
        return this;
    }

    public CartPage selectPickupPoint() {
        WebElement pickupPointButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@resource-id='ru.citilink.develop:id/buttonSetDeliverySelf'])[1]"))
        );
        pickupPointButton.click();
        return this;
    }

    public CartPage selectBankCardPayment() {
        WebElement bankCardRadioButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName'])[5]"))
        );
        bankCardRadioButton.click();
        return this;
    }

    public CartPage enterEmail(String email) {
        // Прокручиваем до поля email
        scrollToEmailField();

        WebElement emailField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextOrderingPaymentEmail"))
        );
        emailField.clear();  // Очистим поле, если там что-то есть
        emailField.sendKeys(email);
        return this;
    }

    public CartPage clickAddContactDataButton() {
        WebElement addContactButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonContentOrderingContactData"))
        );
        addContactButton.click();
        return this;
    }

    public CartPage enterFirstName(String name) {
        WebElement nameField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextName"))
        );
        nameField.sendKeys(name);
        return this;
    }

    public CartPage enterLastName(String surname) {
        WebElement surnameField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextSurname"))
        );
        surnameField.sendKeys(surname);
        return this;
    }

    public CartPage enterPhoneNumber(String phone) {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextPhone"))
        );
        phoneField.sendKeys(phone);
        return this;
    }

    public CartPage clickSaveContactData() {
        // Ждём появления кнопки "Сохранить" и кликаем по ней
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonSave"))
        );
        saveButton.click();
        return this;
    }

    public CartPage selectRadioButton() {
        WebElement radioButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName' and @text='Подтверждаю данные']"))
        );
        radioButton.click();
        return this;
    }

    // Новый шаг: Подтверждение данных перед оформлением заказа
    public CartPage confirmDataAndProceed() {
        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonConfirmData"))
        );
        confirmButton.click();
        return this;
    }

    public CartPage placeOrder() {
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonMakeOrder"))
        );
        placeOrderButton.click();
        return this;
    }

    // Метод для ожидания видимости элемента
    public CartPage waitForElementToBeVisible(String elementId) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(elementId))
        );
        return this;
    }
}
