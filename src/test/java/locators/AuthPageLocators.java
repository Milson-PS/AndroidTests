package locators;

import org.openqa.selenium.By;

public class AuthPageLocators {
    public static final By DONT_ALLOW_BUTTON = By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_deny_button']");
    public static final By NO_BUTTON = By.xpath("//android.widget.Button[@resource-id='android:id/button2']");
    public static final By CITY_LIST = By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ru.citilink.develop:id/recyclerViewSelectCity']");
    public static final By MOSCOW_CITY = By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text='Москва']");
    public static final By PROFILE_TAB = By.xpath("//android.widget.FrameLayout[@content-desc='Профиль']");
    public static final By LOGIN_BUTTON = By.id("ru.citilink.develop:id/buttonProfileGuestHeaderAuth");
    public static final By PHONE_FIELD = By.id("ru.citilink.develop:id/editTextAuthPhone");
    public static final By GET_CODE_BUTTON = By.id("ru.citilink.develop:id/buttonAuthPhoneGetCode");
    public static final By SMS_FIELD = By.xpath("(//android.widget.FrameLayout[@resource-id='ru.citilink.develop:id/cellContainer'])[%d]");
    public static final By PHONE_INFO_ELEMENT = By.xpath("//android.widget.TextView[@resource-id='ru.citilink.develop:id/textViewProfilePhoneInfoCallInfo']");
}
