package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ProductPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public ProductPage addToCart() {
        // Ожидание кнопки "Добавить в корзину"
        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/buttonProductAddToCart"))
        );
        addToCartButton.click();

        return this;
    }
}