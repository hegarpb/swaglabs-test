package com.juaracoding.swaglabs;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.screens.InventoryScreen;
import com.juaracoding.swaglabs.screens.LoginScreen;
import com.juaracoding.swaglabs.utils.DragPositionUtil;
import com.juaracoding.swaglabs.utils.DriverUtil;

public class AddToCartTest {
     @Test()
  public void TC0008() throws MalformedURLException {
    DriverUtil driverUtil = new DriverUtil();
    LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());
    InventoryScreen inventoryScreen = new InventoryScreen(driverUtil.getDriver());

    // Pre condition (sebelum melakukan test)
    loginScreen.login();

    inventoryScreen.drag(new DragPositionUtil(200, 100));
    String expected = "1";
    String actual = inventoryScreen.getTotalCart();
    Assert.assertEquals(actual, expected);

    driverUtil.quitApp();
  }
}
