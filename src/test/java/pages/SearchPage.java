package pages;

import io.appium.java_client.android.AndroidDriver;
import locators.SearchPageLocators;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public SearchPage searchProduct(String productId) {
        // Нажимаем на кнопку поиска
        clickSearchButton();

        // Клик по полю поиска
        WebElement searchInput = waitForElement(SearchPageLocators.SEARCH_INPUT);
        searchInput.click();

        // Очистка поля (если нужно)
        searchInput.clear();

        // Ввод текста
        searchInput.sendKeys(productId);

        // Ожидание появления результатов поиска
        WebElement firstProduct = waitForElement(SearchPageLocators.FIRST_PRODUCT);
        firstProduct.click();

        return this;
    }

    public SearchPage goToHomePage() {
        clickElement(SearchPageLocators.HOME_BUTTON);
        return this;
    }

    public SearchPage clickBackToProfileButton() {
        clickElement(SearchPageLocators.BACK_BUTTON);
        return this;
    }
}
