package pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AuthPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public AuthPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public AuthPage dontAllowButton() {
        WebElement clickAdmonitionsButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']"))
        );
        clickAdmonitionsButton.click();
        return this;
    }


    public AuthPage selectCity() {
        // Ожидаем, пока кнопка "Нет" (android:id/button2) станет кликабельной
        WebElement noButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id='android:id/button2']"))
        );
        // Нажимаем на кнопку "Нет"
        noButton.click();

        // Ожидаем, пока появится список городов (RecyclerView с городами)
        WebElement cityList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ru.citilink.develop:id/recyclerViewSelectCity']"))
        );

        // Ищем элемент с текстом "Москва"
        WebElement moscowCity = cityList.findElement(AppiumBy.xpath("//android.widget.LinearLayout//android.widget.TextView[@text='Москва']"));
        if (moscowCity != null) {
            // Нажимаем на "Москва"
            moscowCity.click();
        } else {
            // Если не найдено, выводим ошибку
            System.out.println("Город Москва не найден!");
        }

        return this;
    }



    public AuthPage openProfileTab() {
        WebElement profileTab = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Профиль']"))
        );
        profileTab.click();
        return this;
    }

    public AuthPage clickLoginButton() {
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonProfileGuestHeaderAuth"))
        );
        loginButton.click();
        return this;
    }

    public AuthPage enterPhoneNumber(String phoneNumber) {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        return this;
    }

    public AuthPage clearPhoneNumber() {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.clear();
        return this;
    }

    public AuthPage clickGetSmsCodeButton() {
        WebElement getCodeButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonAuthPhoneGetCode"))
        );
        getCodeButton.click();
        return this;
    }

    public AuthPage enterSmsCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            WebElement smsField = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id='ru.citilink.develop:id/cellContainer'])[" + (i + 1) + "]"))
            );

            Actions actions = new Actions(driver);
            actions.sendKeys(smsField, Character.toString(code.charAt(i))).perform();
        }
        return this;
    }

    public boolean isAuthSuccessful() {
        WebElement phoneInfoElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewProfilePhoneInfoCallInfo']"))
        );
        return phoneInfoElement.isDisplayed();
    }

    public boolean isPhoneNumberFieldDisplayed() {
        try {
            WebElement phoneField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
            );
            return phoneField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGetSmsCodeButtonEnabled() {
        try {
            WebElement getCodeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonAuthPhoneGetCode"))
            );
            return getCodeButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSmsCodeFieldDisplayed() {
        try {
            WebElement smsField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("ru.citilink.develop:id/editTextAuthCode"))
            );
            return smsField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConfirmButtonEnabled() {
        try {
            WebElement confirmButton = wait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonAuthConfirm"))
            );
            return confirmButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public AuthPage pastePhoneNumber(String phoneNumber) {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        return this;
    }

    public AuthPage clearPhoneNumberButKeepPlus7() {
        WebElement phoneField = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextAuthPhone"))
        );
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys("+7");
        return this;
    }
    public AuthPage loginAsDefaultUser() {
        dontAllowButton()
                .selectCity()
                .openProfileTab()
                .clickLoginButton()
                .clearPhoneNumber()
                .enterPhoneNumber("79351111360")
                .clickGetSmsCodeButton()
                .enterSmsCode("1111");

        Assert.assertTrue(isAuthSuccessful(), "Авторизация не выполнена");
        return this;
    }

}
