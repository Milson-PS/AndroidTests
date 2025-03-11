package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Универсальный метод для клика по элементу
    protected void clickElement(By by) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    // Универсальный метод для ожидания элемента
    protected WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public BasePage clickSearchButton() {
        clickElement(By.id("ru.citilink.develop:id/toolbar"));
        return this;
    }

    public BasePage goToHomePage() {
        clickElement(By.xpath("//android.widget.FrameLayout[@content-desc='Главная']"));
        return this;
    }
}
