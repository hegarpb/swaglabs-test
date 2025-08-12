package com.juaracoding.swaglabs;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.providers.DataTestProvider;
import com.juaracoding.swaglabs.screens.InventoryScreen;
import com.juaracoding.swaglabs.screens.LoginScreen;
import com.juaracoding.swaglabs.utils.DriverUtil;

import io.appium.java_client.android.AndroidDriver;

public class AuthenticationTest {

    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataTestProvider.class)
    public void loginTest(String username, String password, String expected) throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        AndroidDriver driver = driverUtil.getDriver();

        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.login(username, password);

        InventoryScreen inventoryScreen = new InventoryScreen(driver);

        String actual;

        // Jika expected kosong → login sukses, cek title
        if (expected == null || expected.isEmpty()) {
            actual = inventoryScreen.getTitle();  // sudah pakai explicit wait di getTitle()
            Assert.assertEquals(actual, "PRODUCTS");
        } else {
            // login gagal, ambil error message
            actual = loginScreen.getErrMessage(); // sudah pakai explicit wait di getErrMessage()
            Assert.assertEquals(actual, expected);
        }

        driverUtil.quitApp();
    }
}
