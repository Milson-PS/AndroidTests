package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends BasePage { // Наследуемся от BasePage

    public SearchPage(AndroidDriver driver) {
        super(driver); // Вызов конструктора родительского класса
    }

    // Метод для поиска товара
    public SearchPage searchProduct(String productId) {
        // Нажимаем на кнопку поиска
        clickSearchButton();

        // Клик по полю поиска
        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/editTextSearchToolbar"))
        );
        searchInput.click();

        // Очистка поля (если нужно)
        searchInput.clear();

        // Ввод текста
        searchInput.sendKeys(productId);

        // Ожидание появления результатов поиска
        WebElement firstProduct = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ru.citilink.develop:id/recyclerViewSuggestedProducts']/android.view.ViewGroup[1]"))
        );
        firstProduct.click();

        return this;
    }

    // Метод для перехода на главную страницу
    public SearchPage goToHomePage() {
        WebElement homeButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Главная']"))
        );
        homeButton.click();
        return this;
    }
}