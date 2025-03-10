package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class CartPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Увеличен таймаут до 60 секунд
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


    public CartPage clickPayButton() {
        Allure.step("Нажатие кнопки оплаты", () -> {
            try {
                WebElement payButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                AppiumBy.xpath("//android.widget.Button[contains(@text, 'Pay')]")
                        )
                );
                Thread.sleep(1000); // Пауза для уверенности
                payButton.click();
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
                    WebElement payButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[contains(@text, 'Pay')]"));
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
}
