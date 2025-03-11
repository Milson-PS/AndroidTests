package locators;

import org.openqa.selenium.By;

public class CartPageLocators {
    public static final By YOOMONEY_TEXT = By.xpath("(//android.view.View[@content-desc='yoomoney'])[2]/android.widget.Image");
    public static final By CARD_NUMBER_FIELD = By.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.view.View/android.widget.EditText");
    public static final By EXP_MONTH_FIELD = By.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[4]/android.view.View/android.widget.EditText");
    public static final By EXP_YEAR_FIELD = By.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[5]/android.view.View/android.widget.EditText");
    public static final By CVC_FIELD = By.xpath("//android.view.View[@resource-id='root']/android.view.View[2]/android.view.View[4]/android.view.View[2]/android.view.View[1]/android.view.View[7]/android.widget.EditText");
    public static final By PAY_BUTTON = By.xpath("//android.widget.Button[contains(@text, 'Pay')]");
    public static final By SUCCESS_TEXT = By.xpath("//android.widget.TextView[@text='Success']");
    public static final By CART_BUTTON = By.id("ru.citilink.develop:id/buttonProductItemInCartCart");
    public static final By CHECKOUT_BUTTON = By.id("ru.citilink.develop:id/buttonMakeOrder");
    public static final By GUEST_BUTTON = By.id("ru.citilink.develop:id/buttonGuestContinue");
    public static final By PICKUP_ADDRESS_BUTTON = By.id("ru.citilink.develop:id/buttonContentOrderingDeliverySelfAddress");
    public static final By LIST_BUTTON = By.xpath("//android.widget.LinearLayout[@content-desc='Список']");
    public static final By PICKUP_POINT_BUTTON = By.xpath("(//android.widget.Button[@resource-id='ru.citilink.develop:id/buttonSetDeliverySelf'])[1]");
    public static final By BANK_CARD_RADIO_BUTTON = By.xpath("(//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName'])[5]");
    public static final By EMAIL_FIELD = By.id("ru.citilink.develop:id/editTextOrderingPaymentEmail");
    public static final By ADD_CONTACT_BUTTON = By.id("ru.citilink.develop:id/buttonContentOrderingContactData");
    public static final By NAME_FIELD = By.id("ru.citilink.develop:id/editTextName");
    public static final By SURNAME_FIELD = By.id("ru.citilink.develop:id/editTextSurname");
    public static final By PHONE_FIELD = By.id("ru.citilink.develop:id/editTextPhone");
    public static final By SAVE_BUTTON = By.id("ru.citilink.develop:id/buttonSave");
    public static final By RADIO_BUTTON = By.xpath("//android.widget.RadioButton[@resource-id='ru.citilink.develop:id/radioButtonName']");
    public static final By PLACE_ORDER_BUTTON = By.id("ru.citilink.develop:id/buttonMakeOrder");
}
