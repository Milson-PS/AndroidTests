package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import locators.AuthPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AuthPage extends BasePage {

    public AuthPage(AndroidDriver driver) {
        super(driver);
    }

    public AuthPage dontAllowButton() {
        clickElement(AuthPageLocators.DONT_ALLOW_BUTTON);
        return this;
    }

    public AuthPage selectCity() {
        clickElement(AuthPageLocators.NO_BUTTON);

        WebElement cityList = waitForElement(AuthPageLocators.CITY_LIST);
        WebElement moscowCity = cityList.findElement(AuthPageLocators.MOSCOW_CITY);

        if (moscowCity != null) {
            moscowCity.click();
        } else {
            System.out.println("Город Москва не найден!");
        }

        return this;
    }

    public AuthPage openProfileTab() {
        clickElement(AuthPageLocators.PROFILE_TAB);
        return this;
    }

    public AuthPage clickLoginButton() {
        clickElement(AuthPageLocators.LOGIN_BUTTON);
        return this;
    }

    public AuthPage enterPhoneNumber(String phoneNumber) {
        WebElement phoneField = waitForElement(AuthPageLocators.PHONE_FIELD);
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        return this;
    }

    public AuthPage clearPhoneNumber() {
        WebElement phoneField = waitForElement(AuthPageLocators.PHONE_FIELD);
        phoneField.clear();
        return this;
    }

    public AuthPage clickGetSmsCodeButton() {
        clickElement(AuthPageLocators.GET_CODE_BUTTON);
        return this;
    }

    public AuthPage enterSmsCode(String code) {
        for (int i = 0; i < code.length(); i++) {
            By smsFieldLocator = AppiumBy.xpath(String.format("(//android.widget.FrameLayout[@resource-id='ru.citilink.develop:id/cellContainer'])[%d]", i + 1));
            WebElement smsField = waitForElement(smsFieldLocator);

            Actions actions = new Actions(driver);
            actions.moveToElement(smsField).click().sendKeys(Character.toString(code.charAt(i))).perform();
        }
        return this;
    }


    public boolean isAuthSuccessful() {
        WebElement phoneInfoElement = waitForElement(AuthPageLocators.PHONE_INFO_ELEMENT);
        return phoneInfoElement.isDisplayed();
    }

    public boolean isGetSmsCodeButtonEnabled() {
        try {
            WebElement getCodeButton = waitForElement(AuthPageLocators.GET_CODE_BUTTON);
            return getCodeButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public AuthPage pastePhoneNumber(String phoneNumber) {
        return enterPhoneNumber(phoneNumber);
    }

    public AuthPage clearPhoneNumberButKeepPlus7() {
        WebElement phoneField = waitForElement(AuthPageLocators.PHONE_FIELD);
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
                .enterPhoneNumber("79351111344")
                .clickGetSmsCodeButton()
                .enterSmsCode("1111");

        Assert.assertTrue(isAuthSuccessful(), "Авторизация не выполнена");
        return this;
    }
}
