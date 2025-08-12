package com.juaracoding.swaglabs.screens;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.juaracoding.swaglabs.components.HeaderComponent;
import com.juaracoding.swaglabs.utils.DragPositionUtil;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class InventoryScreen {
  private AndroidDriver driver;
  private By header = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
  private By productCards = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]");
  private By scrollView = AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]");
  private By buttonDrag = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"])[1]");
  private HeaderComponent headerComponent;

  public InventoryScreen(AndroidDriver driver, HeaderComponent headerComponent) {
    this.driver = driver;
    this.headerComponent = headerComponent;
  }

  public InventoryScreen(AndroidDriver driver) {
    this.driver = driver;
    this.headerComponent = new HeaderComponent(driver);
}


  public String getTitle() {
    return driver.findElement(header).getText();
  }

  public int getTotalProduct() {
    return driver.findElements(productCards).size();
  }

  public String getTotalCart() {
    return driver.findElement(headerComponent.getTotalByOne()).getText();
  }

  public void scrollDown(double percent) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("elementId", driver.findElement(scrollView));
    params.put("direction", "down");
    params.put("percent", percent);
    params.put("speed", 1000);

    js.executeScript("mobile: scrollGesture", params);
  }

  public void drag(DragPositionUtil dragPositionUtil) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Map<String, Object> params = new HashMap<String, Object>();

    int x = driver.findElement(scrollView).getLocation().getX() + dragPositionUtil.getCustomX();
    int y = driver.findElement(scrollView).getLocation().getY() - dragPositionUtil.getCustomY();

    params.put("elementId", driver.findElement(buttonDrag));
    params.put("endX", x);
    params.put("endY", y);
    params.put("speed", 5000);

    js.executeScript("mobile: dragGesture", params);

  }

}
