package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import locators.CartPageLocators;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class CartPage extends BasePage {

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public void switchToWebViewContext() {
        for (String context : driver.getContextHandles()) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }
    }

    public boolean checkYoomoneyText() {
        try {
            Allure.step("Проверка текста на форме Yoomoney");
            WebElement yoomoneyText = waitForElement(CartPageLocators.YOOMONEY_TEXT);
            return yoomoneyText.isDisplayed();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке текста Yoomoney: " + e.getMessage());
            return false;
        }
    }

    public CartPage enterCardDetails(String cardNumber, String expMonth, String expYear, String cvc) {
        Allure.step("Ввод данных карты", () -> {
            WebElement cardNumberField = waitForElement(CartPageLocators.CARD_NUMBER_FIELD);
            cardNumberField.sendKeys(cardNumber);

            WebElement expMonthField = waitForElement(CartPageLocators.EXP_MONTH_FIELD);
            expMonthField.sendKeys(expMonth);

            WebElement expYearField = waitForElement(CartPageLocators.EXP_YEAR_FIELD);
            expYearField.sendKeys(expYear);

            WebElement cvcField = waitForElement(CartPageLocators.CVC_FIELD);
            cvcField.sendKeys(cvc);
        });
        return this;
    }

    public CartPage clickPayButton() {
        Allure.step("Нажатие кнопки оплаты", () -> {
            try {
                clickElement(CartPageLocators.PAY_BUTTON);
                Thread.sleep(1000); // Пауза для уверенности
                System.out.println("Кнопка 'Pay' успешно нажата.");
            } catch (Exception e) {
                System.out.println("Ошибка при нажатии кнопки 'Pay': " + e.getMessage());
                throw e;
            }
        });
        return this;
    }

    public CartPage scrollToPayButton() {
        Allure.step("Прокрутка до кнопки оплаты", () -> {
            int maxAttempts = 5;
            int attempts = 0;
            boolean isFound = false;

            while (!isFound && attempts < maxAttempts) {
                try {
                    WebElement payButton = driver.findElement(CartPageLocators.PAY_BUTTON);
                    if (payButton.isDisplayed()) {
                        isFound = true;
                        System.out.println("Кнопка 'Pay' найдена после прокрутки.");
                    }
                } catch (NoSuchElementException e) {
                    attempts++;
                    System.out.println("Попытка " + attempts + ": Кнопка 'Pay' не найдена. Выполняем прокрутку...");

                    Dimension size = driver.manage().window().getSize();
                    int startX = size.width / 2;
                    int startY = (int) (size.height * 0.8);
                    int endY = (int) (size.height * 0.2);

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                    Sequence scroll = new Sequence(finger, 0);
                    scroll.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
                    scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
                    scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    driver.perform(Collections.singletonList(scroll));
                }
            }

            if (!isFound) {
                throw new RuntimeException("Кнопка 'Pay' не найдена после " + maxAttempts + " попыток.");
            }
        });
        return this;
    }

    public boolean waitForSuccessPage() {
        return Allure.step("Ожидание успешного завершения оплаты", () -> {
            WebElement successText = waitForElement(CartPageLocators.SUCCESS_TEXT);
            return successText.isDisplayed();
        });
    }

    public CartPage waitForPaymentPageToLoad() {
        try {
            Allure.step("Ожидание загрузки страницы оплаты...");
            Thread.sleep(10000); // 10 секунд
            System.out.println("Страница оплаты загружена.");
        } catch (InterruptedException e) {
            System.out.println("Ошибка при ожидании загрузки страницы оплаты: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return this;
    }

    private void scrollToEmailField() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"ru.citilink.develop:id/editTextOrderingPaymentEmail\"))"
        ));
    }

    public CartPage goToCart() {
        clickElement(CartPageLocators.CART_BUTTON);
        return this;
    }

    public CartPage proceedToCheckout() {
        clickElement(CartPageLocators.CHECKOUT_BUTTON);
        return this;
    }

    public CartPage continueAsGuest() {
        clickElement(CartPageLocators.GUEST_BUTTON);
        return this;
    }

    public CartPage selectPickupAddress() {
        clickElement(CartPageLocators.PICKUP_ADDRESS_BUTTON);
        return this;
    }

    public CartPage clickListButton() {
        clickElement(CartPageLocators.LIST_BUTTON);
        return this;
    }

    public CartPage selectPickupPoint() {
        clickElement(CartPageLocators.PICKUP_POINT_BUTTON);
        return this;
    }

    public CartPage selectBankCardPayment() {
        clickElement(CartPageLocators.BANK_CARD_RADIO_BUTTON);
        return this;
    }

    public CartPage enterEmail(String email) {
        scrollToEmailField();
        WebElement emailField = waitForElement(CartPageLocators.EMAIL_FIELD);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public CartPage clickAddContactDataButton() {
        clickElement(CartPageLocators.ADD_CONTACT_BUTTON);
        return this;
    }

    public CartPage enterFirstName(String name) {
        WebElement nameField = waitForElement(CartPageLocators.NAME_FIELD);
        nameField.sendKeys(name);
        return this;
    }

    public CartPage enterLastName(String surname) {
        WebElement surnameField = waitForElement(CartPageLocators.SURNAME_FIELD);
        surnameField.sendKeys(surname);
        return this;
    }

    public CartPage enterPhoneNumber(String phone) {
        WebElement phoneField = waitForElement(CartPageLocators.PHONE_FIELD);
        phoneField.sendKeys(phone);
        return this;
    }

    public CartPage clickSaveContactData() {
        clickElement(CartPageLocators.SAVE_BUTTON);
        return this;
    }

    public CartPage selectRadioButton() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                        "new UiSelector().resourceId(\"ru.citilink.develop:id/radioButtonName\"))"
        ));

        WebElement radioButton = waitForElement(CartPageLocators.RADIO_BUTTON);
        System.out.println("Найден элемент: " + radioButton.getText());
        radioButton.click();
        return this;
    }

    public CartPage placeOrder() {
        clickElement(CartPageLocators.PLACE_ORDER_BUTTON);
        return this;
    }
}
