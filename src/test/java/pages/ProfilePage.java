package pages;

import base.TestBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends TestBase {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public ProfilePage clickAddAddressButton() {
        WebElement addButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/add"))
        );
        addButton.click();
        return this;
    }

    public ProfilePage closePopupsIfNeeded() {
        try {
            WebElement closeButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/close_popup"))
            );
            closeButton.click();
        } catch (Exception ignored) {
        }
        return this;
    }

    public ProfilePage openProfile() {
        closePopupsIfNeeded();
        WebElement profileTab = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Профиль"))
        );
        profileTab.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/profile_graph")));
        return this;
    }

    public ProfilePage openMyAddresses() {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("ru.citilink.develop:id/profile_graph")));
        WebElement myAddresses = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewLabel' and @text='Мои адреса']"))
        );
        myAddresses.click();
        return this;
    }

    public ProfilePage clickSaveButton() {
        WebElement saveButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("ru.citilink.develop:id/buttonSave"))
        );
        if (saveButton.getLocation().getY() < 0 || saveButton.getLocation().getX() < 0) {
            throw new IllegalStateException("Save button is not visible or is offscreen.");
        }
        saveButton.click();
        return this;
    }

    public boolean isCityErrorDisplayed() {
        return isErrorDisplayed("Выберите город из списка");
    }

    public boolean isStreetErrorDisplayed() {
        return isErrorDisplayed("Введите улицу или выберите из списка");
    }

    public boolean isHouseErrorDisplayed() {
        return isErrorDisplayed("Введите номер дома");
    }

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

    public ProfilePage enterCity(String city) {
        enterText("ru.citilink.develop:id/autoCompleteTextViewCity", city);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ListView")));
            String xpath = String.format("//android.widget.TextView[@text='%s']", city);
            WebElement cityOption = driver.findElement(By.xpath(xpath));
            cityOption.click();
        } catch (TimeoutException e) {
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        }
        return this;
    }

    public ProfilePage enterStreet(String street) {
        enterText("ru.citilink.develop:id/autoCompleteTextViewStreet", street);
        return this;
    }

    public ProfilePage enterHouse(String house) {
        enterText("ru.citilink.develop:id/editTextHouse", house);
        return this;
    }

    public ProfilePage enterCorpus(String corpus) {
        enterText("ru.citilink.develop:id/editTextCorpus", corpus);
        return this;
    }

    public ProfilePage enterBuilding(String building) {
        enterText("ru.citilink.develop:id/editTextBuilding", building);
        return this;
    }

    public ProfilePage enterPorch(String porch) {
        enterText("ru.citilink.develop:id/editTextPorch", porch);
        return this;
    }

    public ProfilePage enterFloor(String floor) {
        enterText("ru.citilink.develop:id/editTextFloor", floor);
        return this;
    }

    public ProfilePage enterFlat(String flat) {
        enterText("ru.citilink.develop:id/editTextFlat", flat);
        return this;
    }

    private void enterText(String resourceId, String text) {
        WebElement field = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id(resourceId))
        );
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    public ProfilePage selectDeliveryButton() {
        clickElement(AppiumBy.xpath("(//android.widget.RadioButton[@resource-id=\"ru.citilink.develop:id/radioButtonName\"])[2]"));
        return this;
    }

    public ProfilePage clickDeliveryButton() {
        clickElement(AppiumBy.id("ru.citilink.develop:id/buttonContentOrderingDeliveryCourierAddress"));
        return this;
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        element.click();
    }

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

    public ProfilePage verifyDeliveryAddress() {
        WebElement addressElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewLabel' and @text='Ленина, д.21, к.1, ст.2А, под.3, этаж 1, кв.9']"))
        );
        String actualAddress = addressElement.getText();
        String expectedAddress = "Ленина, д.21, к.1, ст.2А, под.3, этаж 1, кв.9";
        if (!actualAddress.equals(expectedAddress)) {
            throw new AssertionError("Expected address: " + expectedAddress + ", but found: " + actualAddress);
        }
        return this;
    }
}
