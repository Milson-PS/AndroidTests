package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriverProvider {

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion("14.0")  // Убедитесь, что версия Android корректна
                .setDeviceName("Pixel 6a API 34")  // Проверьте имя устройства через ADB
                .setApp(getAppPath())
                .setAppPackage("ru.citilink.develop")
                .setAppActivity("ru.citilink.app.presentation.mainactivity.MainActivity");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");  // Убедитесь, что сервер работает
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    private static String getAppPath() {
        // Укажите путь к вашему локальному APK-файлу
        String appName = "app-alpha-universal-release.apk"; // Имя вашего APK-файла
        String appPath = "src/test/resources/apps/" + appName;
        File app = new File(appPath);

        if (!app.exists()) {
            throw new RuntimeException("APK file not found at: " + app.getAbsolutePath());
        }

        return app.getAbsolutePath();
    }
}