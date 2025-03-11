package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import locators.ProfilePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage {

    public ProfilePage(AndroidDriver driver) {
        super(driver);
    }

    public ProfilePage clickAddAddressButton() {
        clickElement(ProfilePageLocators.ADD_BUTTON);
        return this;
    }

    public ProfilePage closePopupsIfNeeded() {
        try {
            clickElement(ProfilePageLocators.CLOSE_POPUP_BUTTON);
        } catch (Exception ignored) {
        }
        return this;
    }

    public ProfilePage openProfile() {
        closePopupsIfNeeded();
        clickElement(ProfilePageLocators.PROFILE_TAB);
        waitForElement(ProfilePageLocators.PROFILE_GRAPH);
        return this;
    }

    public ProfilePage openMyAddresses() {
        waitForElement(ProfilePageLocators.PROFILE_GRAPH);
        clickElement(ProfilePageLocators.MY_ADDRESSES);
        return this;
    }

    public ProfilePage clickSaveButton() {
        WebElement saveButton = waitForElement(ProfilePageLocators.SAVE_BUTTON);
        if (saveButton.getLocation().getY() < 0 || saveButton.getLocation().getX() < 0) {
            throw new IllegalStateException("Save button is not visible or is offscreen.");
        }
        saveButton.click();
        return this;
    }

    public boolean isCityErrorDisplayed() {
        return isErrorDisplayed(ProfilePageLocators.CITY_ERROR);
    }

    public boolean isStreetErrorDisplayed() {
        return isErrorDisplayed(ProfilePageLocators.STREET_ERROR);
    }

    public boolean isHouseErrorDisplayed() {
        return isErrorDisplayed(ProfilePageLocators.HOUSE_ERROR);
    }

    private boolean isErrorDisplayed(By errorLocator) {
        try {
            WebElement errorElement = waitForElement(errorLocator);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProfilePage enterCity(String city) {
        enterText(ProfilePageLocators.CITY_FIELD, city);
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
        enterText(ProfilePageLocators.STREET_FIELD, street);
        return this;
    }

    public ProfilePage enterHouse(String house) {
        enterText(ProfilePageLocators.HOUSE_FIELD, house);
        return this;
    }

    public ProfilePage enterCorpus(String corpus) {
        enterText(ProfilePageLocators.CORPUS_FIELD, corpus);
        return this;
    }

    public ProfilePage enterBuilding(String building) {
        enterText(ProfilePageLocators.BUILDING_FIELD, building);
        return this;
    }

    public ProfilePage enterPorch(String porch) {
        enterText(ProfilePageLocators.PORCH_FIELD, porch);
        return this;
    }

    public ProfilePage enterFloor(String floor) {
        enterText(ProfilePageLocators.FLOOR_FIELD, floor);
        return this;
    }

    public ProfilePage enterFlat(String flat) {
        enterText(ProfilePageLocators.FLAT_FIELD, flat);
        return this;
    }

    private void enterText(By locator, String text) {
        WebElement field = waitForElement(locator);
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    public ProfilePage selectDeliveryButton() {
        clickElement(ProfilePageLocators.DELIVERY_BUTTON);
        return this;
    }

    public ProfilePage clickDeliveryButton() {
        clickElement(ProfilePageLocators.DELIVERY_COURIER_BUTTON);
        return this;
    }

    public boolean isAddressSaved() {
        try {
            WebElement addressArea = waitForElement(ProfilePageLocators.ADDRESS_AREA);
            return addressArea.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProfilePage verifyDeliveryAddress() {
        WebElement addressElement = waitForElement(ProfilePageLocators.ADDRESS_ELEMENT);
        String actualAddress = addressElement.getText();
        String expectedAddress = "Ленина, д.21, к.1, ст.2А, под.3, этаж 1, кв.9";
        if (!actualAddress.equals(expectedAddress)) {
            throw new AssertionError("Expected address: " + expectedAddress + ", but found: " + actualAddress);
        }
        return this;
    }
}
