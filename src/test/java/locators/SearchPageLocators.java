package locators;

import org.openqa.selenium.By;

public class SearchPageLocators {
    public static final By SEARCH_INPUT = By.id("ru.citilink.develop:id/editTextSearchToolbar");
    public static final By FIRST_PRODUCT = By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ru.citilink.develop:id/recyclerViewSuggestedProducts']/android.view.ViewGroup[1]");
    public static final By HOME_BUTTON = By.xpath("//android.widget.FrameLayout[@content-desc='Главная']");
    public static final By BACK_BUTTON = By.xpath("//android.widget.ImageButton");
}
