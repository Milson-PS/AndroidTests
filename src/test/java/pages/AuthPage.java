package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public AuthPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Метод для отказа от уведомлений
    public void dontAllowButton() {
        WebElement clickAdmonitionsButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']"))
        );
        clickAdmonitionsButton.click();
    }

    // Метод для выбора города
    public void selectCity() {
        WebElement noButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button1']"))
        );
        noButton.click();
    }

    // Переход в профиль
    public void openProfileTab() {
        WebElement profileTab = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Профиль']"))
        );
        profileTab.click();
    }

    // Клик на кнопку "Войти или зарегистрироваться"
    public void clickLoginButton() {
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonProfileGuestHeaderAuth"))
        );
        loginButton.click();
    }

    // Ввод номера телефона
    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
    }

    // Очистка поля ввода номера телефона
    public void clearPhoneNumber() {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.clear();
    }

    // Клик на кнопку получения смс
    public void clickGetSmsCodeButton() {
        WebElement getCodeButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonAuthPhoneGetCode"))
        );
        getCodeButton.click();
    }

    // Ввод кода из смс
    public void enterSmsCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            WebElement smsField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id='ru.citilink.develop:id/cellContainer'])[" + (i + 1) + "]"))
            );

            Actions actions = new Actions(driver);
            actions.sendKeys(smsField, Character.toString(code.charAt(i))).perform();
        }
    }

    // Проверка успешной авторизации
    public boolean isAuthSuccessful() {
        WebElement phoneInfoElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewProfilePhoneInfoCallInfo']"))
        );
        return phoneInfoElement.isDisplayed();
    }
}