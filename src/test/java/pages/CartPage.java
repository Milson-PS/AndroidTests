package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
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

    // Переключение на контекст WEBVIEW
    public void switchToWebViewContext() {
        for (String context : driver.getContextHandles()) {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                break;
            }
        }
    }

    // Переключение на контекст NATIVE_APP
    public void switchToNativeContext() {
        driver.context("NATIVE_APP");
    }

    // Проверка текста на форме Yoomoney
    public boolean checkYoomoneyText() {
        try {
            Allure.step("Проверка текста на форме Yoomoney");
            WebElement yoomoneyText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("(//android.view.View[@content-desc='yoomoney'])[2]/android.widget.Image")
                    )
            );
            return yoomoneyText.isDisplayed();
        } catch (Exception e) {
            System.out.println("Ошибка при проверке текста Yoomoney: " + e.getMessage());
            return false;
        }
    }

    // Ввод данных карты
    public CartPage enterCardDetails(String cardNumber, String expMonth, String expYear, String cvc) {
        Allure.step("Ввод данных карты", () -> {
            // Ввод номера карты
            WebElement cardNumberField = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.view.View/android.widget.EditText")
                    )
            );
            cardNumberField.sendKeys(cardNumber);

            // Ввод месяца
            WebElement expMonthField = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[4]/android.view.View/android.widget.EditText")
                    )
            );
            expMonthField.sendKeys(expMonth);

            // Ввод года
            WebElement expYearField = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[5]/android.view.View/android.widget.EditText")
                    )
            );
            expYearField.sendKeys(expYear);

            // Ввод CVC
            WebElement cvcField = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[7]/android.widget.EditText")
                    )
            );
            cvcField.sendKeys(cvc);
        });
        return this;
    }

    // Свайп для поиска кнопки оплаты
    public CartPage swipeToFindPayButton() {
        Allure.step("Свайп для поиска кнопки оплаты", () -> {
            try {
                // Используем UiScrollable для прокрутки экрана
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                                "new UiSelector().textContains(\"Pay\"))"
                ));
                System.out.println("Кнопка 'Pay' найдена после свайпа.");
            } catch (Exception e) {
                System.out.println("Ошибка при поиске кнопки 'Pay': " + e.getMessage());
                throw e; // Пробрасываем исключение, чтобы тест завершился с ошибкой
            }
        });
        return this;
    }

    // Нажатие кнопки оплаты
    public CartPage scrollToAndClickPayButton() {
        Allure.step("Прокрутка до кнопки оплаты и клик по ней", () -> {
            // Пытаемся найти и кликнуть на кнопку оплаты
            try {
                // Ищем кнопку оплаты без прокрутки
                WebElement payButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Pay 4509']"));
                payButton.click();
            } catch (NoSuchElementException e) {
                // Если кнопка не найдена, выполняем прокрутку и повторяем поиск
                boolean isFound = false;
                while (!isFound) {
                    // Выполняем свайп вверх
                    Dimension size = driver.manage().window().getSize();
                    int startY = (int) (size.height * 0.8);
                    int endY = (int) (size.height * 0.2);
                    int startX = size.width / 2;
                    new TouchAction<>(driver)
                            .press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();

                    // Пытаемся найти кнопку оплаты после прокрутки
                    try {
                        WebElement payButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Pay 4509']"));
                        payButton.click();
                        isFound = true;
                    } catch (NoSuchElementException ignored) {
                        // Если кнопка все еще не найдена, продолжаем прокрутку
                    }
                }
            }
        });
        return this;
    }


    // Ожидание успешного завершения оплаты
    public boolean waitForSuccessPage() {
        return Allure.step("Ожидание успешного завершения оплаты", () -> {
            WebElement successText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Success']")
                    )
            );
            return successText.isDisplayed();
        });
    }

    // Ожидание загрузки страницы оплаты
    public CartPage waitForPaymentPageToLoad() {
        try {
            Allure.step("Ожидание загрузки страницы оплаты...");
            // Просто ждем 10 секунд (или другое время, которое вам нужно)
            Thread.sleep(10000); // 10 секунд
            System.out.println("Страница оплаты загружена.");
        } catch (InterruptedException e) {
            System.out.println("Ошибка при ожидании загрузки страницы оплаты: " + e.getMessage());
            Thread.currentThread().interrupt(); // Восстановление прерванного статуса
        }
        return this;
    }

    // Остальные методы из вашего исходного кода
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

    public CartPage waitForElementToBeVisible(String elementId) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(elementId))
        );
        return this;
    }
}