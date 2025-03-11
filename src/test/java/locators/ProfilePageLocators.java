package locators;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProfilePageLocators {
    public static final By ADD_BUTTON = AppiumBy.id("ru.citilink.develop:id/add");
    public static final By CLOSE_POPUP_BUTTON = AppiumBy.id("ru.citilink.develop:id/close_popup");
    public static final By PROFILE_TAB = AppiumBy.accessibilityId("Профиль");
    public static final By PROFILE_GRAPH = AppiumBy.id("ru.citilink.develop:id/profile_graph");
    public static final By MY_ADDRESSES = AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewLabel' and @text='Мои адреса']");
    public static final By SAVE_BUTTON = AppiumBy.id("ru.citilink.develop:id/buttonSave");
    public static final By CITY_ERROR = AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textinput_error' and @text='Выберите город из списка']");
    public static final By STREET_ERROR = AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textinput_error' and @text='Введите улицу или выберите из списка']");
    public static final By HOUSE_ERROR = AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textinput_error' and @text='Введите номер дома']");
    public static final By CITY_FIELD = AppiumBy.id("ru.citilink.develop:id/autoCompleteTextViewCity");
    public static final By STREET_FIELD = AppiumBy.id("ru.citilink.develop:id/autoCompleteTextViewStreet");
    public static final By HOUSE_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextHouse");
    public static final By CORPUS_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextCorpus");
    public static final By BUILDING_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextBuilding");
    public static final By PORCH_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextPorch");
    public static final By FLOOR_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextFloor");
    public static final By FLAT_FIELD = AppiumBy.id("ru.citilink.develop:id/editTextFlat");
    public static final By DELIVERY_BUTTON = AppiumBy.xpath("(//android.widget.RadioButton[@resource-id=\"ru.citilink.develop:id/radioButtonName\"])[2]");
    public static final By DELIVERY_COURIER_BUTTON = AppiumBy.id("ru.citilink.develop:id/buttonContentOrderingDeliveryCourierAddress");
    public static final By ADDRESS_AREA = AppiumBy.id("ru.citilink.develop:id/linearLayoutDragArea");
    public static final By ADDRESS_ELEMENT = AppiumBy.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewLabel' and @text='Ленина, д.21, к.1, ст.2А, под.3, этаж 1, кв.9']");
}
