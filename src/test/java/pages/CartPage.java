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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Увеличен таймаут до 60 секунд
    }

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
        scrollToEmailField();
        WebElement emailField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextOrderingPaymentEmail"))
        );
        emailField.clear();
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
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonSave"))
        );
        saveButton.click();
        return this;
    }

    public CartPage selectRadioButton() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                        "new UiSelector().resourceId(\"ru.citilink.develop:id/radioButtonName\"))"
        ));

        WebElement radioButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath(
                                "//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName']"
                        )
                )
        );

        System.out.println("Найден элемент: " + radioButton.getText());
        radioButton.click();
        return this;
    }

    public CartPage placeOrder() {
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonMakeOrder"))
        );
        placeOrderButton.click();
        return this;
    }

    public CartPage waitForPaymentPageToLoad() {
        try {
            System.out.println("Ожидание загрузки страницы оплаты...");
            WebElement payButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonFooterPay"))
            );
            System.out.println("Страница оплаты загружена.");
            return this;
        } catch (Exception e) {
            System.out.println("Ошибка при ожидании загрузки страницы оплаты: " + e.getMessage());
            throw e;
        }
    }

    public CartPage enterCardDetails(String cardNumber, String expMonth, String expYear, String cvc) {
        try {
            WebElement cardNumberField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.widget.EditText"))
            );
            System.out.println("Поле для ввода номера карты найдено.");
            cardNumberField.sendKeys(cardNumber);

            WebElement expMonthField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[4]/android.view.View/android.widget.EditText"))
            );
            System.out.println("Поле для ввода месяца найдено.");
            expMonthField.sendKeys(expMonth);

            WebElement expYearField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[5]/android.view.View/android.widget.EditText"))
            );
            System.out.println("Поле для ввода года найдено.");
            expYearField.sendKeys(expYear);

            WebElement cvcField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='root']/android.view.View/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[7]/android.widget.EditText"))
            );
            System.out.println("Поле для ввода CVC найдено.");
            cvcField.sendKeys(cvc);

            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckBox[@text='I allow debiting money automatically']"))
            );
            System.out.println("Чекбокс найден.");
            checkbox.click();
        } catch (Exception e) {
            System.out.println("Ошибка при заполнении данных карты: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public CartPage confirmPayment() {
        try {
            WebElement payButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='Pay 2999₽']"))
            );
            System.out.println("Кнопка оплаты найдена.");
            payButton.click();
        } catch (Exception e) {
            System.out.println("Ошибка при подтверждении оплаты: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public CartPage waitForElementToBeVisible(String elementId) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(elementId))
        );
        return this;
    }
}
