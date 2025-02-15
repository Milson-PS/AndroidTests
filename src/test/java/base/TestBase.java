package base;

import com.google.common.collect.ImmutableMap;
import drivers.LocalDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = LocalDriverProvider.createDriver();
        disableNotifications();  // Отключаем уведомления
        dismissNotifications();   // Скрываем панель уведомлений
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Метод для отключения уведомлений
    private void disableNotifications() {
        try {
            driver.executeScript("mobile: shell", ImmutableMap.of("command", "settings put global heads_up_notifications_enabled 0"));
            System.out.println("Уведомления отключены.");
        } catch (Exception e) {
            System.out.println("Не удалось отключить уведомления: " + e.getMessage());
        }
    }

    // Метод для скрытия панели уведомлений
    private void dismissNotifications() {
        try {
            driver.executeScript("mobile: shell", ImmutableMap.of("command", "cmd statusbar collapse"));
            System.out.println("Панель уведомлений скрыта.");
        } catch (Exception e) {
            System.out.println("Не удалось скрыть панель уведомлений: " + e.getMessage());
        }
    }
}
