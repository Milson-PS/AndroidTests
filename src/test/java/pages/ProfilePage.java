package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Метод для закрытия возможных попапов перед взаимодействием с профилем
    public ProfilePage closePopupsIfNeeded() {
        try {
            WebElement closeButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/close_popup"))
            );
            closeButton.click();
        } catch (Exception ignored) {
            // Если попапа нет, ничего страшного — просто продолжаем
        }
        return this;
    }

    // Метод для перехода в раздел "Профиль"
    public ProfilePage openProfile() {
        closePopupsIfNeeded(); // Закрываем возможные всплывающие окна
        WebElement profileTab = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Профиль']"))
        );
        profileTab.click();

        // Ждем, пока загрузится экран профиля
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/profile_graph")));

        return this;
    }

    // Метод для перехода в "Мои адреса"
    public ProfilePage openMyAddresses() {
        // Дожидаемся, что экран "Профиль" загрузился перед кликом
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/profile_graph")));

        WebElement myAddresses = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewLabel' and @text='Мои адреса']"))
        );
        myAddresses.click();
        return this;
    }

    // Нажатие на кнопку "+"
    public ProfilePage clickAddAddressButton() {
        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/add"))
        );
        addButton.click();
        return this;
    }

    // Нажатие кнопки "Сохранить"
    public ProfilePage clickSaveButton() {
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonSave"))
        );
        saveButton.click();
        return this;
    }

    // Проверка ошибки "Выберите город из списка"
    public boolean isCityErrorDisplayed() {
        return isErrorDisplayed("Выберите город из списка");
    }

    // Проверка ошибки "Введите улицу или выберите из списка"
    public boolean isStreetErrorDisplayed() {
        return isErrorDisplayed("Введите улицу или выберите из списка");
    }

    // Проверка ошибки "Введите номер дома"
    public boolean isHouseErrorDisplayed() {
        return isErrorDisplayed("Введите номер дома");
    }

    // Вспомогательный метод для проверки ошибок
    private boolean isErrorDisplayed(String errorMessage) {
        try {
            WebElement errorElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(
                            "//android.widget.TextView[@resource-id='ru.citilink.develop:id/textinput_error' and @text='" + errorMessage + "']"))
            );
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Ввод города
    public ProfilePage enterCity(String city) {
        enterText("ru.citilink.develop:id/autoCompleteTextViewCity", city);
        return this;
    }

    // Ввод улицы
    public ProfilePage enterStreet(String street) {
        enterText("ru.citilink.develop:id/autoCompleteTextViewStreet", street);
        return this;
    }

    // Ввод номера дома
    public ProfilePage enterHouse(String house) {
        enterText("ru.citilink.develop:id/editTextHouse", house);
        return this;
    }

    // Вспомогательный метод для ввода текста в поля
    private void enterText(String resourceId, String text) {
        WebElement field = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id(resourceId))
        );
        field.click();
        field.sendKeys(text);
    }

    // Проверка, что адрес сохранен
    public boolean isAddressSaved() {
        try {
            WebElement addressArea = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("ru.citilink.develop:id/linearLayoutDragArea"))
            );
            return addressArea.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
