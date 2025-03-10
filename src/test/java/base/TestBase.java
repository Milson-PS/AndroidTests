package base;

import drivers.LocalDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class TestBase {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = LocalDriverProvider.createDriver();
        // Запуск видео записи
        driver.startRecordingScreen();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Останавливаем запись и получаем видео в Base64
            String videoBase64 = driver.stopRecordingScreen();
            byte[] videoBytes = Base64.getDecoder().decode(videoBase64);
            // Прикрепляем видео к Allure отчету
            Allure.addAttachment("Test Video", "video/mp4", new ByteArrayInputStream(videoBytes), ".mp4");
            driver.quit();
        }
    }

}
