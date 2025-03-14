package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.MutableCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriverProvider {

    public static AndroidDriver createDriver(String deviceName, String platformVersion, String udid) {
        System.out.println("Creating driver for device: " + deviceName);
        System.out.println("UDID: " + udid);

        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(platformVersion)
                .setDeviceName(deviceName)
                .setUdid(udid) // Указываем UDID устройства
                .setApp(getAppPath())
                .setAppPackage("ru.citilink.develop")
                .setAppActivity("ru.citilink.app.presentation.mainactivity.MainActivity");

        MutableCapabilities mutableOptions = (MutableCapabilities) options;
        mutableOptions.setCapability("appium:video", true);
        mutableOptions.setCapability("appium:videoName", "test_video.mp4");

        try {
            URL appiumUrl = new URL("http://localhost:4723/wd/hub"); // Один порт для всех устройств
            System.out.println("Appium URL: " + appiumUrl);
            return new AndroidDriver(appiumUrl, options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Неверный URL Appium сервера", e);
        }
    }

    private static String getAppPath() {
        String appName = "app-alpha-universal-release.apk";
        String appPath = "src/test/resources/apps/" + appName;
        File app = new File(appPath);

        if (!app.exists()) {
            throw new RuntimeException("APK файл не найден по пути: " + app.getAbsolutePath());
        }
        return app.getAbsolutePath();
    }
}