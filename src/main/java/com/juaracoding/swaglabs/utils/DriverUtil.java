package com.juaracoding.swaglabs.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DriverUtil {

    private AndroidDriver driver;
    private DesiredCapabilities capabilities;

    public DriverUtil() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "0c1037280411");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");

        // Tambahan untuk menghindari error hidden API policy
        capabilities.setCapability("appium:disableHiddenApiPolicyError", true);

        // Gunakan URI → URL untuk hindari warning deprecated
        URL appiumServerUrl = URI.create("http://127.0.0.1:4723/").toURL();
        driver = new AndroidDriver(appiumServerUrl, capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Hindari exception jika keyboard tidak muncul
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Keyboard tidak muncul, diabaikan...");
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void quitApp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
