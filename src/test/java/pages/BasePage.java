package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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


    public BasePage clickSearchButton() {
        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("ru.citilink.develop:id/toolbar"))
        );
        searchButton.click();
        return this;
    }


    public BasePage goToHomePage() {
        WebElement homeButton = wait.until(
                ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.FrameLayout[@content-desc='Главная']"))
        );
        homeButton.click();
        return this;
    }
}